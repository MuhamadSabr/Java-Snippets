package com.mmd.producerconsumer;

import com.mmd.introduction.ThreadColor;

import javax.xml.catalog.CatalogFeatures;
import java.awt.*;
import java.lang.annotation.Retention;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static com.mmd.introduction.ThreadColor.*;

public class LockMain {
	public static void main(String... args){
//		List<String> buffer = Collections.synchronizedList(new ArrayList<>() );
//		CopyOnWriteArrayList<String> buffer = new CopyOnWriteArrayList<>();
		ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<>(6);//One element at a time, if there are more to be
//		ReentrantLock bufferLock = new ReentrantLock(true);		//processed then they have to wait until space becomes available
		ExecutorService executorService = Executors.newFixedThreadPool(6);//Will only allow three threads to run at a time

		MyProducer4 producer = new MyProducer4(buffer, ANSI_GREEN);
		MyConsumer4 consumer1= new MyConsumer4(buffer, ANSI_PURPLE);
		MyConsumer4 consumer2= new MyConsumer4(buffer, ANSI_RED);
		MyConsumer4 consumer3= new MyConsumer4(buffer, ANSI_CYAN);
		MyConsumer4 consumer4= new MyConsumer4(buffer, ANSI_BLUE);

//		new Thread(producer).start();
//		new Thread(consumer1).start();
//		new Thread(consumer2).start();
//		new Thread(consumer3).start();
//		new Thread(consumer4).start();

		executorService.execute(producer);//This is how u start the pool
		executorService.execute(consumer1);
		executorService.execute(consumer2);
		executorService.execute(consumer3);
		executorService.execute(consumer4);

//		Future<String> future = executorService.submit(new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				System.out.println(ANSI_BLACK + " I am being printed for the Callable class");
//				return "This is the Callable result";
//			}
//		});
//		future.cancel(true);//If u cancel the done flag is set to true
//		System.out.println("Is done: " + future.isDone());
//		try {
//			System.out.println(future.get());//Waits if necessary for the computation to complete, then retrieves the result.
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}

		executorService.shutdown();//U have to shutdown the service manually otherwise the app will keep running.
		//Will wait for any execution tasks to terminate before it shuts down. If u use shutdownNow() it will terminate waiting tasks n
		//return a list of tasks that were waiting In a not orderly fasion. but shutdown completes tasks and then terminates orderly
	}
}

class MyProducer2 implements Runnable{
	private String color;
	private final List<String> buffer;
	private Lock bufferLock;
	public MyProducer2(List<String> buffer, String color, Lock bufferLock){
		this.buffer = buffer;
		this.color = color;
		this.bufferLock = bufferLock;
	}
	public void run(){
		Random random = new Random();
		String[] numbers = {"1", "2", "3", "4", "5"};
		for(String number : numbers){
			System.out.println(color + "Adding: " + number);
			bufferLock.lock();
			buffer.add(number);
			bufferLock.unlock();
			try {
				Thread.sleep(random.nextInt(1000));
			}catch (InterruptedException e){
				System.out.println("Producer was interrupted because: " + e.getCause());
			}
		}
		System.out.println("Adding EOF and exiting...");
		bufferLock.lock();
		buffer.add("EOF");
		bufferLock.unlock();
	}
}



class MyConsumer2 implements Runnable {
	private String color;
	private List<String> buffer;
	private Lock bufferLock;
	public MyConsumer2(List<String> buffer, String color, Lock bufferLock) {
		this.buffer = buffer;
		this.color = color;
		this.bufferLock = bufferLock;
	}

	public void run(){
		synchronized (buffer){
			while (true) {
				bufferLock.lock();//If the section after this statement is locked. Any thread reaching this will suspend here.
				if (buffer.isEmpty()) {//Will wait indefinitely for the section to be unlocked by another thread.
					bufferLock.unlock();
					continue;
				}
				if (buffer.get(0).equalsIgnoreCase("EOF")) {
					System.out.println(color + "Exiting...");
					bufferLock.unlock();
					break;
				} else {
					System.out.println(color + "Removed " + buffer.remove(0));
				}
				bufferLock.unlock();//U have to make sure that the unlock statement is reachable in every case.
			}
		}
	}
}










