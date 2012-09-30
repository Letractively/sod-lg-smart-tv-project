package com.sgsong.Net;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiSubService {

	static WifiManager.MulticastLock mlock;
	static Context context;
	//Activity act;

	static public void  setContext(Context in){
		context = in;
	}
	
	static protected WifiManager getWifiManager() {

		return (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		
	}

	static public void unLockMulticast(){
		mlock = getWifiManager().createMulticastLock("test_mlock");
		mlock.setReferenceCounted(true);
		mlock.acquire();
	}

	static public String getLocalIpAddress() {
		// need to
		// <uses-permission
		// android:name="android.permission.ACCESS_WIFI_STATE"/>
		WifiManager wifiManager = getWifiManager();
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

	static protected final byte[] int2byte(int i) {
		byte[] dest = new byte[4];
		dest[3] = (byte) (i & 0xff);
		dest[2] = (byte) (i >> 8 & 0xff);
		dest[1] = (byte) (i >> 16 & 0xff);
		dest[0] = (byte) (i >> 24 & 0xff);

		return dest;
	}

}


