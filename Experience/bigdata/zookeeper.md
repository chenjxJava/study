# zookeeper
### 〇、介绍
<pre>
ZooKeeper是Apache Hadoop的子项目。
是一个树型的目录服务，支持变更推送，适合作为dubbo的注册中心，工业强度较高，可用于生产环境。
</pre>
### 一、安装
官网下载，解压，zoo.cfg

[zookeeper的功能以及工作原理](https://www.cnblogs.com/felixzh/p/5869212.html)

[zookeeper windows单机模式和伪集群模式](https://blog.csdn.net/lovesummerforever/article/details/48975703)

### 二、CLS 
<pre>
1.zkCli.sh连接客户端

2.查看目录
> ls /
> ls /zookepper

3.获取值
> get /test

4.创建节点
> create /node1 mytestData

5.删除节点
> delete /node1

6.修改节点
> set /node1 modifyData
</pre>

### 三、java api
### 参考：[Zookeeper的java客户端API使用方法（五）](https://blog.csdn.net/jiuqiyuliang/article/details/56012027)

### [Zookeeper Api(java)入门与应用【重点】](http://www.cnblogs.com/ggjucheng/p/3370359.html)

### [ZooKeeper Java API编程](https://www.cnblogs.com/IcanFixIt/p/7882107.html)

##### [1.ZKUtilsTest.java](https://github.com/chenjxJava/zookeeper/blob/master/src/test/java/com/chenjx/arms/ZkUtilsTest.java)
详情见：https://github.com/chenjxJava/zookeeper
<pre>
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZkUtilsTest {
    @Resource(name = "zk")
    private ZooKeeper zk;

    /**
     * 创建节点，根据HHmmss创建
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void create() throws KeeperException, InterruptedException {
        String hHmmss = new SimpleDateFormat("HHmmss").format(new Date());
        String node = new StringBuffer("/test").append(hHmmss).toString();
        String data = new StringBuffer("data:").append(hHmmss).toString();
        String path = zk.create(node, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
        Assert.assertNotNull(path);
    }

    /**
     * 根绝路径获取数据
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getData() throws KeeperException, InterruptedException {
        byte[] data = zk.getData("/test", null, new Stat());
        System.out.println(new String(data));
        Assert.assertNotNull(data);
    }

    @Test
    public void exist() throws KeeperException, InterruptedException {
        Stat exists = zk.exists("/test184747", false);
        if (exists == null) {
            System.out.println("不存在");
        } else {
            System.out.println("存在");
        }
    }

    @Test
    public void delete() throws KeeperException, InterruptedException {
        //第二个参数为version，-1表示删除所有版本
        //它不支持删除的节点下面还有子节点，只能递归删除
        zk.delete("/test184747", -1);
    }

    @Test
    public void setData() throws KeeperException, InterruptedException {
        //修改znode的值
        zk.setData("/test001", "modify data".getBytes(), -1);

        //测试是否修改成功
        System.out.println(new String(zk.getData("/test001", false, null)));
    }

    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> childrens = zk.getChildren("/", false);
        for (String children : childrens) {
            System.out.println("子节点:"+ children+ "\n");
        }
        //阻塞，测试监听器,且只监听"/"目录
        Thread.sleep(Long.MAX_VALUE);
    }

}
</pre>

##### 2.ZooKeeper目录节点形式CreateMode
<pre>
zk有四种形式的目录节点，及四种CreateMode
两大类，持久化节点和临时节点，自动编号和非自动节点，两两组合。
1. PERSISTENT 
持久化目录节点，存储数据不会丢失。

2、PERSISTENT_SEQUENTIAL
顺序自动编号的持久化目录节点，存储的数据不会丢失，并且根据当前已近存在的节点数自动加 1，然后返回给客户端已经成功创建的目录节点名。

3、EPHEMERAL
临时目录节点，一旦创建这个节点的客户端与服务器端口也就是session 超时，这种节点会被自动删除。

4、EPHEMERAL_SEQUENTIAL
临时自动编号节点，一旦创建这个节点的客户端与服务器端口也就是session 超时，这种节点会被自动删除，并且根据当前已近存在的节点数自动加 1，然后返回给客户端已经成功创建的目录节点名。
</pre>


### 五、dubbo中zookeeper使用
参考：

[Dubbo与注册中心Zookeeper的交互过程](https://blog.csdn.net/qq_27529917/article/details/80632078)

[Dubbo源码学习--注册中心分析](https://www.cnblogs.com/javanoob/p/dubbo_registry.html)

[Dubbo源码学习文章目录](https://www.cnblogs.com/javanoob/p/dubbo_menu.html)