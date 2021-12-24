# SSH原理与运用
[参考：SSH原理与运用（一）：远程登录](http://www.ruanyifeng.com/blog/2011/12/ssh_remote_login.html)

### 一、什么是SSH？

简单说，SSH是一种网络协议，用于计算机之间的加密登录。

如果一个用户从本地计算机，使用SSH协议登录另一台远程计算机，我们就可以认为，这种登录是安全的，即使被中途截获，密码也不会泄露。

最早的时候，互联网通信都是明文通信，一旦被截获，内容就暴露无疑。1995年，芬兰学者Tatu Ylonen设计了SSH协议，将登录信息全部加密，成为互联网安全的一个基本解决方案，迅速在全世界获得推广，目前已经成为Linux系统的标准配置。

需要指出的是，SSH只是一种协议，存在多种实现，既有商业实现，也有开源实现。本文针对的实现是OpenSSH，它是自由软件，应用非常广泛。

此外，本文只讨论SSH在Linux Shell中的用法。如果要在Windows系统中使用SSH，会用到另一种软件PuTTY，这需要另文介绍。



# Git生成key
> 生成key，并配置

一、[为GitLab帐号添加SSH keys并连接GitLab](http://blog.csdn.net/xyzchenxiaolin/article/details/51852333)

二、[IntelliJ IDEA 使用SSH Terminal](http://blog.csdn.net/ab7253957/article/details/72957924)

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

<pre>
注意：在$HOME/.ssh/目录下，会新生成两个文件：id_rsa.pub和id_rsa。前者是你的公钥，后者是你的私钥。
</pre>

### 二、TortoiseGit中SSH密钥的配置方法
参考：[百度经验TortoiseGit中SSH密钥的配置方法](https://jingyan.baidu.com/article/495ba841f2892638b30edefa.html)

参考：[git教程](http://www.yiibai.com/git/git_clone.html)
参考：[ Git 【基于SSH协议clone GitHub远端仓库到本地】](http://blog.csdn.net/felicity294250051/article/details/53606158)


