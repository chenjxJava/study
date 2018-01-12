# Git版本控制工具
介绍使用：[IDEA中Git的使用](https://www.cnblogs.com/wyb628/p/7243776.html)
<pre>
CVS和SVN都是集中式的版本控制系统。
Git是分布式版本控制系统。

集中式版本控制系统，版本库是集中存放在中央服务器的，而干活的时候，用的都是自己的电脑，所以要先从中央服务器取得最新的版本，
然后开始干活，干完活了，再把自己的活推送给中央服务器。中央服务器就好比是一个图书馆，你要改一本书，必须先从图书馆借出来，
然后回到家自己改，改完了，再放回图书馆。

总结：集中式版本控制，必须要联网。
</pre>
### 〇、下载
* Git 官网下载：[http://git-scm.com/](http://git-scm.com/)
* TortoiseGit 官网下载：[http://download.tortoisegit.org/tgit/](http://download.tortoisegit.org/tgit/)

### 重要：工作区和暂存区
<pre>
1.Git和其他版本控制系统如SVN的一个不同之处就是有暂存区的概念。
2.工作区（Working Directory）:
 就是你在电脑里能看到的目录，就比如study，简单说，就是整个项目的文件夹。
3.版本库（Repository）:
 工作区有一个隐藏目录".git",这个不算工作区，而是Git的版本库。
 版本库中，最重要的东西就是称为stage(或者叫index)的暂存区，还有Git为我们自动创建的第一分支master,以及指向master的一个
指针叫HEAD.
4.暂存区（stage）:
</pre>

![](https://raw.githubusercontent.com/chenjxJava/photos/master/git/git%E7%BB%93%E6%9E%84.png)

<pre>
$ git status
On branch master
Your branch is up-to-date with 'origin/master'.
// stage暂存区
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

        modified:   readme.txt
// 工作区
Untracked files: 
  (use "git add <file>..." to include in what will be committed)

        test.md

no changes added to commit (use "git add" and/or "git commit -a")
</pre>
<pre>
1."git add"把文件添加进去，实际上就是把文件修改添加到暂存区；
2."git commit"提交更改，实际上就是把暂存区的所有内容提交到当前分支。
因为我们创建Git版本库时，Git自动为我们创建了唯⼀一⼀一个master分⽀支，所以，现在，commit就是往master分⽀支上提交更改。
你可以简单理解为，需要提交的⽂文件修改通通放到暂存区，然后，⼀一次性提交暂存区的所有修改。 
</pre>

<pre>
场景1：当你改乱了⼯工作区某个⽂文件的内容，想直接丢弃⼯工作区的修改时，⽤用命令git checkout -- [file]。 
场景2：当你不但改乱了⼯工作区某个⽂文件的内容，还添加到了暂存区时，想丢弃修改，分两 步，第⼀一步⽤用命令git reset HEAD 
file，就回到了场景1，第二步按场景1操作。 
场景3：已经提交了不合适的修改到版本库时，想要撤销本次提交，参考版本回退⼀一节，不过前提是没有推送到远程库。
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
5. git checkout -- &lt;file&gt;  // to discard changes in working directory
6. git reset HEAD readme.txt //
6. git commit -m "worte a readme file"
   -m 后面就是本次提交的说明
7. git status 查看版本库状态
8. git diff   显示不同
9. git log --pretty=oneline   查看提交日志

ps：
  1.使用命令行时，每次文件改动，需要重新add commit
</pre>

* 入门篇（二）：克隆远程库，切换分支流程
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

* 入门篇（二）：git分支操作，推送gitlab、github
<br>参考：[Github 创建新分支](http://blog.csdn.net/top_code/article/details/51931916)
<pre>
// 一、查看分支
1. git branch  查看本地分支
2. git branch -r  查看远程分支
3. git branch -a  查看所有分支
4. git branch [branch name] 本地创建新的分支

// 二、切换新分支
1. git checkout [branch name]  切换分支
2. git checkout -b [branch name] 创建切换分支
3. git push origin [branch name] 将分支推送到github

// 三、删除分支
1. git branch -d [branch name] 删除本地分支
2. git push origin :[branch name] 删除远程分支 
</pre>


* 加强篇（二）：版本回滚
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

* 加强篇（三）：栈
<pre>
git stash 可用来暂存当前正在进行的工作， 比如想pull 最新代码， 又不想加新commit， 或者另外一种情况，为了fix 一个紧急的bug,
先stash, 使返回到自己上一个commit, 改完bug之后再stash pop, 继续原来的工作。
基础命令：
$git stash
$do some work
$git stash pop

进阶：
git stash save "work in progress for foo feature"
当你多次使用’git stash’命令后，你的栈里将充满了未提交的代码，这时候你会对将哪个版本应用回来有些困惑，
’git stash list’ 命令可以将当前的Git栈信息打印出来，你只需要将找到对应的版本号，例如使用’git stash apply stash@{1}’
就可以将你指定版本号为stash@{1}的工作取出来，当你将所有的栈都应用回来的时候，可以使用’git stash clear’来将栈清空。


git stash          # save uncommitted changes
# pull, edit, etc.
git stash list     # list stashed changes in this git
git show stash@{0} # see the last stash 
git stash pop      # apply last stash and remove it from the list

git stash --help   # for more info
</pre>

### FAQ
##### 1.对于脱离版本控制，不能再次添加的文件
<pre>
idea飘红，脱离版本控制，不能git add到版本控制的文件。

解决方法：先剪切到别的地方，git pull,再撤销。
</pre>
