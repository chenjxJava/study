# Git版本控制工具
介绍使用：[IDEA中Git的使用](https://www.cnblogs.com/wyb628/p/7243776.html)
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

git clone只能clone远程库的master分支，无法clone所有分支，解决办法如下：
1. 找一个干净目录，假设是git_work
2. cd git_work
3. git clone http://myrepo.xxx.com/project/.git ,这样在git_work目录下得到一个project子目录
4. cd project
5. git branch -a，列出所有分支名称如下：
remotes/origin/dev
remotes/origin/release
6. git checkout -b dev origin/dev，作用是checkout远程的dev分支，在本地起名为dev分支，并切换到本地的dev分支
7. git checkout -b release origin/release，作用参见上一步解释
8. git checkout dev，切换回dev分支，并开始开发。
</pre>
参考：[git切换远程分支](http://www.cnblogs.com/libertycode/p/5858450.html)

<pre>
总结：
 初始化一个Git仓库，使⽤git init命令。
 添加文件到Git仓库，分两步：
 • 第一步，使用命令git add ，注意，可反复多次使用，添加多个文件；
 • 第二步，使用命令git commit，完成。
</pre>