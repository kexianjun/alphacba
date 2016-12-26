package ke.alphacba.cms.service.rpcimpl;

import ke.alphacba.cms.core.rpc.CMSServiceRpc;

public class CMSServiceRpcImpl implements CMSServiceRpc {

	@Override
	public String helloWorld(String name) {
		return "Hello" + name;
	}
}
