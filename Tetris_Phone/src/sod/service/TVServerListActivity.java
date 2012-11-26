package sod.service;

import java.util.ArrayList;

import sod.common.ActionEx;
import sod.common.NetworkUtils;
import sod.common.ThreadEx;
import sod.smartphone.AccessManager;
import sod.smartphone.SearchCallBack;
import sod.smartphone.ServerInfo;

import android.app.ListActivity;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SlidingDrawer;

public class TVServerListActivity extends ListActivity {

	final static int port = ConnectionBean.SERVERPORT;
	final static int TurnAroundWaitTime = 5000;

	private ArrayList<String> list;
	private ArrayAdapter<String> adapter;

	Handler onSearchHandler;
	Handler visibleProgressHandler;
	Handler inVisibleProgressHandler;
	
	ServerInfo info;
		
	ProgressBar progressBar;
	ImageButton searchButton;
	
	boolean ibFlag = false;
	final static boolean enable = true;
	final static boolean disable = false;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tvserverlist);
		progressBar = (ProgressBar)findViewById(R.id.inProgress);
		
		NetworkUtils.setLocalIp(getLocalIpAddress());// 

		list = new ArrayList<String>();

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);
		setListAdapter(adapter);

		// Log.i("jaeyeong","jaeyeong start");

		onSearchHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				info = (ServerInfo) msg.obj;
				list.add(info.EndPoint.getAddress().getHostAddress() + ","
						+ info.ServiceName);
				adapter.notifyDataSetChanged();
			}
		};
		
		visibleProgressHandler = new Handler(){
			
			@Override
			public void handleMessage(Message msg) {
				progressBar.setVisibility(ProgressBar.VISIBLE);
				list.clear();
				ibFlag = false;
			}//end handleMessage....
		};
		
		inVisibleProgressHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				progressBar.setVisibility(ProgressBar.INVISIBLE);
				ibFlag = true;
				adapter.notifyDataSetChanged();
			}//end handleMessage....
		};
		
	
		searchServer();
				
		searchButton =(ImageButton)findViewById(R.id.searchButton);
		searchButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(ibFlag)
					searchServer();
			}
		});

		// list.add("192.168.0.11,GCC"); //test용 코드

		adapter.notifyDataSetChanged();
		// /////////////////////////////////////////////////////////
		progressControl();
	}

	// //////////////////////////////////////////////////////////////////////////////////////
	public String getLocalIpAddress() {
		// need to
		// <uses-permission
		// android:name="android.permission.ACCESS_WIFI_STATE"/>
		WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ipAddress = wifiInfo.getIpAddress();

		byte[] bytes = int2byte(ipAddress);
		int[] values = new int[4];

		for (int i = 0; i < 4; i++)
			values[i] = bytes[i] & 0xFF;

		String ipStr = String.format("%d.%d.%d.%d", values[3], values[2],
				values[1], values[0]);

		return ipStr;

	}

	final byte[] int2byte(int i) {
		byte[] dest = new byte[4];
		dest[3] = (byte) (i & 0xff);
		dest[2] = (byte) (i >> 8 & 0xff);
		dest[1] = (byte) (i >> 16 & 0xff);
		dest[0] = (byte) (i >> 24 & 0xff);

		return dest;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		String[] strs = adapter.getItem(position).split(",");
		String ip = strs[0];
		String serviceName = strs[1];
		ConnectionBean.SERVERIP = ip;
		Intent intent = new Intent(TVServerListActivity.this,
				GCC_PHONEActivity.class);
		intent.putExtra("serviceName", serviceName);
		
		ConnectionBean.ServerInfomation = info;
		startActivity(intent);

	}

	protected void searchServer() {

		ThreadEx.invoke(null, new ActionEx() {

			@Override
			public void work(Object arg) {
				// TODO Auto-generated method stub
				// String localip = NetworkUtils.getLocalIP();
				progressControl();
				
				String localip = getLocalIpAddress();
				Log.i("jaeyeong", localip);
				AccessManager.searchServer(localip, new SearchCallBack() {

					@Override
					public void onSearch(ServerInfo info) {
						// TODO Auto-generated method stub
						if (info == null) {

						} else {
							// 핸들러 이용해서 보내야하나??
							Log.i("jaeyeong", "jaeyeong" + "OnSearch");
							Log.i("jaeyeong", info.EndPoint.getAddress()
									.getHostAddress());
							Message msg = onSearchHandler.obtainMessage();
							msg.obj = info;
							onSearchHandler.sendMessage(msg);
						}
					}
				});			
			}
		});
	}
	
	protected void progressControl(){
		ThreadEx.invoke(null, new ActionEx() {

			@Override
			public void work(Object arg) {
				// TODO Auto-generated method stub
				visibleProgressHandler.sendMessage(new Message());
				try {
					Thread.sleep(TurnAroundWaitTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				inVisibleProgressHandler.sendMessage(new Message());
			}
			
		});
	}
}