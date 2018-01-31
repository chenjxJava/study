# 关于发布
### 一、如何修改jsp页面内容
> 因为只是修改jsp页面，项目需要重启，那怎么做呢？
<pre>
1.使用XShell连接服务器

2.点击xftp,就是这个图标

3.找到/home/wanandroid/tomcat71/webapps/ROOT下的index.jsp

4.右键，用记事本编辑，修改完保存就ok了。
</pre>
![](https://github.com/chenjxJava/photos/blob/master/linux/xshell_%E4%BF%AE%E6%94%B9index%E9%A1%B5%E9%9D%A2.gif?raw=true)

### 二、如何部署发布项目
> 1.打war包 2.上传war包 3.重启tomcat

<pre>
1.使用idea打war包，其他方式，自行百度

2.使用xshell,连接服务器
<pre>
// 2.1关闭tomcat
cd /home/wanandroid/tomcat71/
./bin/shutdown.sh

// 2.2删除之前的war包,以及文件夹
rm -rf api*
</pre>
3.使用xshell-xftp，上传war包
ps:上传到，/home/wanandroid/tomcat71/webapps下

4.重启tomcat
cd /home/wanandroid/tomcat71/
./bin/startup.sh

5.查看日志，没有报错，说明启动正常
tail -f catalina.out    //ctrl + c 退出

ps:阿里云重启tomcat，要等上个5分钟左右 
</pre>