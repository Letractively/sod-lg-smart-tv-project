package sod.smarttv;


public class TimeoutThread extends Thread {
	Object serviceMonitor;
	int timeout;
	
	public TimeoutThread(Object obj, int time)
	{
		serviceMonitor = obj;
		timeout = time;
	}
	
	public void run(){
		try {
			sleep(timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			return;
		}
		
		synchronized(serviceMonitor){
			serviceMonitor.notify();
		//	Log.i("down","timeout notify() order hold");
		}
	}
}
