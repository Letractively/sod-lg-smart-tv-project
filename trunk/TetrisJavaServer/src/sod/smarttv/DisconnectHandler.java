package sod.smarttv;

public interface DisconnectHandler {
	/**
	 * 연결이 끊겼을때 호출되는 콜백함수.
	 * @param connid
	 * Transceiver 객체를 가르키는 id
	 */
	abstract void onDisconnect(int connid);
}
