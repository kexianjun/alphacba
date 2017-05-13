package ke.alphacba.cms.core.rpc;

public class RpcConsumer {
	public static void main(String[] args) throws Exception {
		HelloService service = RPCFramework.refer(HelloService.class, "127.0.0.1", 8888);
		for(int i = 0; i < 10; i++){
			String hello = service.sayHello("i = " + i);
			System.out.println(hello);
			Thread.sleep(1000);
		}
	}
}
