# Git版本控制工具
<pre>
CVS和SVN都是集中式的版本控制系统。
Git是分布式版本控制系统。

集中式版本控制系统，版本库是集中存放在中央服务器的，而干活的时候，用的都是自己的电脑，所以要先从中央服务器取得最新的版本，然后开始干活，干完活了，再把自己的活推送给中央服务器。中央服务器就好比是一个图书馆，你要改一本书，必须先从图书馆借出来，然后回到家自己改，改完了，再放回图书馆。

总结：集中式版本控制，必须要联网。
</pre>
### 一、Git的优势
1. 不需要联网
2. 极其强大的分支管理

### 二、Git命令
<pre>设置全局参数
1. git config --global user.name "chenjixing"
2. git config --global user.email "chenjixing@ebupt.com"
初始化git仓库
3. git init 
4. git add readme.txt
5. git commit -m "worte a readme file"
   -m 后面就是本次提交的说明
6. git status
7. git diff

</pre>

<pre>
总结：
 初始化一个Git仓库，使⽤git init命令。
 添加文件到Git仓库，分两步：
 • 第一步，使用命令git add ，注意，可反复多次使用，添加多个文件；
 • 第二步，使用命令git commit，完成。
</pre>