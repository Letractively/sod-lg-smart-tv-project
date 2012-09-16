package sod.smarttv;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import javax.jws.Oneway;


import sod.common.ActionEx;
import sod.common.ConsoleLogger;
import sod.common.Constants;
import sod.common.NetworkUtils;
import sod.common.Packet;
import sod.common.ReceiveHandler;
import sod.common.Serializer;
import sod.common.ThreadEx;
import sod.common.Transceiver;
import sod.common.Tuple;

/**
 * 
 * @author MB
 *
 */
public class AccessManagerServer {
	protected ConnectHandler cb_conn;
	protected DisconnectHandler cb_disc;
	protected ServerReceiveHandler cb_recv;
	
	protected MulticastSocket listener_multi;
	protected Transceiver listener;
	protected ServerConfig config;
	protected boolean isRunning = false;
	protected Map<Integer, Tuple<Transceiver, Long>> connset;
	
	public AccessManagerServer(){
		connset = new ConcurrentHashMap<Integer, Tuple<Transceiver,Long>>();
	}
	
	/**
	 * 서버 초기화 및 beginListening, beginCheckingConnection 호출
	 * @param conf
	 * 서버의 정보
	 * @throws IllegalArgumentException
	 * 매개변수가 null일때 발생
	 * @throws IllegalStateException
	 * setReceiveHandler()가 호출되지 않았거나 이미 이전에 start()가 호출되었다면 발생
	 */
	public void start(ServerConfig conf) throws IllegalArgumentException, IllegalStateException{
		if(conf == null) throw new IllegalArgumentException("argument conf should not be null.");
		if(isRunning) throw new IllegalStateException("already server is running.");
		
		this.config = conf;
		listener = new Transceiver(null, conf.Port);
		isRunning = true;
		
		beginListening();
		beginListeningMulti();
		beginCheckingConnection();
	}

	/**
	 * beginListening, beginCheckingConnection에서 생성한 스레드를 종료하고,
	 * 콜백 함수를 전부 제거하고 할당된 모든 리소스 해제.
	 * @throws IllegalStateException
	 * start()가 호출되기 전에 호출되거나 두번 이상 호출될 시 발생
	 */
	public void shutdown() throws IllegalStateException{
		if(!isRunning) throw new IllegalStateException("server is not running.");
		
		isRunning = false;
		connset.clear();
		listener.dispose();
		listener = null;
		listener_multi.close();
		listener_multi = null;
	}

	/**
	 * 새 연결이 들어왔을떄 호출되는 콜백함수 등록
	 * @param handler
	 * 콜백함수를 구현한 객체
	 * @throws IllegalArgumentException
	 * 매개변수가 null일때 발생
	 */
	public void setConnectHandler(ConnectHandler handler) throws IllegalArgumentException{
		if(handler == null) throw new IllegalArgumentException("argument handler should not be null.");
		
		cb_conn = handler;
	}

	/**
	 * 연결이 끊겼을떄 호출되는 콜백함수 등록
	 * @param handler
	 * 콜백함수를 구현한 객체
	 * @throws IllegalArgumentException
	 * 매개변수가 null일때 발생
	 */
	public void setDisconnectHandler(DisconnectHandler handler) throws IllegalArgumentException{
		if(handler == null) throw new IllegalArgumentException("argument handler should not be null.");
		
		cb_disc = handler;
	}

	/**
	 * 현재 서버에 접속이 성립되어 생성된 Transceiver중 어떤 것이든 onReceive 콜백이 호출될때
	 * 전달된 Packet및 해당 Transceiver의 id를 넘겨주는 콜백 함수를 등록한다.
	 * @param handler
	 * 콜백함수를 구현한 객체
	 * @throws IllegalArgumentException
	 * 매개변수가 null일때 발생
	 */
	public void setReceiveHandler(ServerReceiveHandler handler) throws IllegalArgumentException{
		if(handler == null) throw new IllegalArgumentException("argument handler should not be null.");
		
		cb_recv = handler;
	}
	
	/**
	 * 컬렉션에 등록된 연결 갯수를 가져온다.
	 * @return
	 * 현재 붙어있는 연결 갯수
	 */
	public int getConnectionCount(){
		return connset.size();
	}

	/**
	 * Send a Packet via Transceiver which matching connid.
	 * @param pkt
	 * 데이터를 담은 패킷
	 * @param connid
	 * Transceiver 객체를 가르키는 id
	 */
	public boolean send(Packet pkt, int connid){
		if(!isRunning) return false;		
		if(pkt == null) return false;
		if(!connset.containsKey(connid)) return false;
		
		Transceiver t = connset.get(connid).item1;
		return t.send(pkt);
	}

	/**
	 * 해당 id에 일치하는 Transceiver의 연결을 끊고 컬렉션에서 제거한다.
	 * @param connid
	 * Transceiver 객체를 가르키는 id
	 * @throws IllegalArgumentException
	 * connid가 유효하지 않은 경우 발생
	 */
	public void dropConnection(int connid) throws IllegalArgumentException{
		if(!connset.containsKey(connid))
			throw new IllegalArgumentException("connid is not valid.");
		
		connset.remove(connid);
	}

