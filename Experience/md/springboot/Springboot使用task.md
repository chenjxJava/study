# Springboot使用task
> 参考：[SpringBoot几种定时任务的实现方式](https://blog.csdn.net/wqh8522/article/details/79224290)
### 一、编写定时任务类
<pre>
@Component
public class MyTask {

    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
        System.out.println("=====>>>>>使用cron  "+ System.currentTimeMillis());
    }
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        System.out.println("=====>>>>>使用fixedRate" + System.currentTimeMillis());
    }
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        System.out.println("=====>>>>>fixedDelay{}" + System.currentTimeMillis());
    }

}
</pre>
### 二、启动类XXXAplication上打上@EnableScheduling//定时任务 
<pre>
这样执行定时任务，会在一个线程池里面串行执行
// Thread.currentThread().getName()可以打印当前线程名

pool-1-thread-1=====>>>>>使用cron  1533784705001
pool-1-thread-1=====>>>>>使用fixedRate1533784707811
pool-1-thread-1=====>>>>>fixedDelay{}1533784707811
pool-1-thread-1=====>>>>>使用cron  1533784710002
pool-1-thread-1=====>>>>>使用fixedRate1533784712811
pool-1-thread-1=====>>>>>fixedDelay{}1533784712813
pool-1-thread-1=====>>>>>使用cron  1533784715001
</pre>

### 三、启动类XXXAplication上面打上@EnableAysnc，此外还需要在@Scheduled注解上，打上@Aysnc
<pre>
// 改造后的定时任务类
@Component
public class MyTask {

    @Async
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
        System.out.println(Thread.currentThread().getName()+ "=====>>>>>使用cron  "+ System.currentTimeMillis());
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        System.out.println(Thread.currentThread().getName()+ "=====>>>>>使用fixedRate" + System.currentTimeMillis());
    }

    @Async
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        System.out.println(Thread.currentThread().getName()+ "=====>>>>>fixedDelay{}" + System.currentTimeMillis());
    }

}

// 执行效果
SimpleAsyncTaskExecutor-1=====>>>>>使用fixedRate1533785038477
SimpleAsyncTaskExecutor-2=====>>>>>fixedDelay{}1533785038477
SimpleAsyncTaskExecutor-3=====>>>>>使用cron  1533785040012
SimpleAsyncTaskExecutor-4=====>>>>>使用fixedRate1533785043475
SimpleAsyncTaskExecutor-5=====>>>>>fixedDelay{}1533785043475
SimpleAsyncTaskExecutor-6=====>>>>>使用cron  1533785045000
SimpleAsyncTaskExecutor-7=====>>>>>使用fixedRate1533785048474
SimpleAsyncTaskExecutor-8=====>>>>>fixedDelay{}1533785048477
SimpleAsyncTaskExecutor-9=====>>>>>使用cron  1533785050002
SimpleAsyncTaskExecutor-10=====>>>>>使用fixedRate1533785053464
SimpleAsyncTaskExecutor-11=====>>>>>fixedDelay{}1533785053478
SimpleAsyncTaskExecutor-12=====>>>>>使用cron  1533785055000
SimpleAsyncTaskExecutor-13=====>>>>>使用fixedRate1533785058461
SimpleAsyncTaskExecutor-14=====>>>>>fixedDelay{}1533785058480
SimpleAsyncTaskExecutor-15=====>>>>>使用cron  1533785060002
SimpleAsyncTaskExecutor-16=====>>>>>使用fixedRate1533785063463
SimpleAsyncTaskExecutor-17=====>>>>>fixedDelay{}1533785063480
SimpleAsyncTaskExecutor-18=====>>>>>使用cron  1533785065002
SimpleAsyncTaskExecutor-19=====>>>>>使用fixedRate1533785068462
SimpleAsyncTaskExecutor-20=====>>>>>fixedDelay{}1533785068484


默认使用SimpleAsyncTaskExecutor，每执行一次都会开启一个新的线程
</pre>

### 四、配置
<pre>
// 1.application.yml中
async:
  corePoolSize: 10
  maxPoolSize: 200
  queueCapacity: 10

// 2.创建配置类
 @Configuration
 @EnableAsync
 public class AsyncConfig {
 @Value(value = "${async.corePoolSize}")
 private int corePoolSize;

 @Value(value = "${async.maxPoolSize}")
 private int maxPoolSize;

 @Value(value = "${async.queueCapacity}")
 private int queueCapacity;

 @Bean
 public Executor taskExecutor() {
     ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
     executor.setCorePoolSize(corePoolSize);
     executor.setMaxPoolSize(maxPoolSize);
     executor.setQueueCapacity(queueCapacity);
     executor.initialize();
     return executor;
 }
}
</pre>


### 附录：@Scheduled源码
<pre>
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.scheduling.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(Schedules.class)
public @interface Scheduled {
    String cron() default "";

    String zone() default "";

    long fixedDelay() default -1L;

    String fixedDelayString() default "";

    long fixedRate() default -1L;

    String fixedRateString() default "";

    long initialDelay() default -1L;

    String initialDelayString() default "";
}
</pre>


