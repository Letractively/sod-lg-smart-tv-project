package sod.common;

public class Constants {
	public static final boolean isDebug = true;
	
	public static final int Default_CheckingPeriod = 4000;
	public static final int Default_Timeout = 30000;
	
	public static final String Multicast_IP = "224.0.0.1";
	public static final int Multicast_Port_Send = 37401;
	public static final int Multicast_Port_Response = 37402;

	public static final Logable logger = ConsoleLogger.getInstance();
	
}
