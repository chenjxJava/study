# HTML+CSS
### API
[W3cplus 官网](https://www.w3cplus.com/)<br>
[W3cplus | 前端工具](https://www.w3cplus.com/solution/tools/tools.html)<br>
[W3cplus| css解决方案](https://www.w3cplus.com/solution/index/ )<br>

[HTML 速查列表](http://www.runoob.com/html/html-quicklist.html)<br>


### 一、	开发流程
1.	给div提供id属性，定html整体的结构。
2.	使用选择器，对指定的div进行修饰。


### 二、	CSS层叠样式表 (Cascading Style Sheets)
<pre>
1.外部样式表的优点：
  CSS 指层叠样式表 (Cascading Style Sheets)
  样式定义如何显示 HTML 元素
  样式通常存储在样式表中
  把样式添加到 HTML 4.0 中，是为了解决内容与表现分离的问题
  外部样式表可以极大提高工作效率
  外部样式表通常存储在 CSS 文件中
  多个样式定义可层叠为一

总结：为了内容表现分离，提高开发效率，使用外部样式表。
2.外部样式表长什么样？
</pre>
![](https://github.com/chenjxJava/photos/blob/master/css/css_out_link.png?raw=true)
<pre>
简单说，就是把css样式独立放在一个css文件中，然后<link>引入。
3.	Css文件内容应该怎么写？语法是什么？
CSS 规则由两个主要的部分构成：选择器，以及一条或多条声明:
</pre>
![](https://github.com/chenjxJava/photos/blob/master/css/css_selector.png?raw=true)
<pre>
  选择器通常是您需要改变样式的 HTML 元素。
  每条声明由一个属性和一个值组成。
  属性（property）是您希望设置的样式属性（style attribute）。每个属性有一个值。属性和值被冒号分开。

4.Selector选择器
 简单说，就是选中html结构中的指定模块的区域
 1)id选择器：
   Html中定义一个div,设置一个id="myId",例如：
	 &lt;div id="myId">&lt;/div>
   获取#id
 2)class选择器：
   Html中定义一个div,设置一个class="myClass",例如：
	  &lt;div id="myId">&lt;/div>
   获取.myClass
</pre>

### 三、FAQ
1.	定义样式，一定是&lt;style&gt;标签，而不是&lt;script&gt;标签
