# 一、idea创建springboot项目
参考：[使用IDEA创建SpringBoot项目](https://blog.csdn.net/lom9357bye/article/details/69677120)

![](https://github.com/chenjxJava/photos/blob/master/springboot/springboot_init1.png?raw=true)

![](https://github.com/chenjxJava/photos/blob/master/springboot/springboot_init2.png?raw=true)

![](https://github.com/chenjxJava/photos/blob/master/springboot/springboot_init3.png?raw=true)

因为只是简单的测试Controller功能，只需要勾选Web即可，其它可根据具体需求勾选

![](https://github.com/chenjxJava/photos/blob/master/springboot/springboot_init4.png?raw=true)

![](https://github.com/chenjxJava/photos/blob/master/springboot/springboot_init5.png?raw=true)

上图为项目初始目录，将”.mvn、mvnw、mvnw.cmd”文件删掉

### 问题一，IDEA新建项目时，没有Spring Initializr选项
解决办法如下：

在settings -> Plugins 里面搜索spring boot，勾选上，然后再重启下idea，就可以了。如果Plugins里面没有spring boot的话，先安装下，再勾选

# 二、springboot项目三种启动方式
1.idea 右键run

2.mvn springboot:run

3.mvn install后，java -jar xxx.jar

# 三、项目配置
1.默认配置

application.properties
server.port=8081
server.context-path=/girl

application.yml(推荐)
server:
	prot: 8081
    context-path: /girl

2.配置文件依赖注入
application.yml
<pre>
server:
	port: 8081
cupSize: B
age: 8
content: "cupSize: ${cupSize}, age: ${age}"

@Value("${cupSize}")
private String cupSize;

@Value("${age}")
private Integer age;


@Value("${content}")
private String content;
</pre>

### 四、Controller的使用
<pre>
@Controller 处理http请求
@RestController Spring4之后新加的注解，原来返回json需要@ResponseBody配合@Controller
@ResquestMapping 配置url映射

@Controller必须配合模板使用
1.spring-boot-starter-theamleaf
2.resourse目录下新增/templates


@PathVariable 获取url中的数据
@RequestParam 获取请求参数的值
@GetMapping 组合注解 = @RequestMapping(value = "/index", method = RequestMethod.GET)
</pre>

# 五、数据库操作
<pre>
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
</pre>
Spring-Data-Jpa
JPA(Java Persistence API)定义了一系列对象持久化的标准，母亲啊实现这一规范的产品有Hibernate、TopLink等。

RESTful API设计

六、数据库，事务性操作
@Transational