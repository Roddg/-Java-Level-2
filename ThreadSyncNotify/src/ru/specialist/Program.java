package ru.specialist;

public class Program {

	public static void main(String[] args) throws InterruptedException {
		
		class S {
			volatile double x;
			boolean isSin = true;
			
		}
		
		
		class Sync {
			volatile int counter = 0;
		}
		Sync sync = new Sync();
		
		Thread t0 = new Thread( ()->{
			for(int i=1; i <= 100; i++) {  
				System.out.printf("%s : %d\n", Thread.currentThread().getName(), i);
				synchronized(sync) {
					sync.counter = i;
					//sync.notify();
					sync.notifyAll();
				}
			}
		}, "T0");
		Thread t1 = new Thread(()->{
			try {
				synchronized(sync) {
					while(sync.counter < 50)
						sync.wait();
						// sync.wait(timeout)
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
				synchronized(sync) {
					while(sync.counter < 70)
						sync.wait();
						// sync.wait(timeout)
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
