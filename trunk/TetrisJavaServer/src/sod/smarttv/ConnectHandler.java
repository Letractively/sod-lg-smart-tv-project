package sod.smarttv;

public interface ConnectHandler {
	/**
	 * 연결되었을때 호출되는 콜백함수.
	 * @param connid
	 * Transceiver 객체를 가르키는 id
	 */
	void onConnect(int connid);
}
