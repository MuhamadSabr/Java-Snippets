package com.mmd.multiplethreads.messages;

import javax.management.relation.RelationNotification;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Message message = new Message();
		(new Thread(new Writer(message))).start();
		(new Thread(new Reader(message))).start();
	}
}

class Message{
	private String message;
	private boolean empty = true;
//Here when a thread uses one of the methods, U don't want another thread to be able to call any of them.
	public synchronized String read(){//Used by the consumer
		while(empty){//Loop until ur empty is set to false. If it is not empty, you can read your message.
			//U wrote ur first message with no problem, but the second comes here and blocks these two methods, as a result
		}//The reader cannot access the read, since only one synchronized method can be accessed by one thread at a time.
		empty = true; //U say u read the message, and below u return it.
		return message;
	}

	public synchronized void write(String message){//Used by the producer.
		while (!empty){//U r gonna loop until the empty is set to true. If empty is true, then u write your method.
			//The thread that is looping, is holding the lock on the object for eternity. The other is trying to reach but the lock
		}//is never released. This is known as a deadlock.
		empty = false;//U say u wrote the message, and below u set it.
		this.message = message;
	}
}

class Writer implements Runnable{
	private Message message;
	public Writer(Message message){
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
			Thread.sleep(random.nextInt(2000));//Make it sleep for 0 up to two seconds.
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		message.write("Finished");
	}
}

class Reader implements Runnable{
	private Message message;
	public Reader(Message message){
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