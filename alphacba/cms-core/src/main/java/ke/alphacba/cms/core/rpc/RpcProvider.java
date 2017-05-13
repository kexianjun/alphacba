package ke.alphacba.cms.core.rpc;

public class RpcProvider {
	public static void main(String[] args) throws Exception {
		HelloService helloService = new HelloServiceImpl();
		RPCFramework.export(helloService, 8888);
	}
}
