# 深入分析JavaWeb技术内幕 #
## 第1章 深入Web请求过程 ##
1. B/S架构带来的两方面好处。
    a.客户端使用统一的浏览器（browser）。
    b.服务端（Server）基于统一的HTTP。
	    由于C/S架构使用自定义应用层协议。
2. 一次HTTP请求其实就是一次socket通信。（可使用HttpClient模拟请求）
3. HTTP结构（请求头，响应头）
# 第9章Servlet工作原理解析 #
1. Servlet容器，tomcat、jetty等可以说是servlet容器，因为servlet依赖他们运行。
2. 在Tomcat的容器级别中，Context容器直接管理Servlet在容器中的包装类Wrapper，所以Context容器直接影响Servlet的工作方式。</br>
 
   	从上图可以看出，Tomcat容器分为4个等级，真正管理Servlet的容器是Context容器，一个Context对应一个Web工程，在
Tomcat的配置文件中可以很容易地发现这一点，如下所示：

 
# 第10章 深入理解Session和Cookie #
> 他们本身只是HTTP中的一个配置项，在Servlet规范中也只是对应一个类而已。</br>
Session与Cookie的作用都是为了保持访问用户与后端服务器的交互状态。他们各有优点，也各有缺点。使用Cookie来传递信息时，随着个数和访问量的增加，他占用的网络带宽也很大，事项加入Cookie占用200个字节，如果一天的PV有几亿，那么要占用多少带宽？所以有大访问量时希望用Session，但是Session的致命弱点是不容易在多台服务器之间共享。

1. 一个应用系统有几百台服务器时，如何解决Session共享问题？
2. Cookie被盗、Cookie伪造等问题如何避免？
