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

##### 2.linux软链接
介绍：[linux硬链接与软链接](https://www.cnblogs.com/yfanqiu/archive/2012/06/11/2545556.html)<br>
参考：[linux下建立软链接](http://biyutong.iteye.com/blog/1445699)
<pre>
实例：ln -s /home/gamestat    /gamestat 

linux下的软链接类似于windows下的快捷方式

ln -s a b 中的 a 就是源文件，b是链接文件名,其作用是当进入b目录，实际上是链接进入了a目录

如上面的示例，当我们执行命令   cd /gamestat/的时候  实际上是进入了 /home/gamestat/

值得注意的是执行命令的时候,应该是a目录已经建立，目录b没有建立。我最开始操作的是也把b目录给建立了，结果就不对了

删除软链接：

   rm -rf  b  注意不是rm -rf  b/
// 建立软链接
ln -s  /home/adminwx/picture_storage/ upload
</pre>

