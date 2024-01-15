package com.mmd.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
	private final Queue<Integer> buffer = new LinkedList<>();
	private final int capacity = 10;

	private final Lock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();

	public void produce(int item) throws InterruptedException {
		lock.lock();
		try {
			while (buffer.size() == capacity) {
				// Buffer is full, wait for it to not be full
				notFull.await();
			}

			buffer.offer(item);
			System.out.println("Produced: " + item);

			// Signal that the buffer is not empty anymore
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public int consume() throws InterruptedException {
		lock.lock();
		try {
			while (buffer.isEmpty()) {
				// Buffer is empty, wait for it to not be empty
				notEmpty.await();
			}

			int item = buffer.poll();
			System.out.println("Consumed: " + item);

			// Signal that the buffer is not full anymore
			notFull.signal();
			return item;
		} finally {
			lock.unlock();
		}
	}
}