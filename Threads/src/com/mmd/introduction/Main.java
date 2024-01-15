package com.mmd.introduction;

import static com.mmd.introduction.ThreadColor.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
public class Main {
	public static void main(String[] args) {

		System.out.println(ANSI_BLUE + "Hello from the main thread");

		AnotherThread thread1 = new AnotherThread();

		System.out.println("Hello again from the main thread");

//		thread1.run();//Won't start the thread again. But instead it will execute the run method on this thread.

		Thread anonymousThread = new Thread(){
			@Override
			public void run() {
				System.out.println(ANSI_CYAN + "Hello from the anonymous class thread");
			}
		};//.start();//U have to start it directly. U can never guarantee which thread runs first.

		System.out.println(ANSI_BLUE + "Hello from the Main thread again");

		Thread myRunnableThread = new Thread(new MyRunnable());
		System.out.println(ANSI_BLUE + "Hello from the Main thread again");

		Thread anonymousRunnable = new Thread(new Runnable(){
			@Override
			public void run(){
				try {
//					thread1.join();//If thread1 never terminates then this thread will never run, it will look like it died or crashed.
					thread1.join();//It will have a timeout of 10 seconds if it did not terminate then this will start running.
					//So it says wait for thread1 to terminate or 10 seconds to terminate and then run(whichever terminates first).
//					Thread.sleep(5000);
				} catch (InterruptedException e) {
//					System.out.println("Another thread woke me up");//Could be interrupted by other threads.
//					throw new RuntimeException(e);//JVM asks the underlying OS also to put this thread to sleep when interrupted.
					System.out.println("I could not wait since I was interrupted, I terminate my work");
					return; //Terminate if it does get interrupted.
				}
				System.out.println(ANSI_PURPLE + "Hello from " + Thread.currentThread().getName());
			}
		});

		thread1.setPriority(Thread.MAX_PRIORITY);
		anonymousRunnable.setName("anonymousRunnable");
		anonymousRunnable.start();
//		anonymousRunnable.interrupt();
		System.out.println(ANSI_BLUE + "Hello from the main thread again");
		anonymousThread.start();
		myRunnableThread.start();
		thread1.start();//Will start the thread n U can only start(call start on) a thread once.

	}
}