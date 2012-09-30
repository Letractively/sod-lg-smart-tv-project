package sod.smartphone;

import java.net.SocketException;



/**
 * 
 * @author MB
 *
 */
public class TVLocationViewer {
	
	TVLocation[] tvLocations;
	AdminInformation [] admins;
	
	public TVLocationViewer(){
		
		admins = new AdminInformation[5];
		
		for(int i = 0 ; i<5 ; i++)
			admins[i] = new AdminInformation();
		
		initAdminList();
		
		
		
		tvLocations = new TVLocation[5];
		
		tvLocations[0] = new TVLocation(37.582138, 127.010732, admins[0]);
		tvLocations[1] = new TVLocation(37.582129, 127.011288, admins[1]);
		tvLocations[2] = new TVLocation(37.583005, 127.009530, admins[2]);
		tvLocations[3] = new TVLocation(37.581804, 127.009938, admins[3]);
		tvLocations[4] = new TVLocation(37.582694, 127.010823, admins[4]);
	}
	
	protected void initAdminList(){
		admins[0].TVname = "창의관TV";
		admins[0].e_mailAddress = "abc@naver.com";
		admins[0].phoneNumber = "010-000-1111";
		admins[0].serviceName = "A&A";
		
		admins[1].TVname = "낙산관TV";
		admins[1].e_mailAddress = "abc@naver.com";
		admins[1].phoneNumber = "010-000-1111";
		admins[1].serviceName = "PPT";
		
		admins[2].TVname = "진리관TV";
		admins[2].e_mailAddress = "abc@naver.com";
		admins[2].phoneNumber = "010-000-1111";
		admins[2].serviceName = "ORDER";
		
		admins[3].TVname = "공학관TV";
		admins[3].e_mailAddress = "abc@naver.com";
		admins[3].phoneNumber = "010-000-1111";
		admins[3].serviceName = "GCC";
		
		admins[4].TVname = "미래관TV";
		admins[4].e_mailAddress = "abc@naver.com";
		admins[4].phoneNumber = "010-000-1111";
		admins[4].serviceName = "A&A";
		
	}
	
	/**
	 * get nearby tv list from server.
	 * @param distance
	 * SmartTV들의 위치를 가져올 반경. 단위는 km. (최대 3)
	 * @return
	 * nearby tv list
	 * @throws IllegalArgumentException
	 * distance가 0 이하이거나 최대 값을 넘길때 발생 
	 * @throws SocketException
	 * 데이터를 가져오는데 실패하면 발생
	 */
	public TVLocation[] getTVLocationList(int distance) throws IllegalArgumentException, SocketException{
		
		return tvLocations;
		
	}
	
}
