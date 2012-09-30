package com.sgsong.main;

import sod.common.ActionEx;
import sod.common.NetworkUtils;
import sod.common.ThreadEx;
import sod.smarttv.AccessManagerServer;
import sod.smarttv.ServerConfig;

import com.sgsong.Net.ConnectionBean;
import com.sgsong.Net.Networking;
import com.sgsong.Net.WifiSubService;

import android.app.Activity;
import android.os.Bundle;

public class controller extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.controller);
		WifiSubService.setContext(this);

		ThreadEx.invoke(null, new ActionEx() {
			
			@Override
			public void work(Object arg) {
				// TODO Auto-generated method stub
				ConnectionBean.server = new AccessManagerServer();
				ConnectionBean.ServerConfig = new ServerConfig();
				ConnectionBean.ServerConfig.Timeout = 30000;
				ConnectionBean.ServerConfig.Port = ConnectionBean.SERVERPORT;
				ConnectionBean.ServerConfig.CheckingPeriod = 4000;
				ConnectionBean.ServerConfig.serviceName = "gcc";

				
			
				// 0. Run.java ���� ��Ƽ��Ƽ�� context ������ �̸� �����ߴ�. ( Run.java 64 Line)
				// 1. �ڱ� �ڽ��� Ip�� �˾ƿͼ� �����Ѵ�./////////////////
				NetworkUtils.setLocalIp(WifiSubService.getLocalIpAddress());
				new Networking().TVServerIni();
				// 2.��Ƽĳ��Ʈ ���� �����Ѵ�. (�˻������ ����)
				WifiSubService.unLockMulticast();
				// 3. ���� ����
				ConnectionBean.server.start(ConnectionBean.ServerConfig);
			}
			
		} );
		
		

		// TODO Auto-generated method stub
	}

}
