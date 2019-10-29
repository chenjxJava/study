# SpringCloud





## 一、Eureka + Ribbon
参考：

[SpringCloud学习笔记（一） 搭建一个SpringCloud[Ribbon部分]](https://blog.csdn.net/q15150676766/article/details/80931187)

[SpringBoot2.2.0.REALESE+SpringCloud项目地址](https://github.com/chenjxJava/springcloud-learn/tree/master/ribbon)



> 注册中心,
> provider   --------->eureka     ----------> consumer
>
> Ribbon @loadBanlaced



## 二.Actuator
>[springcloud-eureka启动报错，提示The following method did not exist: org.springframework.boot.actuate.health](https://blog.csdn.net/cmqwan/article/details/89410114)

**1.springcloud-eureka启动报错，提示The following method did not exist: org.springframework.boot.actuate.health.CompositeHealthIndicato**

```
***************************
APPLICATION FAILED TO START
***************************

Description:

An attempt was made to call a method that does not exist. The attempt was made from the following location:

    org.springframework.cloud.client.discovery.health.DiscoveryCompositeHealthIndicator.<init>(DiscoveryCompositeHealthIndicator.java:42)

The following method did not exist:

    org.springframework.boot.actuate.health.CompositeHealthIndicator.<init>(Lorg/springframework/boot/actuate/health/HealthAggregator;)V

The method's class, org.springframework.boot.actuate.health.CompositeHealthIndicator, is available from the following locations:

    jar:file:/C:/Users/zhao/.m2/repository/org/springframework/boot/spring-boot-actuator/2.2.0.M1/spring-boot-actuator-2.2.0.M1.jar!/org/springframework/boot/actuate/health/CompositeHealthIndicator.class

It was loaded from the following location:

    file:/C:/Users/zhao/.m2/repository/org/springframework/boot/spring-boot-actuator/2.2.0.M1/spring-boot-actuator-2.2.0.M1.jar


Action:
```

#####  这个是启动的时候找不到方法，每次迭代，或删或减或重构，某些类兴许就被删掉了。所以需要查看springcloud和springboot的版本对应关系，springcloud使用英文单词作为版本，springboot是用数字作为版本。 

```
Table 1. Release train Spring Boot compatibility
Release Train	Boot Version
Greenwich 2.1.x(可用2.1.4.RELEASE)

Finchley 2.0.x(可用2.0.5.RELEASE)

Edgware 1.5.x

Dalston 1.5.x
```

 最新对应关系可以查看官网https://spring.io/projects/spring-cloud 

