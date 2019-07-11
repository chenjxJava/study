# spark算子
### 1.text, filter, map, flatmap
[spark RDD算子（二） filter,map ,flatMap](https://blog.csdn.net/T1DMzks/article/details/70198393)
<pre>
1)textRDD
scala> var textRDD = sc.textFile("/home/hadoop/2.txt")
textRDD: org.apache.spark.rdd.RDD[String] = /home/hadoop/2.txt MapPartitionsRDD[11] at textFile at <console>:24

scala> textRDD.collect()
res20: Array[String] = Array(this is hello world, this is test, this is a, this is test)


2)filterRDD
scala> textRDD.filter(line=>line.contains("test")).collect()
res21: Array[String] = Array(this is test, this is test)


3)mapRDD
scala> val mapRDD = textRDD.map(line=>line.split("\\s+"))
mapRDD: org.apache.spark.rdd.RDD[Array[String]] = MapPartitionsRDD[13] at map at <console>:26

scala> mapRDD.collect()
res22: Array[Array[String]] = Array(Array(this, is, hello, world), Array(this, is, test), Array(this, is, a), Array(this, is, test))


(4)flatMapRDD
scala> val flatMapRDD = textRDD.flatMap(lines=>lines.split("\\s"))
flatMapRDD: org.apache.spark.rdd.RDD[String] = MapPartitionsRDD[15] at flatMap at <console>:28

scala> flatMapRDD.collect()
res32: Array[String] = Array(this, is, hello, world, this, is, test, this, is, a, this, is, test)
</pre>
