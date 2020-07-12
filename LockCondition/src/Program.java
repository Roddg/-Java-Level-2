import java.util.concurrent.locks.*;

public class Program {

	public static void main(String[] args) {
		/*class Sync {
			int counter;
		}
		
		Sync sync = new Sync();*/
		Lock l = new ReentrantLock();
		Condition greater50 = l.newCondition();
		Condition greater70 = l.newCondition();
		
		Thread t0 = new Thread(()->{
			for(int i=1; i <= 100; i++) {
				System.out.printf("%s : %d\n", Thread.currentThread().getName(), i);
				l.lock();
				try {
					if (i == 50) greater50.signalAll();
					if (i == 70) greater70.signalAll();
				}
				finally {
					l.unlock();
				}
				
				
				/*synchronized(sync) {
					sync.counter = i;
					sync.notifyAll();
				}*/
			}
		});
		Thread t1 = new Thread(()->{
			/*synchronized(sync) {
				while(sync.counter < 50)
					try {
						sync.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}*/
			l.lock();
			try {
				greater50.awaitUninterruptibly();
			}
			finally {
				l.unlock();
			}
			
			for(int i=1; i <= 100; i++) {
				System.out.printf("%s : %d\n", Thread.currentThread().getName(), i);
			}
		});
		Thread t2 = new Thread(()->{
			/*synchronized(sync) {
				while(sync.counter < 70)
					try {
						sync.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}*/
			l.lock();
			try {
				greater70.awaitUninterruptibly();
			}
			finally {
				l.unlock();
			}
			for(int i=1; i <= 100; i++) {
				System.out.printf("%s : %d\n", Thread.currentThread().getName(), i);
			}
		});
		
		t0.start();
		t1.start();
		t2.start();

	}

}
