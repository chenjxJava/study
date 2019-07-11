# rpm安装yum
### 一、什么是RPM(Red-Hat Package Manager)

### 二、安装
参考：[CentOS6.8重新安装yum](https://blog.csdn.net/visiontime/article/details/70857454)

<pre>
1.获取rpm文件
wget http://mirrors.163.com/centos/6/os/x86_64/Packages/python-iniparse-0.3.1-2.1.el6.noarch.rpm

wget http://mirrors.163.com/centos/6/os/x86_64/Packages/yum-metadata-parser-1.1.2-16.el6.x86_64.rpm

wget http://mirrors.163.com/centos/6/os/x86_64/Packages/yum-3.2.29-81.el6.centos.noarch.rpm

wget http://mirrors.163.com/centos/6/os/x86_64/Packages/yum-plugin-fastestmirror-1.1.30-40.el6.noarch.rpm

2.rpm进行安装
rpm -ivh python-iniparse-0.3.1-2.1.el6.noarch.rpm

rpm -ivh yum-metadata-parser-1.1.2-16.el6.x86_64.rpm

rpm -ivh yum-3.2.29-81.el6.centos.noarch.rpm yum-plugin-fastestmirror-1.1.30-40.el6.noarch.rpm --force --nodeps
</pre>

### 三、注意
<pre>
1.yum-plugin-fastestmirror去搜索找版本

2.查看版本（x86.64 为64位版本）
> cat /etc/redhat-release
CentOS release 6.4 (Final)
> rpm -qa | grep release
centos-release-6-4.el6.centos.10.i686
</pre>

