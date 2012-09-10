package sod.common;

public class Constants {
	public static final boolean isDebug = true;
	
	public static final int Default_CheckingPeriod = 4000;
	public static final int Default_Timeout = 30000;
	
	public static final String Multicast_IP = "224.0.0.1";
	public static final int Multicast_Port = 6789;
	public static final int Multicast_Port_Response = 6790;

	public static final Logable logger = ConsoleLogger.getInstance();
	
}
