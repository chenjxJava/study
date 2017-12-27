# Git版本控制工具
介绍使用：[IDEA中Git的使用](https://www.cnblogs.com/wyb628/p/7243776.html)
<pre>
CVS和SVN都是集中式的版本控制系统。
Git是分布式版本控制系统。

集中式版本控制系统，版本库是集中存放在中央服务器的，而干活的时候，用的都是自己的电脑，所以要先从中央服务器取得最新的版本，然后开始干活，干完活了，再把自己的活推送给中央服务器。中央服务器就好比是一个图书馆，你要改一本书，必须先从图书馆借出来，然后回到家自己改，改完了，再放回图书馆。

总结：集中式版本控制，必须要联网。
</pre>
### 〇、下载
* Git 官网下载：[http://git-scm.com/](http://git-scm.com/)
* TortoiseGit 官网下载：[http://download.tortoisegit.org/tgit/](http://download.tortoisegit.org/tgit/)

### 关于名词
<pre>
1.版本库 
	简单说就是一个文件夹
2.
</pre>

### 一、Git的优势
1. 不需要联网
2. 极其强大的分支管理
3. 分布式

### 二、Git命令
* 入门篇（一）：基本操作
<pre>
//设置全局参数
1. git config --global user.name "chenjixing"
2. git config --global user.email "chenjixing@ebupt.com"
//初始化git仓库
3. git init 
4. git add readme.txt
5. git commit -m "worte a readme file"
   -m 后面就是本次提交的说明
6. git status 查看版本库状态
7. git diff   显示不同
8. git log --pretty=oneline   查看提交日志

ps：
  1.使用命令行时，每次文件改动，需要重新add commit
</pre>

* 入门篇（二）：克隆远程库，切换分支
<br>参考：[git切换远程分支](http://www.cnblogs.com/libertycode/p/5858450.html)
<pre>
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


* 入门篇（二）：版本回滚
<pre>
1.git log --pretty=oneline
 fe58d2599a66f481a52b52ddbceaccdbdc5091ff (HEAD -> master) i like git
 fffd2d660948b1d6511e93d814b8389757cabe84 1
 2368b600bb17329b4fae9c9c12d49c3e89f1136c add readme file
 
注意：fe58d2599a66f481a52b52ddbceaccdbdc5091ff就是commitID
2.git reset --HEAD^
 HEAD     表示当前版本
 HEAD^    表示上一个版本
 HEAD^^   表示上两个版本
 HEAD~100 表示上100版本
 
注意:reset 可以根据commitID进行回滚
3.git reflog
 fe58d25 (HEAD -> master) HEAD@{0}: reset: moving to fe58d259
 fffd2d6 HEAD@{1}: reset: moving to HEAD^
 fe58d25 (HEAD -> master) HEAD@{2}: commit: i like git
 fffd2d6 HEAD@{3}: commit: 1
 2368b60 HEAD@{4}: commit (initial): add readme file

可以看到i like git对应的commitID,然后reset

现在总结一下： 
• HEAD指向的版本就是当前版本，因此，Git允许我们在版本的历史之间穿梭，使⽤用命 令git reset --hard commit_id。
• 穿梭前，⽤用git log可以查看提交历史，以便确定要回退到哪个版本。
• 要重返未来，⽤用git reflog查看命令历史，以便确定要回到未来的哪个版本。
</pre>
