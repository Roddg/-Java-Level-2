package ru.specialist;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Program {

	public static void main(String[] args) throws InterruptedException {
		
		class Sync {
			volatile int counter = 0;
		}
		Sync sync = new Sync();
		Lock lock = new ReentrantLock(); //mutex
		Condition greater50 = lock.newCondition();
		Condition greater70 = lock.newCondition();
		
		Thread t0 = new Thread( ()->{
			for(int i=1; i <= 100; i++) {  
				System.out.printf("%s : %d\n", Thread.currentThread().getName(), i);
				lock.lock();
				try {
					sync.counter = i;
					if (i == 50)
						greater50.signal();
					if (i == 70)
						greater70.signal();
				}
				finally {
					lock.unlock();
				}

			}
		}, "T0");
		
		Thread t1 = new Thread(()->{
			try {
				lock.lock();
				try {
					while(sync.counter < 50)
						greater50.await();
				}
				finally {
					lock.unlock();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i=1; i <= 100; i++) { 
				System.out.printf("%s : %d\n", Thread.currentThread().getName(), i);
			}
		}, "T1");
		Thread t2 = new Thread(()->{
			try {
				lock.lock();
				try {
					while(sync.counter < 70)
						greater70.await();
				}
				finally {
					lock.unlock();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i=1; i <= 100; i++) { 
				System.out.printf("%s : %d\n", Thread.currentThread().getName(), i);
			}
		}, "T2");
		
		
		t0.start();
		t1.start();
		t2.start();
		//Thread.sleep(100);
		
		

	}

}
