package com.sgsong.Net;

import java.net.InetSocketAddress;

import android.util.Log;

import com.sgsong.Game.GameMain;

import sod.common.NetworkUtils;
import sod.common.Packet;
import sod.common.Transceiver;
import sod.smarttv.AccessManagerServer;
import sod.smarttv.ConnectHandler;
import sod.smarttv.DisconnectHandler;
import sod.smarttv.ServerConfig;
import sod.smarttv.ServerReceiveHandler;

public class Networking {
	private static Packet packet;
	private static int PushCard;
	public static GameMain GM;
	
	
	public GameMain getGM() {
		return GM;
	}
	public static void setGM(GameMain gM) {
		GM = gM;
	}
	public static Packet getPacket() {
		return packet;
	}
	public static void setPacket(Packet packet) {
		Networking.packet = packet;
	}
	public void TVServerIni() { // Tv initalize
		
		ConnectionBean.server = new AccessManagerServer();
		ConnectionBean.ServerConfig = new ServerConfig();
		ConnectionBean.ServerConfig.Timeout = 100000;
		ConnectionBean.ServerConfig.Port = ConnectionBean.SERVERPORT;
		ConnectionBean.ServerConfig.CheckingPeriod = 4000;
		ConnectionBean.ServerConfig.serviceName = "gcc";

		ConnectionBean.server.setConnectHandler(new ConnectHandler() {

			public void onConnect(int connid) {
				ConnectionBean.ClientId=connid;
			}
		});
		ConnectionBean.server.setDisconnectHandler(new DisconnectHandler() {

			public void onDisconnect(int connid) {
				// Disconnection handler
			}
		});
		
		ConnectionBean.server.setReceiveHandler(new ServerReceiveHandler() {
			Transceiver trans= new Transceiver(new InetSocketAddress("192.168.0.19", 2013));
			public void onReceive(Packet pkt, int connid) {
			//	logger.log("(server): 스루패스");
				
				Log.i("재영","스루패스");
				trans.send(pkt);
				
			}

		});

	}

	public String SignCase(Object sign) {
		return sign.toString();
	}
}
