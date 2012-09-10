package sod.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Iterator;

import sod.smartphone.AccessManager;
import sod.smartphone.SearchCallBack;
import sod.smartphone.ServerInfo;
import sod.smarttv.AccessManagerServer;
import sod.smarttv.ConnectHandler;
import sod.smarttv.DisconnectHandler;
import sod.smarttv.ServerConfig;
import sod.smarttv.ServerReceiveHandler;

import sod.common.*;

public class Test {
	final static String ServerIP = "127.0.0.1";
	final static int ServerPort = 6789; 
	
	static Logable logger;
	static AccessManagerServer server; 
	static AccessManager client;
	static{
		logger = ConsoleLogger.getInstance();
	}
	
	public static void waitfor(){
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testServerSearch(){		
		//server setup
		server = new AccessManagerServer();
		ServerConfig conf = new ServerConfig();
		conf.Port = ServerPort;
		conf.serviceName = "TestService";
		server.start(conf);		
		
		//now we are looking for server.
//		String localip = NetworkUtils.getLocalIP();
//		logger.log("localip = " + localip + "\n");		
//		
//		AccessManager.searchServer(localip, new SearchCallBack() {			
//			@Override
//			public void onSearch(ServerInfo info) {
//				if(info == null)
//				{
//					//end of search
//					logger.log("end of search.\n");
//				}
//				else
//				{
//					logger.log("found server: "
//							+ info.EndPoint.getAddress().getHostAddress()
//							+ ", "
//							+ info.ServiceName
//							+ "\n");
//				}
//			}
//		}); 
//
		logger.log("press enter to finish.\n");
		waitfor();
		
		server.shutdown();
	}
	
	public static void testServer(){
		
		//server side
		server = new AccessManagerServer();
		ServerConfig conf = new ServerConfig();
		conf.Timeout = 10000;
		conf.Port = ServerPort;
		conf.CheckingPeriod = 2000;
		conf.serviceName = "TestService";
		server.setConnectHandler(new ConnectHandler() {			
			@Override
			public void onConnect(int connid) {
				logger.log("(server): new connection is accepted - " + connid + "\n");				
			}
		});
		server.setDisconnectHandler(new DisconnectHandler() {
			
			@Override
			public void onDisconnect(int connid) {
				logger.log("(server): client is disconnected - " + connid + "\n");				
			}
		});
		server.setReceiveHandler(new ServerReceiveHandler() {			
			@Override
			public void onReceive(Packet pkt, int connid) {
				logger.log("(server): a packet from client - " + connid + "\n");
				server.send(pkt, connid);
			}
		});
		server.start(conf);
		
		//end of test		
		ThreadEx.sleep(1000);
		logger.log("press enter to terminate client.\n");
		waitfor();
		
		//wait until server drop client.
		logger.log("waiting until server drop client.\n");
		ThreadEx.sleep(11000);
		server.shutdown();
		
		logger.log("finishing test...\n");
	}
	
	public static void testClient(){

		//client side
		client = new AccessManager();
		ServerInfo svinfo = new ServerInfo();
		svinfo.EndPoint = new InetSocketAddress(ServerIP, ServerPort);
		client.setReceiveHandler(new ReceiveHandler() {			
			@Override
			public void onReceive(Packet pkt) {
				logger.log("(client): a packet from server - " + (String)pkt.pop() + "\n");
			}
		});

		client.connect(svinfo);
		
		Packet pkt = new Packet();
		pkt.signiture = 1;
		pkt.push("a string on packet");
		client.send(pkt);
		
		client.dispose();
	}
	
	public static void testAccessManager(){
		
		//server side
		server = new AccessManagerServer();
		ServerConfig conf = new ServerConfig();
		conf.Timeout = 10000;
		conf.Port = ServerPort;
		conf.CheckingPeriod = 2000;
		conf.serviceName = "TestService";
		server.setConnectHandler(new ConnectHandler() {			
			@Override
			public void onConnect(int connid) {
				logger.log("(server): new connection is accepted - " + connid + "\n");				
			}
		});
		server.setDisconnectHandler(new DisconnectHandler() {
			
			@Override
			public void onDisconnect(int connid) {
				logger.log("(server): client is disconnected - " + connid + "\n");				
			}
		});
		server.setReceiveHandler(new ServerReceiveHandler() {			
			@Override
			public void onReceive(Packet pkt, int connid) {
				logger.log("(server): a packet from client - " + connid + "\n");
				server.send(pkt, connid);
			}
		});
		server.start(conf);
		
		//client side
		client = new AccessManager();
		ServerInfo svinfo = new ServerInfo();
		svinfo.EndPoint = new InetSocketAddress(ServerIP, ServerPort);
		client.setReceiveHandler(new ReceiveHandler() {			
			@Override
			public void onReceive(Packet pkt) {
				logger.log("(client): a packet from server - " + (String)pkt.pop() + "\n");
			}
		});

		client.connect(svinfo);
		
		Packet pkt = new Packet();
		pkt.signiture = 1;
		pkt.push("a string on packet");
		client.send(pkt);
		
		//end of test		
		ThreadEx.sleep(1000);
		logger.log("press enter to terminate client.\n");
		waitfor();
		
		client.dispose();
		
		//wait until server drop client.
		logger.log("waiting until server drop client.\n");
		ThreadEx.sleep(11000);
		server.shutdown();
		
		logger.log("finishing test...\n");
	}
	
	public static void testTransceiver(){
		ThreadEx.invoke(null, new ActionEx() {			
			@Override
			public void work(Object arg) {
				logger.log("thread invoked\n");
				Transceiver t = new Transceiver(new InetSocketAddress(ServerIP, 0), ServerPort);
				Packet p = new Packet();
				InetSocketAddress sender = t.receive(p);
				logger.log(sender.getAddress().toString() + ":" + Integer.toString(sender.getPort()) + "\n");
				logger.log(p.pop());
				
				t.dispose();
			}
		});
		
		ThreadEx.sleep(1000);
		Transceiver t = new Transceiver(new InetSocketAddress(ServerIP, ServerPort));
		Packet p = new Packet();
		p.push("trasceiver test...\n");
		boolean check = t.send(p);
		logger.log(Boolean.toString(check)+"\n");
		logger.log("sent msg...\n");
		
		t.dispose();
		
		waitfor();
	}
	
	public static void testSerializer() {
		// TODO Auto-generated method stub
		Packet pkt = new Packet();
		Packet pkt2 = new Packet();
		ByteArrayInputStream input;
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try {
			int u;

			pkt.signiture = -1;
			pkt.push(100);
			//pkt.push(200L);
			pkt.push(1.0f);
			pkt.push(2.0);
			pkt.push("hello");

			byte[] bytes = new byte[0x10];
			for (u = 0; u < bytes.length; ++u)
				bytes[u] = (byte) u;
			pkt.push(bytes);

			for (u = 0; u < bytes.length; ++u)
				logger.log(bytes[u] + " ");
			logger.log("\n");

			Serializer se = new Serializer();
			se.serialize(output, pkt);
			logger.log("\n");

			input = new ByteArrayInputStream(output.toByteArray());
			se.deserialize(input, pkt2);

			logger.log(pkt.signiture);
			logger.log("\n");
			
			while (pkt2.getElementCount() > 0) {
				if (!pkt2.getTopElementType()
						.equals(Packet.DataType_ByteArray))
					logger.log(pkt2.pop() + "\n");
				else {
					byte[] result = (byte[]) pkt2.pop();
					for (u = 0; u < result.length; ++u)
						logger.log(result[u] + " ");
					logger.log("\n");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
