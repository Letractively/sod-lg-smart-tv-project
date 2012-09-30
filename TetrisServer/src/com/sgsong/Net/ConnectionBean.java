package com.sgsong.Net;

import sod.smarttv.AccessManagerServer;
import sod.smarttv.ServerConfig;

public class ConnectionBean {
	public static final int SERVERPORT = 30331;		// Server port // 서버 포트 
	
	public static String Message;			// Data Message// 데이터 메세지
	public static AccessManagerServer server; // server 
	public static ServerConfig ServerConfig; // Server imfomation// 서버정보
	public static int ClientId;			// client identify Id // 클라이언트 식별아이디
	public static boolean OpenSigniture=false;
}
