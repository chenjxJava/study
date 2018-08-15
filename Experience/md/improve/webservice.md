# Webservice

### 一、jws调用webservice
> 参考：[极致精简的webservice例子](https://www.cnblogs.com/fengwenzhee/p/6915606.html)


### 二、Spring Boot+CXF搭建WebService
> 参考：[Spring Boot+CXF搭建WebService](https://www.cnblogs.com/xibei666/p/8970549.html)
 
### 三、运行wsdl2java工具
<pre>
// cxf进行客户端代码生成
wsdl2java -d com/ -o e:/2018081401 -client http://localhost:8989/WS_Server/WebService?wsdl
</pre>

<pre>
在cmd命令中输入：wsdl2java -d D:\\src -client http://api.xxx.cn/xxxAPI/service/auditResBatchQueryService?wsdl

（D:\\src 是客户端程序代码所在的目录，http://api.xxx.cn/xxxAPI/service/auditResBatchQueryService?wsdl 是 定义webservice的地址）

附wsdl2java用法：

wsdl2java -p com -d D:\\src -all  xx.wsdl

-p  指定其wsdl的命名空间，也就是要生成代码的包名:

-d  指定要产生代码所在目录

-client 生成客户端测试web service的代码

-server 生成服务器启动web  service的代码

-impl 生成web service的实现代码

-ant  生成build.xml文件

-all 生成所有开始端点代码：types,service proxy,,service interface, server mainline, client mainline, implementation object, and an Ant build.xml file.
</pre>

### 三、FAQ1： webservice客户端异常( undefinedelement declaration 's:schema')
<pre>
//对应.net的webservice
1.下载wsdl文件（另存为.wsdl文件）
2.删除掉一下元素
&lt;s:element ref="s:schema">
3.wsdl2java 生成
</pre>