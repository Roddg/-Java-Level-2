package ru.specialist;

public class Program {
	
	public static void test() {
		System.out.println("abc");
	}
	

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(()->{
			for(int i=1; i <= 100; i++) 
				System.out.printf("%s : %d\n", Thread.currentThread().getName(), i);
		}, "T1");
		Thread t0 = new Thread( ()->{
			try {
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i=1; i <= 100; i++) 
				System.out.printf("%s : %d\n", Thread.currentThread().getName(), i);
		}, "T0");
		
		
		t1.start();
		Thread.sleep(200);
		t0.start();
		
		
		// Thread.sleep(1000);
		t0.join();
		t1.join();
		
		System.out.println(Thread.currentThread().getName());
		
		//Thread.currentThread().join();
		

	}

}
