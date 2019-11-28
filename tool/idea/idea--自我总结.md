# idea— —自我总结
#### 导语  
> 个人认为idea是功能比eclipse强大N多倍的一个开发工具，能够大幅提升开发者的开发速度。[idea官网下载](https://www.jetbrains.com/idea//)<br>
[IntelliJ IDEA 使用教程-极客学院](http://wiki.jikexueyuan.com/project/intellij-idea-tutorial/interface-introduce.html)<br>
[IntelliJ IDEA 2017.3 Help官方文档](http://www.jetbrains.com/help/idea/configuring-projects.html)
#### 一、idea安装
1.访问官网下载.exe文件，完成默认安装
![image](https://github.com/chenjxJava/photos/blob/master/idea/20171121144825.png?raw=true)
2.idea激活方式 | help-->Register（失效了！！！）

![](https://github.com/chenjxJava/photos/blob/master/idea/20171121152329.png?raw=true)
<pre>
填入下面的license server:
  http://intellij.mandroid.cn/
  http://idea.imsxm.com/
</pre>
最新激活方式
参考：[Idea 2017.3以后版本的破解](http://www.cnblogs.com/legoo/p/8214165.html)
<pre>
1.
</pre>

### 重要：project和module概念
参考：![](http://blog.csdn.net/sdujava2011/article/details/46801619)
<pre>
一个Project是可以由很多个Module组成的。 
比如我下面的leetcode项目，leetcode是题目是有easy、medium和Hard三种级别的，所以我的项目中应该会有三个分别对应的Module，我还没开始做
Hard，所以少了Hard的目录，然后无论是哪个Module，我都会使用到我自己封装的日志工具，所以我有一个专门的Module用来放工具类，Module之间可以相互引用。
</pre>
![](https://github.com/chenjxJava/photos/blob/master/idea/idea_project_module.png?raw=true)

#### 二、基本配置
##### 1.配置jdk

![image](https://github.com/chenjxJava/photos/blob/master/idea/idea_jdk1.png?raw=true)
![image](https://github.com/chenjxJava/photos/blob/master/idea/idea_jdk2.png?raw=true)
##### 2.配置tomcat
##### 3.配置svn
##### 4.配置maven（不用配置）
##### 5.设置默认注释 setting-Editor-File and Code-Files-Class
![image](https://github.com/chenjxJava/photos/blob/master/idea/idea_comment.png?raw=true)
-     /** 
    	* @Author: ${USER}
    	* @Description: ${DESCRIPTION}
    	* @Date: Created in ${TIME} ${DATE}
    	* @Modified By:
    	*/

##### 6.配置datasource
![](https://github.com/chenjxJava/photos/blob/master/idea/datasource.png?raw=true)

#### 三、Idea学习网站 ( 参考：http://www.youmeek.com/)
1. [IDEA 使用入门](http://blog.csdn.net/fatshaw/article/details/52126962)

2. idea快捷键
* http://blog.csdn.net/dc_726/article/details/42784275 十大快捷神键
* http://www.cnblogs.com/zhangpengshou/p/5366413.html 快捷键大全


#### 四、好用
1. 快速生成表格

![image](https://github.com/chenjxJava/photos/blob/master/idea//idea%E5%BF%AB%E9%80%9F%E7%94%9F%E6%88%90%E8%A1%A8%E6%A0%BC.gif?raw=true)
2. 

#### 五、FAQ
##### 1. Idea maven工程的java包下xml文件是不会被打进jar包的,在pom文件中添加
>      <build>
    		<resources>
    			<resource>
    		    	<directory>src/main/java</directory>
    				<includes>
    					<include>**/*.xml</include>
    				</includes>
    			</resource>
    		</resources>
    	</build>

##### 2. 修改idea项目名称

- Ctrl+shift+alt+s--->修改工程结构

![image](https://github.com/chenjxJava/photos/blob/master/idea/idea_pro_modu_name.png?raw=true)
-  .idea-->.name
 
![image](https://github.com/chenjxJava/photos/blob/master/idea/idea_project_name.png?raw=true)
 
##### 3. idea切换svn

![image](https://github.com/chenjxJava/photos/blob/master/idea/idea_svn_change.png?raw=true)
 
##### 4. Configuration Error: deployment source '(projectname): war exploded' is not valid

![image](https://github.com/chenjxJava/photos/blob/master/idea/idea_make_artifact.png?raw=true)
 
##### 5. Idea不能Alt+Enter提示生成变量(代码助手)
- File-->setting-->editor-->intentions-->Introduce local variable

![image](https://github.com/chenjxJava/photos/blob/master/idea/idea_code_intentions.png?raw=true)

##### 6.控制台中文乱码问题
jvm配置中，vm-option:-Dfile.encoding=UTF-8

![image](https://github.com/chenjxJava/photos/blob/master/idea/%E6%8E%A7%E5%88%B6%E5%8F%B0%E4%B8%AD%E6%96%87%E4%B9%B1%E7%A0%81%E9%97%AE%E9%A2%98.png?raw=true)



















