package sod.common;

import java.util.Date;



public class ThreadEx extends Thread {
	protected Object arg;
	protected ActionEx f = null;
	protected long begin;
	
	public ThreadEx(Object arg, ActionEx f) {
		this.arg = arg;
		this.f = f;
	}

	@Override
	public void run() {
		f.work(arg);
	}
	
	@Override
	public void start(){
		super.start();
	}

	public static void sleep(long ms) {
		if(ms == 0)
			Thread.yield();
		else
		{
			try {
				Thread.sleep(ms);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}	
	
	public static void invoke(Object arg, ActionEx f){
		ThreadEx th = new ThreadEx(arg, f);
		th.start();
	}
	
	public static long getCurrentTime(){
		return new Date().getTime();
	}
}
