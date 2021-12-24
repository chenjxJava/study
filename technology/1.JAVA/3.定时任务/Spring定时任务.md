# Spring定时任务
案例：[Spring整合Quartz分布式调度](https://my.oschina.net/OutOfMemory/blog/1790200)<br>
学习：[quartz-core-learning](https://github.com/dufyun/quartz-core-learning)<br>
参考：[Spring定时任务的几种实现](http://gong1208.iteye.com/blog/1773177)
### 一、使用场景
<pre>
近日项目开发中需要执行一些定时任务，比如需要在每天凌晨时候，分析一次前一天的日志信息，借此机会整理了一下定时任务的几种实现方式，
由于项目采用spring框架，所以我都将结合spring框架来介绍。 
</pre>

### 二、分类
<pre>
从实现的技术上来分类，目前主要有三种技术（或者说有三种产品）：
1.Java自带的java.util.Timer类，这个类允许你调度一个java.util.TimerTask任务。使用这种方式可以让你的程序按照某一个频度
执行，但不能在指定时间运行。一般用的较少，这篇文章将不做详细介绍。
2.使用Quartz，这是一个功能比较强大的的调度器，可以让你的程序在指定时间执行，也可以按照某一个频度执行，配置起来稍显复杂，稍后会详细介绍。
3.Spring3.0以后自带的task，可以将它看成一个轻量级的Quartz，而且使用起来比Quartz简单许多，稍后会介绍。

从作业类的继承方式来讲，可以分为两类：
1.作业类需要继承自特定的作业类基类，如Quartz中需要继承自org.springframework.scheduling.quartz.QuartzJobBean；java.util.Timer中需要继承自java.util.TimerTask。
2.作业类即普通的java类，不需要继承自任何基类。
注:个人推荐使用第二种方式，因为这样所以的类都是普通类，不需要事先区别对待。

从任务调度的触发时机来分，这里主要是针对作业使用的触发器，主要有以下两种：
1.每隔指定时间则触发一次，在Quartz中对应的触发器为：org.springframework.scheduling.quartz.SimpleTriggerBean
2.每到指定时间则触发一次，在Quartz中对应的调度器为：org.springframework.scheduling.quartz.CronTriggerBean
注：并非每种任务都可以使用这两种触发器，如java.util.TimerTask任务就只能使用第一种。Quartz和spring task都可以支持这两种触发条件。
</pre>

### 三、名词解释
<pre>
1.作业类 
1.1 需要继承QuartzJobBean
1.2 spring配置文件中配置JobDetailBean

2.作业调度的触发方式（触发器）
// 第一种SimpleTriggerBean，只支持按照一定频度调用任务，如每隔30分钟运行一次。
org.springframework.scheduling.quartz.SimpleTriggerBean
// 第二种CronTriggerBean，支持到指定时间运行一次，如每天12:00运行一次等。
org.springframework.scheduling.quartz.CronTriggerBean

3.调度工厂
org.springframework.scheduling.quartz.SchedulerFactoryBean
</pre>

// 加载类
ClassUtils-111-return classLoaderToUse.loadClass(name);

### 四、quartz好好学习
[Quartz学习——Spring和Quartz集成详解（三）](http://blog.csdn.net/u010648555/article/details/54891264)
http://blog.csdn.net/u010648555/article/details/54891264

### 五、cron表达式
[quartz 时间表达式----- Cron表达式详解](https://blog.csdn.net/u012888052/article/details/53907413)