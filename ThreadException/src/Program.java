
public class Program {
	
	public static void test(int k)
	{
		try
		{
			if (k < 0) throw new RuntimeException("k < 0");
		}
		finally
		{
			System.out.println("finally");
			return;
		}
		
	}
	

	public static void main(String[] args) throws InterruptedException {
		
		Thread t = new Thread() {
		    public void run() {
		        System.out.println("Sleeping ...");
		        try {
		            Thread.sleep(1000);
		        } catch (InterruptedException e) {
		            System.out.println("Interrupted.");
		        }
		        System.out.printf("Throwing exception from thread %s...\n",
		        		this.getName());
		        throw new RuntimeException();
		    }
		};
		
		/*Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
		    public void uncaughtException(Thread th, Throwable ex) {
		        System.out.printf("Uncaught exception from thread %s handled by thread %s (specific): %s\n",
		        			th.getName(), Thread.currentThread().getName(), ex);
		    }
		};
		t.setUncaughtExceptionHandler(h);*/
		
		
		
		Thread.setDefaultUncaughtExceptionHandler(
				new Thread.UncaughtExceptionHandler() {
				    public void uncaughtException(Thread th, Throwable ex) {
				    	
				        System.out.printf("Uncaught exception from thread %s handled by thread %s (unspecific): %s\n",
			        			th.getName(), Thread.currentThread().getName(), ex);
				    }
				}				
		);
		
		t.start();
		
		Thread.sleep(2000);
		
		System.out.println("main");

		//test(-5);
		
		//t.interrupt();
	}

}
