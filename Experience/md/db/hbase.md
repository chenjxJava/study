# HBase
HDFS(分布式文件系统)+HBase
[<br>官网](http://hbase.apache.org/)hbase.apache.org
### 1.HBase的应用场景及特点
<pre>
// 定位
海量数据存储
准实时查询（百毫秒之内）

// 应用场景
后期需要对进行数据分析
交通，
金融，
电商，
移动，

// HBase的特点
a.容量大，HBase单表可以有百亿行、百万列，数据矩阵横向和纵向两个维度所支持的数据量级都非常具有弹性。
(传统数据库，单表数据不能超过500w，列不能超过30列)
b.面向列,HBase是面向列的存储和权限控制并支持独立检索。
列式存储，器数据在表中是按照某列存储的，这样在查询只需要少数几个字段的时候，能大大减少读取的数据量。
简单说，就是可以单独增加列，对列进行各种操作
c.多版本，HBase每一个列的数据存储有多个Version。
d.稀疏性，为空的列并不占用存储空间，表可以设计的非常稀疏。
e.扩展性，底层依赖于HDFS
f.高可靠性，WAL机制保证了数据写入时不会因集群异常而导致写入数据丢失：Replication机制保证了在集群出现严重的问题时，数据不会发生丢失或损坏。而且HBase底层使用HDFS HDFS本身也有备份。
g.高性能，底层的LSM数据结构和Rowkey有序排列等架构上的独特涉及，使得HBase具有非常高的写入性能。region切分、主键索引和缓存机制使得HBase在海量数据下具备一定的随机读取性能，该性能针对Rowkey的查询能够到大毫秒级别。
</pre>

### 2.HBase的概念与定位
<pre>
a.如何选择合适HBase的版本？
官网版本：http://archive.apache.org/dist/
CDH版本：http://archive.cloudera.com/cdh5/
0.98
</pre>
### 3.HBase架构体系与设计模型
<pre>
先安装zookeeper,HDFS,才能安装HBase
主要有两个进程，RegionServer，master

HBase表结构模型并举例说明
列簇，

a.一张表的列簇不会超过5个
b.每个列簇中的猎术没有限制
c.列只有插入数据后存在
d.列在列簇中是顺序排序的
</pre>
### 4.HBase的安装部署
<pre>
推荐版本：
hadoop-2.5.0.tar.gz
hbase-0.98.6-cdh5.3.0.tar.gz
jdk-7u67-linux-x64.tar.gz
zookeeper-3.4.5-cdh5.10.0.tar.gz

1.JDK1.7

2.hadoop2.5.0
 a.解压Hadoop-2.5.0并安装
 b.配置hadoop-env.sh

3.zookeeper
</pre>
### 5.HBase shell使用

## HBase表与关系型数据库表结构的对比
<pre>
1.列动态增加
2.数据自动切分
3.高并发读写
4.不支持条件查询
</pre>
