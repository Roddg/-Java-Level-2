
public class Program {

	public static void main(String[] args) throws InterruptedException {
		BounderBuffer buf = new BounderBuffer();
		
		Thread t0 = new Thread(()->{
			System.out.printf("Thread %s has taken data: %s\n", Thread.currentThread().getName(),
					buf.take());
		});
		
		t0.start();
		Thread.sleep(100);
		
		Thread t1 = new Thread(()->{
			buf.put("my string");
		});
		
		t1.start();
		
		
		

	}

}
