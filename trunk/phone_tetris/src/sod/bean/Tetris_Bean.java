package sod.bean;

import java.io.IOException;

import org.json.JSONArray;

import sod.beanImpl.ServiceBeanImpl;
import sod.common.Packet;
import sod.service.ConnectionBean;

public class Tetris_Bean  implements ServiceBeanImpl{

	public static final int UP = 100;
	public static final int DOWN = 200;
	public static final int LEFT = 300;
	public static final int RIGHT = 400;
	@Override
	public void ReceiveData() throws IOException {
		// TODO Auto-generated method stub
	
		
		//receiveData plugin 정의
		//   한줄 , 4줄 소거 작업은여기서
		
	}

	@Override
	public void SendData(String data) throws IOException {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void SendData(Packet pkt, JSONArray data) throws Exception {
		// TODO Auto-generated method stub
		String temp=data.toString();
		if(Integer.parseInt(temp)==UP)pkt.push(""+UP);
		else if(Integer.parseInt(temp)==DOWN)pkt.push(""+DOWN);
		else if(Integer.parseInt(temp)==LEFT)pkt.push(""+LEFT);
		else {pkt.push(""+RIGHT);}
		ConnectionBean.client.send(pkt);
	}

}
