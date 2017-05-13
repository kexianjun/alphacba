package ke.alphacba.cms.core.util;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class StringTest {
	public static void main(String[] args) {
		/*String a = "ab";
		String b = "abc";
		String c = a + "c";
		String d = "ab" + "c";
		System.out.println(b == c);
		System.out.println(b == d);
		System.out.println(d == c);*/
		
		/*生产者消费者模型*/
		Product product = new Product();
		Thread produce = new Thread(new ProduceProduct(product));
		produce.setName("生产者1 ");
		Thread comsume = new Thread(new ConsumeProduct(product));
		comsume.setName("消费者1 ");
		Thread comsume2 = new Thread(new ConsumeProduct(product));
		comsume2.setName("消费者2 ");
		Thread comsume3 = new Thread(new ConsumeProduct(product));
		comsume3.setName("消费者3 ");
		produce.start();
		comsume.start();
		comsume2.start();
		comsume3.start();
		/*ProductSem productSem = new ProductSem(2);
		Thread prodece1 = new Thread(new ProduceSem(productSem));
		Thread prodece2 = new Thread(new ProduceSem(productSem));
		prodece1.setName("生产线程1 ");
		prodece2.setName("生成线程2 ");
		Thread comsume1 = new Thread(new ComsumeSem(productSem));
		Thread comsume2 = new Thread(new ComsumeSem(productSem));
		comsume1.setName("消费线程1 ");
		comsume2.setName("消费线程2 ");
		try {
			prodece1.start();
			prodece2.start();
			comsume1.start();
			comsume2.start();
		} catch (Exception e) {
		}*/
		
	}
	
	static class Product{
		
		private int max_product = 10;
		private int min_product = 0;
		private volatile int product = 0;
		public Product(int max_product){
			this.max_product = max_product;
		}
		
		public Product(){
			
		}
		
		public synchronized void produceProduct(){
			while (this.product >= max_product) {
				try{
					System.out.println(Thread.currentThread().getName() + "产品已经足够，请稍等");
					wait();
				}catch (Exception e) {
				}
				return;
			}
			product++;
			System.out.println(Thread.currentThread().getName() + " 生产了第：" + product + "个产品");
			notifyAll();
		}
		
		public synchronized void comsumeProduct(){
			while (this.product <= min_product) {
				try{
					System.out.println(Thread.currentThread().getName() + "产品不够了，请稍等");
					wait();
				}catch (Exception e) {
				}
				return;
			}
			System.out.println(Thread.currentThread().getName() + "消费了第：" + product + " 个产品");
			product--;
			notifyAll();
		}
	}
	
	static class ConsumeProduct implements Runnable{
		
		private Product product;
		public ConsumeProduct(Product product) {
			this.product = product;
		}
		@Override
		public void run() {
			System.out.println("开始消费产品");
			while(true){
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				product.comsumeProduct();
			}
		}
		
	}
	
	static class ProduceProduct implements Runnable{
		private Product product;
		public ProduceProduct(Product product) {
			this.product = product;
		}
		@Override
		public void run() {
			System.out.println("开始生产产品");
			while (true) {
				try{
					Thread.sleep(1000);
				}catch (Exception e) {
				}
				product.produceProduct();
			}
		}
		
	}
	
	static class ProductSem{
		
		private Semaphore semaphore;
		private Semaphore isFull;
		private Semaphore isEmpty;
		private AtomicInteger product = new AtomicInteger(0);
		public ProductSem(int maxNum){
			semaphore = new Semaphore(1);
			isFull = new Semaphore(maxNum);
			isEmpty = new Semaphore(0);
		}
		
		public void produce(){
			try {
				isFull.acquire();
				semaphore.acquire();
				System.out.println(Thread.currentThread().getName() + "生产了第：" + product.incrementAndGet() + "个产品");
			} catch (Exception e) {
			}finally {
				semaphore.release();
				isEmpty.release();
			}
		}
		
		public void comsume() {
			try {
				isEmpty.acquire();
				semaphore.acquire();
				System.out.println(Thread.currentThread().getName() + "消费了第：" + product.getAndDecrement() + "个产品");
			} catch (Exception e) {
			}finally {
				semaphore.release();
				isFull.release();
			}
		}
	}
	
	static class ProduceSem implements Runnable{
		private ProductSem produceSem;
		public ProduceSem(ProductSem productSem) {
			this.produceSem = productSem;
		}
		@Override
		public void run() {
			while(true){
				try {
					produceSem.produce();
					Thread.sleep(1000);
				} catch (Exception e) {
				}
			}
		}
	}
	static class ComsumeSem implements Runnable{
		private ProductSem productSem;
		public ComsumeSem(ProductSem productSem) {
			this.productSem = productSem;
					
		}
		
		@Override
		public void run() {
			while(true){
				try{
					productSem.comsume();
					Thread.sleep(1000);
				}catch (Exception e) {
				}
			}
		}
	}
}
