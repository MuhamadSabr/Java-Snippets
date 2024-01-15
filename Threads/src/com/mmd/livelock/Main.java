package com.mmd.livelock;

public class Main {
	public static void main(String args[]) {
		Worker worker1 = new Worker("Mohammed", true);
		Worker worker2 = new Worker("Ahmed", true);
		SharedResource sharedResource = new SharedResource(worker1);
		new Thread(new Runnable() {
			@Override
			public void run() {
				worker1.work(sharedResource, worker2);
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				worker2.work(sharedResource, worker1);
			}
		}).start();
	}
}


class Worker{
	private final String name;
	private boolean isActive;
	public Worker(String name, boolean isActive) {
		this.name = name;
		this.isActive = isActive;
	}
	public String getName() {
		return name;
	}
	public boolean isActive() {
		return isActive;
	}
	public synchronized void work(SharedResource sharedResource, Worker otherWorker){
		while (isActive){
			if(sharedResource.getOwner()!=this){
				try {
					wait(10);
				}catch (InterruptedException e){
					e.printStackTrace();
				}
				continue;
			}
			if(otherWorker.isActive){
				System.out.println(name + " : give the resource to the worker " + otherWorker.getName());
				sharedResource.setOwner(otherWorker);
				continue;
			}
			System.out.printf(name + " working on the common resource");
			isActive = false;
			sharedResource.setOwner(otherWorker);
		}
	}
}

class SharedResource{
	private Worker owner;
	public SharedResource(Worker owner) {
		this.owner = owner;
	}
	synchronized public void setOwner(Worker owner) {
		this.owner = owner;
	}
	public Worker getOwner() {
		return owner;
	}
}