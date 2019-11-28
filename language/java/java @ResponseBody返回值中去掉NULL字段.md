# java @ResponseBody返回值中去掉NULL字段
参考：http://www.bubuko.com/infodetail-1642438.html<br>
需要同时添加两个位置：

1、annotation-driven过滤
<pre>
<mvc:annotation-driven>
    <mvc:message-converters register-defaults="true">
        <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
            <property name="objectMapper">
                <bean class="com.fasterxml.jackson.databind.ObjectMapper">
                    <property name="serializationInclusion">
                        <util:constant static-field="com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL" />
                    </property>
                </bean>
            </property>
        </bean>
    </mvc:message-converters>
</mvc:annotation-driven>
</pre>

2、RequestMapping过滤
<pre>
<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
    <property name="messageConverters">
        <list>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean id="jacksonObjectMapper" class="com.fasterxml.jackson.databind.ObjectMapper">
                        <property name="dateFormat">
                            <bean class="java.text.SimpleDateFormat">
                                <constructor-arg type="java.lang.String" value="yyyy-MM-dd HH:mm:ss" />
                            </bean>
                        </property>
                        <property name="serializationInclusion">
                            <util:constant static-field="com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL" />
                        </property>
                    </bean>
                </property>
                <property name="supportedMediaTypes">
                    <list>
                        <value>application/json;charset=UTF-8</value>
                        <value>application/x-www-form-urlencoded</value>
                    </list>
                </property>
            </bean>
            <bean class="org.springframework.http.converter.ByteArrayHttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>image/jpeg</value>
                        <value>image/png</value>
                    </list>
                </property>
            </bean>
        </list>
    </property>
</bean>
</pre>