	/**
	 * 새로운 연결을 수신(Accept)하거나 데이터를 수신(Receive)하기 위한 작업 스레드를 생성한다.
	 * 연결이 될때마다 내부의 컬렉션에 연결이 추가된다.
	 */
	protected void beginListening(){
		ThreadEx.invoke(null, new ActionEx() {		
			
			@Override
			public void work(Object arg) {
				Packet p = new Packet();
				Packet p2 = new Packet();
				InetSocketAddress sender = null;
				Tuple<Transceiver, Long> t = null;
				Transceiver sender_t = null;
				
				while(isRunning){
					p.clear();
					sender = listener.receive(p);
					if(sender == null) continue;
					if(Constants.isDebug)
						Constants.logger.log("(debug:server) received a packet.\n");
					
					switch(p.signiture){
					case Packet.REQUEST_ACCEPT:
						t = new Tuple<Transceiver, Long>();
						t.item1 = new Transceiver(sender);
						t.item2 = ThreadEx.getCurrentTime();
						connset.put(sender.hashCode(), t);
						sendServiceName(config.serviceName, sender.hashCode());
						cb_conn.onConnect(sender.hashCode());
						break;
					case Packet.RESPONSE_CLIENT_ALIVE:
						t = connset.get(sender.hashCode());
						t.item2 = ThreadEx.getCurrentTime();
						break;
					case Packet.REQUEST_SERVICE_DATA:
						//need to implement
						break;
					case Packet.REQUEST_PING:
						break;
					default:
						cb_recv.onReceive(p, sender.hashCode());
						break;
					}
				}
			}
			
		});		
	}

	/**
	 * 현재 컬렉션에 있는 각 Transceiver들을 열거하며 연결 점검용 패킷을 보내기 위한 작업 스레드를 생성한다.
	 * 열거도중 패킷이 마지막으로 응답한 시간과 현재 시간의 차이가 Timeout을 초과하면 dropConnection 호출.
	 */
	protected void beginCheckingConnection(){
		
		ThreadEx.invoke(null, new ActionEx() {
			
			@Override
			public void work(Object arg) {
				Queue<Integer> expired = new LinkedList<Integer>();
				Packet p = new Packet();
				p.signiture = Packet.REQUEST_CLIENT_ALIVE;
				
				while(isRunning){
					ThreadEx.sleep(config.CheckingPeriod);
					if(!isRunning)
						break;
					
					if(Constants.isDebug)
						Constants.logger.log("(debug:server) checking connection.\n");
					
					Iterator<Integer> h = connset.keySet().iterator();
					while(h.hasNext()){
						Integer connid = h.next();
						Tuple<Transceiver, Long> t = connset.get(connid);
						long elapsed = ThreadEx.getCurrentTime() - t.item2;
						if(elapsed > config.Timeout)
							expired.offer(connid);
						else
							t.item1.send(p);
					}
					
					while(expired.size() > 0)
					{
						Constants.logger.log("(debug:server) removing expired connection.\n");
						connset.remove(expired.poll());
					}
				}
			}
			
		});
		
	}
	
	//Ping 응답 서비스 스레드 시작.
	protected void beginListeningMulti(){
		final int bufsize = 0x1000;

		listener_multi = NetworkUtils.createMutlicastSocket(Constants.Multicast_IP, Constants.Multicast_Port_Send);
		
		ThreadEx.invoke(null, new ActionEx() {			
			@Override
			public void work(Object arg) {	
				byte[] buf = new byte[bufsize];
				Serializer se = new Serializer();				
				Packet pkt = new Packet();
				
				while(isRunning){
					DatagramPacket rawp;
					try {
						rawp = new DatagramPacket(buf, buf.length);
						listener_multi.receive(rawp);
					} catch (Exception ex) {
						continue;
					}
					
					int len = rawp.getLength();
					//Constants.logger.log("packet size" + len);
						
					ByteArrayInputStream in = new ByteArrayInputStream(buf, 0, len);
					pkt.clear();
					se.deserialize(in, pkt);
					String ip = (String)pkt.pop();
					int port = (Integer)pkt.pop();
					
					Transceiver t = new Transceiver(new InetSocketAddress(ip, port));
					pkt.clear();
					pkt.signiture = Packet.RESPONSE_PING;
					pkt.push(NetworkUtils.getLocalIP());
					pkt.push(config.Port);
					pkt.push(config.serviceName);
					t.send(pkt);
					t.dispose();
					
					if(Constants.isDebug)
						Constants.logger.log("(debug:server) ping responsed to " + ip + " : " + port + ".\n");
				}	
				
			}
		});
	}
	
	/**
	 * 특정 Transceiver에게 이 서비스의 이름을 전송한다.
	 * @param name
	 * 서비스의 이름
	 * @param connid
	 * Transceiver 객체를 가르키는 id
	 * @throws IllegalArgumentException
	 * String NULL이거나 ,connid가 유효하지 않은 경우 발생
	 */
	protected void sendServiceName(String name, int connid) throws IllegalArgumentException{
		if(name == null) 
			throw new IllegalArgumentException("argument name should not be null.");
		if(!connset.containsKey(connid))
			throw new IllegalArgumentException("connid is not valid.");
		
		Packet p = new Packet();
		p.signiture = Packet.RESPONSE_SERVICE_NAME;
		p.push(name);
		
		Tuple<Transceiver, Long> t = connset.get(connid);
		t.item1.send(p);
	}

}
