package com.mmd.multiplethreads;

import static com.mmd.introduction.ThreadColor.*;
public class Main {
	public static void main(String[] args) {
		Countdown countdown = new Countdown();
		Thread t1 = new CountdownThread(countdown);
		t1.setName("Thread 1");
		Thread t2 = new CountdownThread(countdown);
		t2.setName("Thread 2");
		t1.start();
		t2.start();
	}
}

class Countdown{//Can also be referred to as 'race condition' which is more common to when two or more threads write to a common resource.
				//There wouldn't be a problem if both threads were only reading a shared resource.
	private int i;//U r gonna face 'thread interference'. //Both threads work on the same instance, so have access to the same memory location of instance variables.
	public /*synchronized*/ void doCountdown(){
		String color;//If u try to use color as the locked object it fails, since this is a local variable n not an instance, each thread has its own copy of color.
		switch (Thread.currentThread().getName()){											//and therefore does not need to wait for any thread to start working on it.
			case "Thread 1" -> color = ANSI_CYAN;
			case "Thread 2" -> color = ANSI_PURPLE;
			default -> color = ANSI_RED;
		}
		synchronized (this) {//Synchronizing only this block that is prone to thread interference by using the intrinsic lock on the object itself that both threads share
			for (i = 10; i > 0; i--) {
				System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
			}
		}
	}
}
class CountdownThread extends Thread{
	private Countdown countdown;

	public CountdownThread(Countdown countdown){
		this.countdown = countdown;
	}

	public void run(){
		countdown.doCountdown();
	}
}