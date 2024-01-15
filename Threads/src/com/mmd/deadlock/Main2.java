package com.mmd.deadlock;

public class Main2 {
	public static void main(String[] args) {
		PolitePerson mohammed = new PolitePerson("Mohammed");
		PolitePerson ahmed    = new PolitePerson("Ahmed");
		new Thread(new Runnable() {
			@Override
			public void run() {
				mohammed.sayHello(ahmed);//Thread 1 acquires the intrinsic lock of Mohammed
			}							//The same thread needs the lock of ahmed also to say hello back in the same method. But thread 2 already has acquired it.
		}).start();
																//They both finish the first statement in the method and Boom they are in a deadlock.
		new Thread(new Runnable() {
			@Override
			public void run(){
			ahmed.sayHello(mohammed);//Thread 2 acquires the intrinsic lock of Ahmed.
			}						//Thread 2 needs mohammed's lock to say hello back in the same method, but Thread 1's got it.
		}).start();
	}

	record PolitePerson(String name) {
		public synchronized void sayHello(PolitePerson person) {
				System.out.format("%s: %s has said hello to me%n", this.name, person.name);
				person.sayHelloBack(this);
		}
		public synchronized void sayHelloBack(PolitePerson person) {
				System.out.format("%s: %s has said hello back to me%n", this.name, person.name);
		}
	}
}
