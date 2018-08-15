# zookeeper
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
参考：[Zookeeper的java客户端API使用方法（五）](https://blog.csdn.net/jiuqiyuliang/article/details/56012027)

[Zookeeper Api(java)入门与应用(转)](http://www.cnblogs.com/ggjucheng/p/3370359.html)

### 四、zkUtils