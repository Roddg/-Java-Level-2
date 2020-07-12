
public class Main {

/*
 * 	RR s 
 *  RW s
 *  WW s
 * 

*/
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		t1.start();
		t2.start();
		

	}

}
