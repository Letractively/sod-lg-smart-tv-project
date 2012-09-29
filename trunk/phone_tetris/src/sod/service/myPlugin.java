package sod.service;

import java.net.InetSocketAddress;
import java.util.concurrent.Semaphore;

import org.json.JSONArray;
import org.json.JSONException;

import sod.bean.AnA_Bean;
import sod.bean.GCC_Bean;
import sod.bean.Tetris_Bean;
import sod.common.ActionEx;
import sod.common.Packet;
import sod.common.ReceiveHandler;
import sod.smartphone.AccessManager;
import sod.smartphone.ServerInfo;

import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.webkit.WebSettings.PluginState;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;

public class myPlugin extends Plugin {

	public PluginResult execute(String action, JSONArray data, String callbackId) {
		// TODO Auto-generated method stub
			Tetris_Bean service_tetris = new Tetris_Bean();
			while (true) {
				if (action.equals("receiveData")) {
					try {
						service_tetris.ReceiveData();
					} catch (Exception e) {
						return new PluginResult(PluginResult.Status.OK,
								"리시브데이타 실패");
					}
					return new PluginResult(PluginResult.Status.OK,
							 "");

				} else if (action.equals("sendData")) {				// 이부분이버튼 클릭 하면 UP DOWN LEFT DOWN 으로 날아가는 부분
					Packet pkt = new Packet();
					String temp="";
					try {
						
					
						temp= data.getString(0);
						
						pkt.push(temp);
						ConnectionBean.client.send(pkt);
						
					
						
						
						System.out.println(data.toString());
					} catch (Exception e) {
						return new PluginResult(PluginResult.Status.OK,
								"플러그인 에러");
					}
					return new PluginResult(PluginResult.Status.OK,temp);
				} else
					return new PluginResult(PluginResult.Status.OK, "플러그인 실패");
			}
			
		}

		
//	}

}
