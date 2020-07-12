
import java.util.concurrent.locks.*;

public class BounderBuffer
{
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();

	final Object[] items = new Object[100];
	int putptr, takeptr, count;

	public void put(Object x)
	{
		lock.lock();
		try
		{
			while (count == items.length)
				notFull.await();
			items[putptr] = x;
			if (++putptr == items.length)
				putptr = 0;
			++count;
			notEmpty.signalAll();
			System.out.printf("Thread %s has put and signal data: %s\n",
					Thread.currentThread().getName(), x);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			System.out.printf("Thread %s unlock\n",
					Thread.currentThread().getName());
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock.unlock();
		}
	}

	public Object take()
	{
		lock.lock();
		try
		{
			while (count == 0)
			{
				System.out.printf("Thread %s take waiting...\n", Thread.currentThread().getName());
				notEmpty.await();
			}
			System.out.printf("Thread %s is going to take data\n", Thread.currentThread().getName());
			
			Object x = items[takeptr];
			if (++takeptr == items.length)
				takeptr = 0;
			--count;
			notFull.signalAll();
			return x;
		} catch (InterruptedException e)
		{
			e.printStackTrace();
			return null;
		} finally
		{
			lock.unlock();
		}
		
	}

}
