package sod.bean;

import java.io.IOException;

import org.json.JSONArray;

import com.phonegap.api.PluginResult;

import sod.beanImpl.ServiceBeanImpl;
import sod.common.Packet;
import sod.service.ConnectionBean;

public class GCC_Bean implements ServiceBeanImpl {
	 public static String DataSign="";
		
	@Override
	public void ReceiveData() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SendData(String data) throws IOException {
		// TODO Auto-generated method stub
		
	}
	public String getDataSign(JSONArray arg1)throws Exception
	{
		this.DataSign=arg1.getString(0);
		return DataSign;
	}
	@Override
	public void SendData(Packet pkt, JSONArray data) throws Exception {
		// TODO Auto-generated method stub
		 pkt.push("SendCard");
		 pkt.push(getDataSign(data));
		 ConnectionBean.client.send(pkt);
		
	}

}
