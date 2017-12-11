# Spring Security
> 简单说，就是根据众多的拦截器对url拦截，以此来管理权限。

### 一、spring security使用分类：

> 如何使用spring security，相信百度过的都知道，总共有四种用法，从简到深为：<br>
> 　1、不用数据库，全部数据写在配置文件，这个也是官方文档里面的demo；<br>
> 　2、使用数据库，根据spring security默认实现代码设计数据库，也就是说数据库已经固定了，这种方法不灵活，而且那个数据库设计得很简陋，实用性差；<br>
> 　3、spring security和Acegi不同，它不能修改默认filter了，但支持插入filter，所以根据这个，我们可以插入自己的filter来灵活使用；<br>
> 　4、暴力手段，修改源码，前面说的修改默认filter只是修改配置文件以替换filter而已，这种是直接改了里面的源码，但是这种不符合OO设计原则，而且不实际，不可用。<br>
>  
　因为本文准备介绍第三种方法，所以面向的读者是已经具备了spring security基础知识的。不过不要紧，读者可以先看一下[这个教程](http://download.csdn.net/detail/u012367513/7826801)，看完应该可以使用第二种方法开发了。

### 二、原理和教程
[参考：spring security的原理及教程](http://www.importnew.com/20612.html)<br>
使用众多的拦截器对url拦截，以此来管理权限。但是这么多拦截器，笔者不可能对其一一来讲，主要讲里面核心流程的两个。

首先，权限管理离不开登陆验证的，所以登陆验证拦截器AuthenticationProcessingFilter要讲；还有就是对访问的资源管理吧，所以资源管理拦截器AbstractSecurityInterceptor要讲；但拦截器里面的实现需要一些组件来实现，所以就有了AuthenticationManager、accessDecisionManager等组件来支撑。

现在先大概过一遍整个流程，用户登陆，会被AuthenticationProcessingFilter拦截，调用AuthenticationManager的实现，而且AuthenticationManager会调用ProviderManager来获取用户验证信息（不同的Provider调用的服务不同，因为这些信息可以是在数据库上，可以是在LDAP服务器上，可以是xml配置文件上等），如果验证通过后会将用户的权限信息封装一个User放到spring的全局缓存SecurityContextHolder中，以备后面访问资源时使用。

访问资源（即授权管理），访问url时，会通过AbstractSecurityInterceptor拦截器拦截，其中会调用FilterInvocationSecurityMetadataSource的方法来获取被拦截url所需的全部权限，在调用授权管理器AccessDecisionManager，这个授权管理器会通过spring的全局缓存SecurityContextHolder获取用户的权限信息，还会获取被拦截的url和被拦截url所需的全部权限，然后根据所配的策略（有：一票决定，一票否定，少数服从多数等），如果权限足够，则返回，权限不够则报错并调用权限不足页面。

虽然讲得好像好复杂，读者们可能有点晕，不过不打紧，真正通过代码的讲解在后面，读者可以看完后面的代码实现，再返回看这个简单的原理，可能会有不错的收获。