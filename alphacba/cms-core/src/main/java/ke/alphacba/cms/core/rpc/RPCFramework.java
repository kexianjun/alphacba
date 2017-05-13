package ke.alphacba.cms.core.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCFramework {
	public static void export(final Object service, int port) throws Exception{
		if (null == service) {
			throw new IllegalArgumentException("service is null");
		}
		try(ServerSocket serverSocket = new ServerSocket(port)){
			while(true){
				final Socket client = serverSocket.accept();
				System.out.println("receive request:" + client);
				 new Thread(new Runnable() {
					
					@Override
					public void run() {
						try(ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream())){
							String methodName = objectInputStream.readUTF();
							Class<?>[] parameterTypes = (Class<?>[]) objectInputStream.readObject();
							Object [] objects = (Object[]) objectInputStream.readObject();
							Method method = service.getClass().getMethod(methodName, parameterTypes);
							Object result = method.invoke(service, objects);
							try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream())){
								
								objectOutputStream.writeObject(result);
							}catch (Exception e) {
								e.printStackTrace();
							}
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T refer(final Class<T> interfaceClass, final String host, final int port){
		return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] {interfaceClass}, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				try(Socket socket = new Socket(host, port);
						ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())){
					
					objectOutputStream.writeUTF(method.getName());
					objectOutputStream.writeObject(method.getParameterTypes());
					objectOutputStream.writeObject(args);
					try(ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())){
						Object result = objectInputStream.readObject();
						return result;
					}catch (Exception e) {
						e.printStackTrace();
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
}
