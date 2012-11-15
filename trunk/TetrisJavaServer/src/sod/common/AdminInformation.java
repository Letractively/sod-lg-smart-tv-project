package sod.common;


/**
 * 
 * @author MB
 *
 */
public class AdminInformation {
	
	public AdminInformation() {
		// TODO Auto-generated constructor stub
		
		TVname = new String();
		password = new String();
		e_mailAddress = new String();
		phoneNumber = new String();
		serviceName = new String();
	}
	/**
	 * indicates name of smart TV.
	 */
	public String TVname;
	
	/**
	 * indicates password data to login as admin.
	 */
	public String password;
	
	/**
	 * indicates e-mail address of administrator.
	 */
	public String e_mailAddress;
	
	/**
	 * indicates phone number of administrator.
	 */
	public String phoneNumber;
	
	/**
	 * indicates name of service.
	 */
	public String serviceName;
}
