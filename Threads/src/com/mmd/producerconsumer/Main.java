package com.mmd.producerconsumer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.mmd.introduction.ThreadColor.*;

public class Main {
	public static void main(String... args){
//		List<String> buffer = Collections.synchronizedList(new ArrayList<>() );
		CopyOnWriteArrayList<String> buffer = new CopyOnWriteArrayList<>();
		MyProducer producer = new MyProducer(buffer, ANSI_GREEN);
		MyConsumer consumer1= new MyConsumer(buffer, ANSI_PURPLE);
		MyConsumer consumer2= new MyConsumer(buffer, ANSI_RED);
		new Thread(producer).start();
		new Thread(consumer1).start();
		new Thread(consumer2).start();

	}
}

class MyProducer implements Runnable{
	private String color;
	private final List<String> buffer;
	public MyProducer(List<String> buffer, String color){
		this.buffer = buffer;
		this.color = color;
	}
	public void run(){
		Random random = new Random();
		String[] numbers = {"1", "2", "3", "4", "5"};
		for(String number : numbers){
			System.out.println(color + "Adding: " + number);
			synchronized (buffer) {
				buffer.add(number);
			}
			try {
				Thread.sleep(random.nextInt(1000));
			}catch (InterruptedException e){
				System.out.println("Producer was interrupted because: " + e.getCause());
			}
		}
		System.out.println("Adding EOF and exiting...");
		synchronized (buffer) {
			buffer.add("EOF");
		}
	}
}



class MyConsumer implements Runnable {
	private String color;
	private List<String> buffer;
	public MyConsumer(List<String> buffer, String color) {
		this.buffer = buffer;
		this.color = color;
	}

	public void run(){
			while (true) {
				synchronized (buffer) {
					if (buffer.isEmpty()) {
						continue;
					}
					if (buffer.get(0).equalsIgnoreCase("EOF")) {
						System.out.println(color + "Exiting...");
						break;
					} else {
						System.out.println(color + "Removed " + buffer.remove(0));
					}
				}
			}
	}
}



