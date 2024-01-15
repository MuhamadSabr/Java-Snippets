package com.mmd.multiplethreads.messages;

import javax.management.relation.RelationNotification;
import java.util.Random;

public class MainWithoutDeadlock {
	public static void main(String[] args) {
		Message2 message = new Message2();
		Thread producer = new Thread(new Writer2(message));
		Thread consumer = new Thread(new Reader2(message));
		producer.start();
		consumer.start();
	}
}
class Message2{
	private String message;
	private boolean empty = true;
	public synchronized String read(){
		while(empty){
			try {//Release hold when u reach the loop
				wait();
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		empty = true;
		notify();
		return message;
	}
	public synchronized void write(String message){
		while (!empty){
			try {//Release hold when u reach the loop
				wait();
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		this.message = message;
		empty = false;
		notify();
	}
}

class Writer2 implements Runnable{
	private Message2 message;
	public Writer2(Message2 message){
		this.message = message;
	}

	public void run(){
		String[] messages = {
				"Humpty Dumpty sat on a wall",
				"Humpty Dumpty had a great fall",
				"All the king's horses and men",
				"Couldn't put Humpty together again"};

		Random random = new Random();
		for(String msg : messages){
			message.write(msg);
		}
		try {
			Thread.sleep(random.nextInt(2000));
		}catch (InterruptedException e){

		}
		message.write("Finished");
	}
}

class Reader2 implements Runnable{
	private final Message2 message;
	public Reader2(Message2 message){
		this.message = message;
	}
	public void run(){
		Random random = new Random();
		String latestMessage = message.read();
		while (!latestMessage.equalsIgnoreCase("Finished")){
			System.out.println(latestMessage);
			try {
				Thread.sleep(random.nextInt(2000));
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			latestMessage = message.read();
		}
	}
}