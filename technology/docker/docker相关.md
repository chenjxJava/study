# docker



### 一、window10家庭版安装docker

> 参考：[Windows10家庭版安装docker攻略](https://www.cnblogs.com/jimmyshan-study/p/11161428.html)

很多帖子，说要提前开启Hyper-V选项，这个选项win10专业版才有。所以，必须先下载.exe安装文件，地址如下：

-  http://mirrors.aliyun.com/docker-toolbox/windows/docker-toolbox/ 

### 二、文档
[docker官网](https://www.docker.com/)

[docker docs](https://docs.docker.com/)


### 三、docker命令行

##### [2.1 docker](https://github.com/chenjxJava/study/blob/master/technology/docker/docker%E5%91%BD%E4%BB%A4%E8%A1%8C%5Bdocker%5D.md)

##### [2.1 docker-compose](https://github.com/chenjxJava/study/blob/master/technology/docker/docker%E5%91%BD%E4%BB%A4%E8%A1%8C%5Bdocker-compose%5D.md)

##### [2.1 docker-machine](https://github.com/chenjxJava/study/blob/master/technology/docker/docker%E5%91%BD%E4%BB%A4%E8%A1%8C%5Bdocker-machine%5D.md)



四、Dockerfile

> 本地需要docker环境，将项目打包成镜像。

项目demo地址：https://github.com/chenjxJava/docker_start





 echo '{ "insecure-registries":["58.59.43.23:5000"] }' > /etc/docker/daemon.json 