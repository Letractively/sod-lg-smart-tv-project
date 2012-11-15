package sod.common;

public class ServicePacketOrder {

	int order;
	int flag_order;
	
	public ServicePacketOrder(){
		order = 0;
		flag_order = -1;
	}
	
	public void initServicePacketOrder(){
		order = 0;
		flag_order = -1;
	}
	
	public void increaseOrder(){
		order++;
		flag_order++;
	}
	
	public int getOrder(){
		return order;
	}
	
	public boolean isCorrectOrder(int number){
		
		boolean returnData;
		if(flag_order == (number-1))
			returnData = true;
		else
			returnData = false;
		
		return returnData;
	}
}
