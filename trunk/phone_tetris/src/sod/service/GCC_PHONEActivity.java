package sod.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.Semaphore;

import sod.bean.AnA_Bean;
import sod.common.ActionEx;
import sod.common.Packet;
import sod.common.ReceiveHandler;
import sod.common.Storage;
import sod.common.StorageFile;
import sod.smartphone.AccessManager;
import sod.smartphone.ServerInfo;

import com.phonegap.*;

import sod.service.R;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;

public class GCC_PHONEActivity extends DroidGap {
	/** Called when the activity is first created. */
	Handler startHandler;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.servicedownload);
		startHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				InitHTML();
			}
		};
		
		//Client_Initalize(); // 나중에 주석 처리 해제 해야함
		InitHTML();
	}
	public void InitHTML() {
		super.loadUrl("file:///android_asset/www/Contorller.html");
		//나중에 다운로드 되게 해야함
		/* 
		String serviceName = ConnectionBean.ServerInfomation.ServiceName;
		String servicePath = null;
		String indexHtmlPath = null;
		try {
			Storage downloadedService = Storage.getStorage(serviceName
					+ "/service");// "A&A_Service/service"
			servicePath = downloadedService.getSODrootPath();
			StorageFile indexHtmlPathStorageFile = downloadedService.openFile(
					"indexHtmlPath.ini", StorageFile.READ);

			byte[] buf = new byte[indexHtmlPathStorageFile.getLength()];
			indexHtmlPathStorageFile.read(buf);
			indexHtmlPath = new String(buf);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String loadUrlPath ="file:///"+ servicePath + "/AnA.html";
		String loadUrlPath = "file:///" + servicePath + indexHtmlPath;
		super.loadUrl(loadUrlPath);
		*/
		
	}
	public void Client_Initalize() {
		ConnectionBean.client = new AccessManager();
		// 이 부분은 TVServerLisrtActivity 에서 서버검색 후 하기 때문에
		// 주석처리
		ConnectionBean.ServerInfomation.EndPoint = new InetSocketAddress(
				ConnectionBean.SERVERIP, ConnectionBean.SERVERPORT);
		ConnectionBean.client.setReceiveHandler(new ReceiveHandler() { // client
																		// ReceiveHandler//
																		// 클라이언트
																		// 리시브
																		// 핸들러

					public void onReceive(Packet pkt) {
						
					}
				});
		
		
		StrictMode.enableDefaults(); // stack default // 스택영역 디폴트 초기화
		ConnectionBean.client.setStartServiceDelegate(new ActionEx() { // 서비스
																		// 시작핸들러
					public void work(Object arg) {
						// TODO Auto-generated method stub
						// 핸들러로 보내기
						startHandler.sendMessage(Message.obtain());
					}
				});
		ConnectionBean.client.connect(ConnectionBean.ServerInfomation); // 접속할
																		// 서버에
																		// 연결//
																		// connection
																		// Server

	}
}
