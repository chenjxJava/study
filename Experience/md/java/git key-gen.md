# Git生成key
> 生成key，并配置

一、[为GitLab帐号添加SSH keys并连接GitLab](http://blog.csdn.net/xyzchenxiaolin/article/details/51852333)

二、[IntelliJ IDEA 使用SSH Terminal](http://blog.csdn.net/ab7253957/article/details/72957924)
###
<pre>
1. git config --global user.name "chenjixing"

2. git config --global user.email "17681819406@139.com"
// 查看
git config --global user.name
git config --global user.email

3. ssh-keygen -t rsa -C "youremail@example.com"
4. 去c:/Users/xxxx_000/.ssh/目录下，打开id_rsa.pub文件，全选复制公钥内容。
5. 去https://gitlab.cmread.com:2443/miguwenxue/wenxue

</pre>
![](https://github.com/chenjxJava/photos/blob/master/wenxue/git_ssh_key.gif?raw=true)

### 二、TortoiseGit中SSH密钥的配置方法
参考：[百度经验TortoiseGit中SSH密钥的配置方法](https://jingyan.baidu.com/article/495ba841f2892638b30edefa.html)