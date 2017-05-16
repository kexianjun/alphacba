package ke.alphacba.cms.core.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZKTest1 {
	
	
	public static void main(String[] args) throws Exception {
		Object lock = new Object();
		ZooKeeper zk = new ZooKeeper("localhost:2181,localhost:2182,localhost:2183", 1000, new Watcher(){
			@Override
			public void process(WatchedEvent event) {
				synchronized (lock) {
					System.out.println("已经触发了：" + event.getType() + "事件" + event.getPath());
				}
			}
		});
		
		zk.create("/testRootPath", "testRootPath".getBytes(), 
				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);
		System.out.println(new String("data:" + zk.getData("/testRootPath", false, null)));
		// 取出子目录节点列表
		System.out.println(zk.getChildren("/testRootPath", true));
		// 修改子目录节点数据
		zk.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);
		System.out.println("目录节点状态：[" + zk.exists("/testRootPath", true) + "]");
		// 创建另外一个子目录节点
		zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);
		System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo", true, null)));
		System.out.println(zk.getSessionId());
		// 删除子目录节点
		zk.delete("/testRootPath/testChildPathTwo", -1);
		zk.delete("/testRootPath/testChildPathOne", -1);
		// 删除父目录节点
		zk.delete("/testRootPath", -1);
		// 关闭连接
		zk.close();
	}
}
