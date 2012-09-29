package sod.common;

/**
 * 
 * @author MB
 *
 */
public interface ReceiveHandler {
	
	/**
	 * AccessManagerServer/AccessManager의 beginListenning에 의해 실행된 스레드에 의해 호출되는 메소드로 프레임워크를 이용하는 소프트웨어에게 수신된 패킷을 전달한다.
	 * 인터페이스이므로 사용자가 사용하기위해서는 꼭 구현되어야한다.
	 * @param pkt
	 * 프레임워크를 이용하는 소프트웨어에게 넘겨줄 수신한 패킷
	 */
	void onReceive(Packet pkt);
}
