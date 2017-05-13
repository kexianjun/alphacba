package ke.alphacba.cms.core.util;

import java.util.concurrent.Semaphore;

public class ThreadTest implements Runnable {
	
	private String contents;
	private Semaphore self;
	private Semaphore next;
	
	public ThreadTest(String contents,Semaphore self, Semaphore next) {
		this.contents = contents;
		this.self = self;
		this.next = next;
	}
	public void run() {
		while (true) {
				try {
					self.acquire();
					System.out.print(contents);
					Thread.sleep(100);
					next.release();
				} catch (InterruptedException e) {
					
				}
		}
	}
	
	public static void main(String[] args) throws Exception {
		/*Semaphore aSemaphore = new Semaphore(1);
		Semaphore bSemaphore = new Semaphore(1);
		Semaphore cSemaphore = new Semaphore(1);
		Semaphore dSemaphore = new Semaphore(1);
		
		Thread threadDemoA = new Thread(new ThreadTest("A", aSemaphore,bSemaphore));
		Thread threadDemoB = new Thread(new ThreadTest("B", bSemaphore,cSemaphore));
		Thread threadDemoC = new Thread(new ThreadTest("C", cSemaphore,dSemaphore));
		Thread threadDemoD = new Thread(new ThreadTest("D", dSemaphore,aSemaphore));
		bSemaphore.acquire();
		cSemaphore.acquire();
		dSemaphore.acquire();
		threadDemoA.start();
		threadDemoB.start();
		threadDemoC.start();
		threadDemoD.start();*/
		/*String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());*/
		Object object = new Object();
		System.out.println(object.hashCode());
		
	}

}
