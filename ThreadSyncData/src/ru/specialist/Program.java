package ru.specialist;

class Sync {
	private volatile int counter = 0;
	
	public int getCounter() {
		return counter;
	}
	
	public synchronized void inc() {
		counter++;
	}
}
public class Program {
	
	//volatile static int counter = 0;

	public static void main(String[] args) throws InterruptedException {
		
		// StringBuilder  non Thread-safe
		// StringBuffer   Tread-safe
		// ArrayList non Thread-safe
		// Vector    Thread-safe
		
		//final Object sync = new Object();
		
		final Sync sync = new Sync();
		
		
		Thread t0 = new Thread( ()->{
			for(int i=1; i <= 10000; i++) { 
				//System.out.printf("%s : %d\n", Thread.currentThread().getName(), i);
				//synchronized(sync) {
				//	sync.counter++;	
				//}
				sync.inc();
				
			}
		}, "T0");
		Thread t1 = new Thread(()->{
			for(int i=1; i <= 10000; i++) { 
				//System.out.printf("%s : %d\n", Thread.currentThread().getName(), i);
				//synchronized(sync) {
				//	sync.counter++;	
				//}
				sync.inc();
			}
		}, "T1");
		
		t0.start();
		t1.start();
		
		t0.join();
		t1.join();
		
		/*int a;
		int b = 0;
		
		a = 7;
		b = 10;
		*/
		
		
		/*
		 *
		 * if (a == 7 && b ==0 )
		 */
		 
		
		
		
		System.out.println(sync.getCounter());
		

	}

}
