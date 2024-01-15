package com.mmd.introduction;

import static com.mmd.introduction.ThreadColor.ANSI_RED;

public class MyRunnable implements Runnable{//Overriding run is compulsory here.
	@Override
	public void run() {
		System.out.println(ANSI_RED + "Hello from MyRunnable class that implements Runnable");
	}

}
