# spark(内存计算)
### 1.下载 (apache spark)


spark访问：
 [http://192.168.88.128:8080/](http://192.168.88.128:8080/)

spark quick-start:
[https://spark.apache.org/docs/latest/quick-start.html](https://spark.apache.org/docs/latest/quick-start.html)

[spark-submit提交任务的方式](https://blog.csdn.net/u010181136/article/details/53514962)

[提交任务到spark（以wordcount为例）](https://www.cnblogs.com/Mrwan/p/7380574.html)

### 2.
参考：[Spark Standalone Mode 单机启动Spark -- 分布式计算系统spark学习(一)](https://www.cnblogs.com/zhangqingping/p/4352977.html)
<pre>
1.启动master
> ./sbin/start-master.sh

2.附加一个worker到master
> ./spark-class org.apache.spark.deploy.worker.Worker  spark://dubbo-server:7077

3.启动spark shell终端
> ./bin/spark-shell

4.提交作业
./spark-submit --class com.wyc.spark.SparkWordCount --master spark://dubbo-server:7077 /home/hadoop/hive-0.0.1-SNAPSHOT.jar "/home/hadoop/1.txt"

./spark-submit --class com.wyc.spark.SparkWordCount --master spark://dubbo-server:7077 /home/hadoop/spark-2.0.2-bin-hadoop2.7/examples/jars/spark.jar "/home/hadoop/1.txt" 

./spark-submit --class com.wyc.stream.HdfsWordCount --master spark://100.124.10.11:7077 /home/ "hdfs://120.77.176.193/demo" 
</pre>

# windows环境下安装spark(可以进行debug)
[windows环境下安装spark](https://blog.csdn.net/u011521890/article/details/78577582)

# mvn 依赖树
dependency:tree -Dverbose -Dincludes=io.netty:netty-all