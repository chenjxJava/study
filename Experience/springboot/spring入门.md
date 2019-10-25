# springboot

[SpringBoot非官方教程 | 第二十五篇：2小时学会springboot](https://blog.csdn.net/forezp/article/details/61472783)

### 一、入门

[SpringBoot官网](https://spring.io/projects/spring-boot/)

[Building a RESTful Web Service(推荐)](https://spring.io/guides/gs/rest-service/)


#### 1.简介

Springboot是一个简化Spring开发的框架，用来监护spring应用开发，约定大约配置，去繁就简，just run就能创建一个独立的，产品级的应用。

我们在使用SpringBoot时只需要配置相应的SpringBoot就可以用所有Spring组件，简单的说，springboot就是整合了很多优秀的框架，不用我们自己手动的去写一堆xml配置然后进行配置。从本质上来说，SpringBoot就是Spring,它做了那些没有它你也会去做的SpringBean配置。

**关键点：**

- 约定俗成，组件化

#### 2.优点

- 快速创建独立运行的Spring项目以及与主流框架集成
- 使用嵌入式Servlet容器，应用无需打成WAR包
- starters自动依赖与版本控制
- 大量的自动配置，简化开发，也可修改默认值
- 无需配置XML，无代码生成，开箱及用
- 准生产环境的运行时应用监控
- 与云计算的天然集成



#### 3.单体应用与微服务

单体应用模块写在一起，臃肿，耦合度高。

微服务，模块单独部署，不同模块可以进行升级部署，各小型服务之间通过http通信。



#### 4.SpringBoot的核心特点

**微服务：**

- 使用SpringBoot可以生产独立的微服务功能单元

**自动配置**

- 针对很多Spring应用程序常见的应用功能，SpringBoot能自动提供相关配置

**起步依赖**

- 告诉SpringBoot需要什么功能，它就能引入需要的库。



### 二、FAQ

#### 1.idea项目下自动生成的mvnw文件

 Maven是一个常用的构建工具，但是Maven的版本和插件的配合并不是那么完美，有时候你不得不切换到一个稍微旧一些的版本，以保证所有东西正常工作。 

而Gradle提供了一个Wrapper，可以很好解决版本切换的问题，当然更重要的是不需要预安装Gradle。

Maven虽然没有官方的Wrapper，但是有一个第三方的Wrapper可以使用。

安装很简单 `mvn -N io.takari:maven:wrapper `[ -Dmaven=3.1.0 ]，安装完成如下

 使用的时候直接 `./mvnw clean install `即可，它会自动下载最新版本来执行。 

