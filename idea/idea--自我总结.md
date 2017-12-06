# idea— —自我总结
#### 导语  
> 个人认为idea是功能比eclipse强大N多倍的一个开发工具，能够大幅提升开发者的开发速度。[idea官网下载](https://www.jetbrains.com/idea//)
#### 一、idea安装
1.访问官网下载.exe文件，完成默认安装
![image](https://github.com/chenjxJava/photos/blob/master/20171121144825.png?raw=true)
2.idea激活方式 | help-->Register 

<html>
<div style="margin-left:40px">
<img src="https://github.com/chenjxJava/photos/blob/master/20171121152329.png?raw=true"/>
</div>
</html>

-     填入下面的license server:
	  http://intellij.mandroid.cn/
      http://idea.imsxm.com/

#### 二、基本配置
1. 配置jdk

![image](https://github.com/chenjxJava/photos/blob/master/idea_jdk1.png?raw=true)
![image](https://github.com/chenjxJava/photos/blob/master/idea_jdk2.png?raw=true)
2. 配置tomcat
3. 配置svn
4. 配置maven（不用配置）
5. 设置默认注释 setting-Editor-File and Code-Files-Class
![image](https://github.com/chenjxJava/photos/blob/master/idea_comment.png?raw=true)
-     /** 
    	* @Author: ${USER}
    	* @Description: ${DESCRIPTION}
    	* @Date: Created in ${TIME} ${DATE}
    	* @Modified By:
    	*/
#### 三、Idea学习网站 ( 参考：http://www.youmeek.com/)
1. idea快捷键
* http://blog.csdn.net/dc_726/article/details/42784275 十大快捷神键
* http://www.cnblogs.com/zhangpengshou/p/5366413.html 快捷键大全


#### 四、好用
1. 快速生成表格

![image](https://github.com/chenjxJava/photos/blob/master/idea%E5%BF%AB%E9%80%9F%E7%94%9F%E6%88%90%E8%A1%A8%E6%A0%BC.gif?raw=true)
2. 

#### 五、FAQ
1. Idea maven工程的java包下xml文件是不会被打进jar包的,在pom文件中添加
-     <build>
    		<resources>
    			<resource>
    		    	<directory>src/main/java</directory>
    				<includes>
    					<include>**/*.xml</include>
    				</includes>
    			</resource>
    		</resources>
    	</build>

2. 修改idea项目名称

- Ctrl+shift+alt+s--->修改工程结构

![image](https://github.com/chenjxJava/photos/blob/master/idea_pro_modu_name.png?raw=true)
-  .idea-->.name
 
![image](https://github.com/chenjxJava/photos/blob/master/idea_project_name.png?raw=true)
 
3. idea切换svn

![image](https://github.com/chenjxJava/photos/blob/master/idea_svn_change.png?raw=true)
 
4. Configuration Error: deployment source '(projectname): war exploded' is not valid

![image](https://github.com/chenjxJava/photos/blob/master/idea_make_artifact.png?raw=true)
 
5. Idea不能Alt+Enter提示生成变量(代码助手)
- File-->setting-->editor-->intentions-->Introduce local variable

![image](https://github.com/chenjxJava/photos/blob/master/idea_code_intentions.png?raw=true)

6.控制台中文乱码问题
jvm配置中，vm-option:-Dfile.encoding=UTF-8

![image](https://github.com/chenjxJava/photos/blob/master/idea_code_intentions.png?raw=true)



 















