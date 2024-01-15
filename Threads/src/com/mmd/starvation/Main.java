package com.mmd.starvation;

import java.util.concurrent.locks.ReentrantLock;

import static com.mmd.introduction.ThreadColor.*;

public class Main {
	private static Object lock = new Object();
	private static ReentrantLock reentrantLock = new ReentrantLock(true);//Only fairness in acquiring the lock is guaranteed and not in threads scheduling.

	public static void main(String[] args) {											//First come, first served in getting the lock is guaranteed.
		Thread thread1 = new Thread(new Worker(ANSI_RED), "Priority 10");			//tryLock method does not honor the FCFS.			 //of providing that fairness
		thread1.setPriority(Thread.MAX_PRIORITY);								//Finally trying to be fair could slow things down, since there is an additional layer
		Thread thread2 = new Thread(new Worker(ANSI_GREEN), "Priority 8");
		thread2.setPriority(8);
		Thread thread3 = new Thread(new Worker(ANSI_CYAN), "Priority 6");
		thread3.setPriority(6);
		Thread thread4 = new Thread(new Worker(ANSI_PURPLE), "Priority 4");
		thread4.setPriority(4);
		Thread thread5 = new Thread(new Worker(ANSI_BLUE), "Priority 2");
		thread5.setPriority(2);

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();

	}

	private static class Worker implements Runnable{
		private int runCount = 1;
		private String threadColor;
		public Worker(String threadColor) {
			this.threadColor = threadColor;
		}
		public void run(){
			for(int i=0; i<100; i++, runCount++){
				reentrantLock.lock();
				try{
					System.out.format(threadColor+ "%s: runCount = %d%n", Thread.currentThread().getName(), runCount);
				}finally{
					reentrantLock.unlock();
				}
			}
		}
	}

}
