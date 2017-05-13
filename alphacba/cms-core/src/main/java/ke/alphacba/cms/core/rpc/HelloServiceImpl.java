package ke.alphacba.cms.core.rpc;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String hello) {
		return "hello from :" + hello;
	}

}
