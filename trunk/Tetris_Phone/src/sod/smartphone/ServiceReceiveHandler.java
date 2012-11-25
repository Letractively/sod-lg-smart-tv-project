package sod.smartphone;

public interface ServiceReceiveHandler {
	/**
	 * 서비스 데이터를 수신했을때 호출되는 콜백함수.
	 * @param serviceName
	 * 서비스의 이름
	 */
	abstract void onReceive(String serviceName);
	
}
