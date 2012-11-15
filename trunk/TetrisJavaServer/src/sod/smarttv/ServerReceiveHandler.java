package sod.smarttv;

import sod.common.Packet;

public interface ServerReceiveHandler {
	/**
	 * 패킷을 수신할떄 호출되는 콜백함수.
	 * @param pkt
	 * 수신된 데이터를 담은 패킷
	 * @param connid
	 * Transceiver 객체를 가르키는 id
	 */
	void onReceive(Packet pkt, int connid);
}
