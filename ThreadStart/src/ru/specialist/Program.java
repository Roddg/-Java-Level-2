package ru.specialist;

class MyThread extends Thread {
	
	private int a, b;
	
	public MyThread(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public void run() {
		for(int i=a; i <= b; i++)
			System.out.printf("%s : %d\n", this.getName(), i);
	}
}

class MySuperThread implements Runnable {
	@Override
	public void run() {
		for(int i=1; i <= 100; i++)
			System.out.printf("%s : %d\n", Thread.currentThread().getName(), i);
	}
}


public class Program {

	public static void main(String[] args) throws InterruptedException {
		
		Thread t0 = new MyThread(1, 50);
		Thread t1 = new MyThread(51, 100);
		Thread t2 = new Thread(new MySuperThread());
		
		Thread t3 = new Thread(
			new Runnable() {
				public void run() {
					for(int i=1; i <= 100; i++)
						System.out.printf("%s : %d\n", Thread.currentThread().getName(), i);
				}
			}	
		);
		
		int a=200;
		int b=400;
		
		Thread t4 = new Thread( ()->{
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.printf("%s interrupting from sleep...\n", Thread.currentThread().getName());
				return;
			}
			
			
			for(int i=a; i <= b; i++) {
				if (Thread.interrupted()) {
					System.out.printf("%s interrupting...\n", Thread.currentThread().getName());
					return;
				}
				System.out.printf("%s : %d\n", Thread.currentThread().getName(), i);
			}
		});
		
		t0.setPriority(Thread.MAX_PRIORITY);
		
		t0.start();
		t1.start();
		t2.start();
		t3.setDaemon(true);
		//t4.setDaemon(true);
		
		t3.start();
		t4.start();
		
		System.out.println(t1.getState());
		System.out.println(Thread.currentThread().getName());
		t4.interrupt();
		
		
		Thread.sleep(2000);
		System.out.println(t1.getState());
		
	}

}
