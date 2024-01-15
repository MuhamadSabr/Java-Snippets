package com.mmd.introduction;

import static com.mmd.introduction.ThreadColor.ANSI_GREEN;
public class AnotherThread extends Thread{

	@Override
	public void run() {
//		super.run();
		try {
			sleep(1000,999999);
		}catch (InterruptedException e){
			throw new RuntimeException(e);
		}
		System.out.println(ANSI_GREEN + "Hello from another thread");
	}
}
