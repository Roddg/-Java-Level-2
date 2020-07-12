import java.util.concurrent.locks.*;
import java.util.concurrent.*;


public class MyThread extends Thread
{
	private static Lock lock = 
		new  ReentrantLock();
	
	@Override
	public void run() {
		
		try {
			while (!lock.tryLock(1, TimeUnit.MILLISECONDS))
				System.out.println("Waiting........");
			try
			{
				for(int i=1; i <=100; i++)
					System.out.printf("%s  - %d\n",
							this.getName(), i);
				
			}
			finally
			{
				lock.unlock();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
