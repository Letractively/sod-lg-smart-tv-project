package sod.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * 
 * @author MB
 * Make a connection between remote point
 */
public class Transceiver implements Disposable {
	
	public final static int TransferUnit = 0x10000;//64kb
	
	byte[] recvbuf;
	Serializer serializer;
	SocketAddress dest;
	DatagramSocket conn = null;
	
	/**
	 * 포트를 자동할당하여 Transceiver객체 생성
	 * dest는 null이 될 수 있음(수신 전용 Transceiver의 경우)
	 * @param dest 목적지 주소
	 */
	public Transceiver(SocketAddress dest){
		try {
			this.dest = dest;
			conn = new DatagramSocket(0);			
			serializer = new Serializer();
			recvbuf = new byte[TransferUnit];
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * dest는 null이 될 수 있음(수신 전용 Transceiver의 경우)
	 * @param dest 목적지 주소
	 * @param recvport 수신하는 포트 (자동 할당하려면 0 지정)
	 */
	public Transceiver(SocketAddress dest, int recvport){
		try {
			this.dest = dest;
			conn = new DatagramSocket(recvport);			
			serializer = new Serializer();
			recvbuf = new byte[TransferUnit];
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void dispose(){
		conn.close();
		conn = null;
	}
	
	@Override
	protected void finalize() throws Throwable {
		if(conn != null)
			dispose();
	};
	
	/**
	 * 넘겨준 패킷 객체를 상대(스마트TV, 스마트폰)에게 보낸다.
	 * @param p
	 * @throws IllegalArgumentException
	 * 넘어온 패킷 객체가 NULL일 때, IllegalArgumentException을 던진다.
	 * @throws SocketException
	 * 전송하는 과정이 실패하면 SocketException을 던진다.
	 */
	public boolean send(Packet p) throws IllegalArgumentException{
		if (p == null)
			throw new IllegalArgumentException();

		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		serializer.serialize(buf, p);
		byte[] data = buf.toByteArray();
		DatagramPacket pkt = new DatagramPacket(data, data.length);
		pkt.setSocketAddress(dest);
		try {
			conn.send(pkt);
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 상대방(스마트TV, 스마트폰)으로부터 패킷을 수신하는 메소드이다. (Blocking으로 동작)
	 * @param p
	 * 상대방(스마트TV, 스마트폰)으로부터 받은 패킷
	 * @throws IllegalArgumentException
	 * 넘어온 패킷 객체가 NULL일 때, IllegalArgumentException을 던진다.
	 */
	public InetSocketAddress receive(Packet p) throws IllegalArgumentException{
		if (p == null)
			throw new IllegalArgumentException();

		try {
			DatagramPacket pkt = new DatagramPacket(recvbuf, recvbuf.length);
			conn.receive(pkt);
			int length = pkt.getLength();
			ByteArrayInputStream in = new ByteArrayInputStream(recvbuf, 0, length);
			serializer.deserialize(in, p);
			return (InetSocketAddress)pkt.getSocketAddress();
		} catch (IOException e) {
			return null;
		}
	}
}