class MyProducer3 implements Runnable{
	private String color;
	private final List<String> buffer;
	private ReentrantLock bufferLock;
	public MyProducer3(List<String> buffer, String color, Lock bufferLock){
		this.buffer = buffer;
		this.color = color;
		this.bufferLock = (ReentrantLock) bufferLock;
	}
	public void run(){
		Random random = new Random();
		String[] numbers = {"1", "2", "3", "4", "5"};
		for(String number : numbers){
			if(bufferLock.tryLock()){// U can use tryLock(long timeout, TimeUnit) This version needs to catch InterruptedException
				System.out.println("The lock was acquired, so I will do my job.");
				System.out.println(color + "Adding: " + number);
				try {
					buffer.add(number);
				}finally {
					bufferLock.unlock();
				}
			}else{
				System.out.println(color + "The lock was not acquired but I cannot wait, bye for now.");
			}

			try {
				Thread.sleep(random.nextInt(1000));
			}catch (InterruptedException e){
				System.out.println(color + "Producer was interrupted because: " + e.getCause());
			}
		}
		System.out.println("Adding EOF and exiting...");
		bufferLock.lock();
		try {
			buffer.add("EOF");
		}finally {
			bufferLock.unlock();
		}//U might say we did not have multiple cases, so why didn't we just use the simple lock n unlocking?
	}			//well in case the line in the try threw an exception we again make sure the lock is released.
}



class MyConsumer3 implements Runnable {
	private String color;
	private List<String> buffer;
	private ReentrantLock bufferLock;
	public MyConsumer3(List<String> buffer, String color, Lock bufferLock) {
		this.buffer = buffer;
		this.color = color;
		this.bufferLock = (ReentrantLock) bufferLock;
	}

	public void run(){
		System.out.println(color + "Is fair: " + bufferLock.isFair());
		while (true) {
//			System.out.println(Thread.currentThread().getName() +" has " + bufferLock.getHoldCount() + " number of holds on the lock");
//			System.out.println( bufferLock.getQueueLength() ) ;
			bufferLock.lock();//Putting ur critical section after the lock, in the try{} and unlock in the finally{} of try
			try {						//makes sure that in every case the lock gets unlocked. Even if an unexpected
				if (buffer.isEmpty()) {			//exception happened.
					continue;									//Also u will avoid having multiple unlock statements
				}													//That could get difficult to maintain n make ur code messy.
				if (buffer.get(0).equalsIgnoreCase("EOF")) {
					System.out.println(color + "Exiting...");
					break;
				} else {
					System.out.println(color + "Removed " + buffer.remove(0));
				}
			}finally {
				bufferLock.unlock();//If u try to unlock a lock that has not been locked. U get IllegalMonitorStateException
			}
		}
	}
}
















class MyProducer4 implements Runnable{
	private String color;
	private final ArrayBlockingQueue<String> buffer;
	public MyProducer4(ArrayBlockingQueue<String> buffer, String color){
		this.buffer = buffer;
		this.color = color;
	}
	public void run(){
		Random random = new Random();
		String[] numbers = {"1", "2", "3", "4", "5"};
		for(String number : numbers){
			System.out.println(color + "Adding: " + number);
			try {
				buffer.put(number);
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Adding EOF and exiting...");
		try {
			buffer.put("EOF");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}



class MyConsumer4 implements Runnable {
	private String color;
	private ArrayBlockingQueue<String> buffer;
	public MyConsumer4(ArrayBlockingQueue<String> buffer, String color) {
		this.buffer = buffer;
		this.color = color;
	}

	public void run(){
			while (true) {
				synchronized (buffer) {
					if (buffer.isEmpty()) {
						continue;
					}
					if (buffer.peek().equalsIgnoreCase("EOF")) {
						System.out.println(color + "Exiting...");
						break;
					} else {
						try {//It will block if the array is empty until an element becomes available
							System.out.println(color + "Removed " + buffer.take());//U can remove elements by calling take
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
	}
}