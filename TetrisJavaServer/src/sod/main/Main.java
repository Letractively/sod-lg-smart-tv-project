package sod.main;

import java.io.IOException;
import java.net.InetSocketAddress;

import sod.common.ConsoleLogger;
import sod.common.Logable;
import sod.common.NetworkUtils;
import sod.common.Packet;
import sod.common.Transceiver;
import sod.smarttv.AccessManagerServer;
import sod.smarttv.ConnectHandler;
import sod.smarttv.DisconnectHandler;
import sod.smarttv.ServerConfig;
import sod.smarttv.ServerReceiveHandler;

public class Main {

	final static int ServerPort = 30331;
	static AccessManagerServer server;
	static Logable logger;
	static {
		logger = ConsoleLogger.getInstance();
	}
	
	public static void waitfor() {
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		server = new AccessManagerServer();
		ServerConfig conf = new ServerConfig();
		conf.Port = ServerPort;
		conf.serviceName = "LgGccTestService";

		server.setConnectHandler(new ConnectHandler() {
			@Override
			public void onConnect(int connid) {
				logger.log("(server): new connection is accepted - " + connid
						+ "\n");
			}
		});
		
		server.setDisconnectHandler(new DisconnectHandler() {

			@Override
			public void onDisconnect(int connid) {
				logger.log("(server): client is disconnected - " + connid
						+ "\n");
			}
		});
		
		server.setReceiveHandler(new ServerReceiveHandler() {

			Transceiver trans= new Transceiver(new InetSocketAddress("127.168.0.19", 2013));
			@Override
			public void onReceive(Packet pkt, int connid) {
				logger.log("스루패스\n");
			//	server.send(pkt, connid);
				trans.send(pkt);	

			}
		});

		server.start(conf);

		// now we are looking for server.
		String localip = NetworkUtils.getLocalIP();
		logger.log("localip = " + localip + "\n");

		

		logger.log("press enter to finish.\n");
		waitfor();

		server.shutdown();

	}

}
