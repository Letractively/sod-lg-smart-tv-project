package sod.smartphone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream.GetField;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import sod.common.ActionEx;
import sod.common.Constants;
import sod.common.Disposable;
import sod.common.NetworkUtils;
import sod.common.Packet;
import sod.common.ReceiveHandler;
import sod.common.Serializer;
import sod.common.ThreadEx;
import sod.common.Transceiver;

/**
 * 
 * @author MB
 *
 */
public class AccessManager implements Disposable {
	
	ServerInfo svinfo;
	Transceiver conn;
	ReceiveHandler cb;
	
	ServiceManager serviceManager;
	
	ActionEx startServiceDelegate;
	
	//debug
	int downloadCnt = 0;
	
	//listening loop 제어용
	boolean isRunning = false;
	
	//서버로부터 인증을 받았는지 여부
	boolean isConnected = false;
	
	public AccessManager(){
		serviceManager = new ServiceManager();
	}
	/**
	 * 서버와 연결을 시도한다.
	 * @param info
	 * 접속할 서버의 정보
	 */
	public void connect(ServerInfo info){
		this.svinfo = info;
		conn = new Transceiver(info.EndPoint);
		
		Packet p = new Packet();
		p.signiture = Packet.REQUEST_ACCEPT;
 		conn.send(p);
		isRunning = true;
		beginListening();
		
	}
	
	
	/**
	 * 같은 와이파이에 있는 서버를 찾아 목록을 반환
	 * 콜백으로 넘겨주는 인자가 null이면 탐색의 마지막에 도달함을 의미함.
	 * @param baseIP ip정보
	 * @param cb  검색 콜백 함수
	 */
	public static void searchServer(String baseIP, SearchCallBack cb){		
		final int TurnAroundWaitTime = 4000;
		
		MulticastSocket s = NetworkUtils.createMutlicastSocket(Constants.Multicast_IP, Constants.Multicast_Port_Send);
		Transceiver t = null;
		try {
			Serializer se = new Serializer();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			Packet p = new Packet();
			p.signiture = Packet.REQUEST_PING;
			p.push(NetworkUtils.getLocalIP());
			p.push(Constants.Multicast_Port_Response);

			se.serialize(output, p);
			byte[] buf = output.toByteArray();
			DatagramPacket rawp = new DatagramPacket(buf, buf.length,
					NetworkUtils.getMulticastAddr(),
					Constants.Multicast_Port_Send);
			s.send(rawp);
			s.close();
			t = new Transceiver(null, Constants.Multicast_Port_Response);
			
			ThreadEx.invoke(new Object[]{cb, t}, new ActionEx() {				
				@Override
				public void work(Object arg) {
					Object[] args = (Object[])arg;
					SearchCallBack _cb = (SearchCallBack)args[0];
					Transceiver _t = (Transceiver)args[1];
					
					while(true){
						try {				
							Packet p = new Packet();
							Object rv = _t.receive(p);
							if(rv == null) return;							
							
							ServerInfo info = new ServerInfo();
							String _ip = (String)p.pop();
							int _port = (Integer)p.pop();
							String _service = (String)p.pop();
							info.EndPoint = new InetSocketAddress(_ip, _port);
							info.ServiceName = _service;
							_cb.onSearch(info);
						} catch (Exception ex) {
							ex.printStackTrace();
							return;
						}
					}
				}
			});
			ThreadEx.sleep(TurnAroundWaitTime);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(t != null)
				t.dispose();
			cb.onSearch(null);
		}
	}

	/**
	 * 수신하는 작업 스레드를 종료하고 할당된 모든 리소스를 반환한다. (접속은 자동으로 종료됨)
	 * terminate listening thread and release resources.
	 */
	public void dispose(){
		isRunning = false;
		conn.dispose();
	}

	/**
	 * Packet이 수신됐을때 호출될 콜백함수를 등록
	 * @param handler
	 * 콜백함수를 구현한 객체
	 */
	public void setReceiveHandler(ReceiveHandler handler){
		cb = handler;
	}
	
	/**
	 * 서비스를 시작할 때 실행할 함수를 등록한다.
	 * @param handler
	 * 콜백함수를 구현한 객체
	 */
	public void setStartServiceDelegate(ActionEx startAction){
		this.startServiceDelegate = startAction;
	}

	/**
	 * 패킷을 서버로 보낸다.
	 * @param pkt
	 * 보낼 데이터를 담은 패킷
	 */
	public boolean send(Packet pkt){
		if(pkt == null) return false;
		return conn.send(pkt);
	}
	
	/**
	 * 클라이언트가 현재 서버에 접속되있는 여부를 알려주는 메소드
	 * @return 접속된 상태면 true, 아니면 false
	 */
	public boolean isConnected(){
		return isConnected;
	}

	/**
	 * 서버로 부터 수신데이터를 확인하고 서버의 확인에 응답할 작업 스레드를 생성한다.
	 */
	protected void beginListening(){
		ThreadEx.invoke(null, new ActionEx() {		
			
			@Override
			public void work(Object arg) {
				InetSocketAddress sender = null;
				Packet p = new Packet();
				Packet p_check = new Packet();
				p_check.signiture = Packet.RESPONSE_CLIENT_ALIVE;

				while(isRunning){
					p.clear();
					sender = conn.receive(p);
					if(sender == null) continue;
					
					switch(p.signiture){
					case Packet.RESPONSE_ACCEPT:
						isConnected = true;
						
						Constants.logger.log("(debug:client) RESPONSE_ACCEPT.\n");
						//1. 서비스가 있는지 없는지 확인한다.
						if(isExistService()){
							Constants.logger.log("(debug:client) START SERVICE.\n");
							startService();   //나중에 주석 없애야함
						}
						else{
							Constants.logger.log("(debug:client) REQUEST_SERVICE_DATA.\n");
							p.signiture= Packet.REQUEST_SERVICE_DATA;
							conn.send(p);
						}
						break;
					case Packet.REQUEST_CLIENT_ALIVE:
						conn.send(p_check);
						break;
					case Packet.RESPONSE_SERVICE_DATA:
						Constants.logger.log("(debug:client) installService");
						
						serviceManager.installService(p);

						break;

					case Packet.RESPONSE_SERVICE_DATA_END:
						Constants.logger.log("(debug:client) Service Install complete");
						startService();    //나중에 주석 없애야함
						break;
					default:
						cb.onReceive(p);
						break;
					}
				}
			}
			
		});		
	}
	
	/**
	 * 다운로드 받은 서비스가 있는지 확인하는 메소드
	 * @return 다운로드 받은 서비스가 있으면 true, 아니면 false
	 */
	protected boolean isExistService(){
		return serviceManager.checkServiceInstalled(svinfo.ServiceName);
		
	}
	
	/**
	 * startServiceDelegate에 등록한 콜백함수를 실행한다.
	 */
	protected void startService(){
		startServiceDelegate.work(svinfo.ServiceName);
	}

}

