import java.util.concurrent.Callable;

public class MyCallTask implements Callable<Long>{

	@Override
	public Long call() throws Exception {
		
		System.out.println(Thread.currentThread().getName());
		
		// Thread.sleep(10);
		
		long summa = 0;
		for(long i=1; i <= 100000000L; i++)
			if (Thread.interrupted()) return -1L;
			else summa += i;
		
		return summa;
	}
	

}
