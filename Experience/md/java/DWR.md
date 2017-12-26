# DWR 

### 一、服务器推送技术
##### 1.分类

1.1 java web 服务器推送技术
<br>comet服务拉取消息

　1.1.1 comte4j<br> 
　参考：[java web 服务器推送技术--comet4j](http://blog.csdn.net/hla199106/article/details/46928489)

　1.1.2 dwr [官网](http://directwebremoting.org/dwr/index.html/)<br>
　参考：[DWR实现服务器推 简单demo](http://blog.csdn.net/u013074465/article/details/50222521)

1.2 app服务器推送技术<br>
　参考：[服务器推送技术常用的三个解决方案](http://blog.csdn.net/ligaofeng/article/details/50601460)
### 二、DWR介绍
<pre>
DWR（Direct Web Remoting）是一个用于改善web页面与Java类交互的远程服务器端Ajax开源框架，可以帮助开发人员开发包含AJAX技术的网站。
它可以允许在浏览器里的代码使用运行在WEB服务器上的JAVA函数，就像它就在浏览器里一样。
DWR就是解决前端和后台之间交互的工具，它可以将后台中的方法转成javascript的方法，在页面中可以直接调用后台方法。

简单说，dwr框架，可以将java中的类和方法转换成js中的方法。
</pre>


### 一、dwr.xml配置
<pre>
<dwr>
 <allow>
 <create creator="new" javascript="testClass" >
 <include method="testMethod1"/>
 </create>
 </allow>
</dwr>

<allow>标签中包括可以暴露给javascript访问的东西。
<create>标签中指定javascript中可以访问的java类名，并定义DWR应当如何获得要进行远程的类的实例。creator="new"属性指定java类实例的生
成方式，new意味着DWR应当调用类的默认构造函数来获得实例，其他的还有spring方式，通过与IOC容器Spring进行集成来获得实例等等。
javascript=" testClass "属性指定javascript代码访问对象时使用的名称。
标签指定要公开给javascript的java类名。
<include>标签指定要公开给javascript的方法。不指定的话就公开所有方法。
<exclude>标签指定要防止被访问的方法。
注意：include和exclude不能同时存在
</pre>

<pre>
注意：
  1.dwr.xml中的create标签的creator属性，设置类的生成方式。
  creator="new"，
</pre>

### 四、DWR使用步骤
参考:[如何搭建dwr开发环境以及dwr入门示例展示](https://jingyan.baidu.com/article/8ebacdf02f4f2b49f65cd5df.html)
###### 1.创建maven工程
###### 2.修改web.xml,添加DwrServlet，对访问/dwr/*的文件进行拦截
###### 3.编写java类以及方法，可转换成js方法
###### 4.创建dwr.xml文件，配置转换js方法的类和方法
###### 5.创建dwr.xml文件
