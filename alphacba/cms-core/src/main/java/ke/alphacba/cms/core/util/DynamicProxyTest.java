package ke.alphacba.cms.core.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class DynamicProxyTest {
	static interface OutputDemo{
		public void outputString();
	}
	
	static class OutputDemoImpl implements OutputDemo{

		@Override
		public void outputString() {
			System.out.println("output from outputdemo");
		}
		
	}
	
	static class OutputDemoHandler implements InvocationHandler{
		private Object targetObject;
		public OutputDemoHandler(Object targetObject) {
			this.targetObject = targetObject;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("before output");
			Object result = method.invoke(targetObject, args);
			return result;
		}
		
	}
	
	public static void main(String[] args) {
		OutputDemo outputDemoimpl = new OutputDemoImpl();
		OutputDemoHandler handler = new OutputDemoHandler(outputDemoimpl);
		OutputDemo proxy = 
				(OutputDemo)Proxy.newProxyInstance(outputDemoimpl.getClass().getClassLoader(), 
						outputDemoimpl.getClass().getInterfaces(), 
						handler);
		proxy.outputString();
		
	}
}
