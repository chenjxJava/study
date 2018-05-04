# 异常总结
## 一、JAVA部分
### 1.nginx报错
<pre>
// 网页出现如下内容： 
An error occurred.
Sorry, the page you are looking for is currently unavailable.
Please try again later.
If you are the system administrator of this resource then you should check theerror log for details.
Faithfully yours, nginx.

解决方法：
nginx的404返回，一般情况下是因为文件不存在，然后的提示。
但根据你的描述，十次请求的话，就有一次head出现404，那文件存在以前权限可以排除
1、试检查一下nginx.conf的设置，是不是有limit的设置，比如limit_zone、limit_conn，这些参数也是有影响的。
2、检查一下防火墙，是不是有相关的设置限制。
3、检查一下nginx.conf的设置，看看有没有valid_referers none blocked的防链设置。

ps:提示已经很清楚了，请查看nginx错误日志来分析解决问题
</pre>

### 2.mybatis异常invalid comparison: java.util.Date and java.lang.String
<pre>
原因：Mapper.xml配置文件中，date类型的字段做了不为空串的校验。

&lt;if test="autherDate!=null"> //不能是"autherDate!=null and autherDate!=null"
   autherDate=#{autherDate},
&lt;/if>
</pre>

## 二、Idea部分
### 1.【idea-git】unable to find remote helper for https
解决方法：git中的脚本命令找不到，去配置下path就好了
