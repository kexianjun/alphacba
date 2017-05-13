package ke.alphacba.cms.core.util;

public class SingletonTest {
	
	private static class InstanceHolder{
		public static final SingletonTest SINGLETON_INSTANCE = new SingletonTest();
	}
	private SingletonTest(){
	}
	
	public static SingletonTest getInstance(){
		return InstanceHolder.SINGLETON_INSTANCE;
	}
	
	public static void main(String[] args) {
		int [] array = {-32,-34,45,-34,-6,3,-1,-16,30,-12,-12,-8,34};
		
		int sum = 0;
		int max = array[0];
		int lIndex = 0;
		int rIndex = 0;
		int lTemp = 0;
		for(int i = 0; i < array.length; i++){
			sum = sum + array[i];
			if (sum > max) {
				max = sum;
				rIndex = i;
				lIndex = lTemp;
			}
			if (sum < 0) {
				sum = 0;
				lTemp = i+1;
			}
		}
		
		System.out.println("从：" + lIndex + " 到：" + rIndex +"为最大子序列；"+ "和为：" + max);
	}
}
