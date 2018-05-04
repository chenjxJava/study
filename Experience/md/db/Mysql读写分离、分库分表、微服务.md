# 一、Mysql读写分离
参考：[使用Spring AOP实现MySQL数据库读写分离案例分析](https://blog.csdn.net/xlgen157387/article/details/53930382)

# 二、Mysql分库分表方案
参考：[Mysql分库分表方案](https://blog.csdn.net/achuo/article/details/72229236)

分表分库，最简单的都是通过取模的方式进行路由。

数据库分表可以解决单表海量数据的查询性能问题，分库可以解决单台数据库的并发访问压力问题。

# 三、微服务实战：从架构到发布
参考：[微服务实战：从架构到发布（一）](https://blog.csdn.net/achuo/article/details/79107261)

<pre>
微服务:SOA(Service Oriented Architecture)

1.根据业务将服务分离

2.接口采用rest/thrift风格

3.服务通信，使用api网关，订阅发布方式

4.不同的服务对应不同地数据库
</pre>