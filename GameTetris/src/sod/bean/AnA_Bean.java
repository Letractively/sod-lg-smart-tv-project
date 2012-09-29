package sod.bean;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import org.json.JSONArray;

import sod.beanImpl.ServiceBeanImpl;
import sod.common.Constants;
import sod.common.Packet;
import sod.service.ConnectionBean;

public class AnA_Bean implements ServiceBeanImpl {
	
	Packet packet;
	private final String ANSWERDATA="answerData";
	public static String Message="";
	public final static String DOWNLOADHTML="download";
	public final static String QUSTION="Questionnaire";
	public static int ClientId;
	public static String TempMessage="";
	public static Semaphore waithandle;
	
	static{
		waithandle = new Semaphore(1);
		try {
			waithandle.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void ReceiveData() throws IOException {
		packet = new Packet();
		packet.push(DOWNLOADHTML);
		ConnectionBean.client.send(packet);
		
	}

	@Override
	public void SendData(String data) throws IOException {
		// TODO Auto-generated method stub
		packet = new Packet();
		packet.push(QUSTION);
		packet.push(data);		
		ConnectionBean.client.send(packet);
		
		Constants.logger.log("(debug:client) Succeed and disconnet");
		ConnectionBean.client.dispose();
	}

	@Override
	public void SendData(Packet pkt,JSONArray data) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
