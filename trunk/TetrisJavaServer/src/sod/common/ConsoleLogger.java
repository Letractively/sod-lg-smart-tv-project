package sod.common;


public class ConsoleLogger implements Logable {
	
	private static Logable instance;
	static{
		instance = new ConsoleLogger(); 
	}
	
	private ConsoleLogger() { }
	
	public static Logable getInstance(){
		return instance;
	}

	@Override
	public void log(Object arg) {
		// TODO Auto-generated method stub
		System.out.print(arg.toString());
	}
	
}
