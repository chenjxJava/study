# liunx
### 一、基本操作
##### 1.杀死tomcat进程
<pre>
1.查看进程号
step1: ps -ef |grep tomcat
sun 5144 1 0 10:21 pts/1 00:00:06 /java/jdk/bin/java -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -Djava.endorsed.dirs=/java/tomcat/common/endorsed -classpath :/java/tomcat/bin/bootstrap.jar:/java/tomcat/bin/commons-logging-api.jar -Dcatalina.base=/java/tomcat -Dcatalina.home=/java/tomcat -Djava.io.tmpdir=/java/tomcat/temp org.apache.catalina.startup.Bootstrap start
//
2.杀掉进程
kill -9 5144
</pre>