# Spring配置
### 1.基本配置———springContext.xml
<pre>
&lt;?xml version="1.0" encoding="UTF-8"?&gt;  
&lt;beans xmlns="http://www.springframework.org/schema/beans"  
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"  
    xmlns:mvc="http://www.springframework.org/schema/mvc"  
    xsi:schemaLocation="http://www.springframework.org/schema/beans    
                        http://www.springframework.org/schema/beans/spring-beans-3.1.xsd    
                        http://www.springframework.org/schema/context    
                        http://www.springframework.org/schema/context/spring-context-3.1.xsd    
                        http://www.springframework.org/schema/mvc    
                        http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd"&gt;  
    &lt;import resource="classpath:config/spring-mybatis.xml" /&gt;
    &lt;import resource="classpath:config/spring-service.xml" /&gt;
    &lt;import resource="classpath:config/spring-shiro.xml" /&gt;
    &lt;import resource="classpath:config/spring-context-jedis.xml" /&gt;
    //配置加载properties
    &lt;context:property-placeholder location="classpath:config/config.properties" ignore-unresolvable="true"/&gt;  
&lt;/beans&gt; 
</pre>