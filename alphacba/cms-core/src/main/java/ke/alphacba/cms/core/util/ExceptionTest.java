package ke.alphacba.cms.core.util;

public class ExceptionTest {

	static class Annoyance extends Exception {}
	static class Sneeze extends Annoyance {}
	public static void main(String[] args) 
			throws Exception {
		try {
			try {
				throw new Sneeze();
				}catch ( Annoyance a ) {
					System.out.println("Caught Annoyance");
					throw a;
					}
			}catch ( Sneeze s ) {
				System.out.println("Caught Sneeze");
				return ;
				}finally {
		System.out.println("Hello World!");}
	}
}
