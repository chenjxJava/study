# idea中out目录和target目录区别
参考：
[intellj idea中target目录和out目录有什么区别？](https://segmentfault.com/q/1010000007563528/a-1020000007563691)
### 一、问题描述
> out目录和target目录有什么区别呢？

![](https://github.com/chenjxJava/photos/blob/master/idea/idea_diff_target_out.png?raw=true)
<pre>
out存放的是该项目下所有Module(模块)的编译结果。 
target存放的是单个Module的编译结果。
 
如果为某个Module指定了编译结果的路径，则不会再输出到out文件夹中了。
你在Project Structure中的Project选项卡中可以设置Project compiler output的目录。 
在Modules中选择某一个模块后，在右侧的Paths选项卡中可以设置该模块的Compiler output目录。
</pre>

![](https://github.com/chenjxJava/photos/blob/master/idea/idea_out_location.png?raw=true)



