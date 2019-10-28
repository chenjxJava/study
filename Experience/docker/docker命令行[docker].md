参考:
[1.如何进入、退出docker的container](https://blog.csdn.net/dongdong9223/article/details/52998375)

[2.停止、删除所有的docker容器和镜像](https://www.imooc.com/article/details/id/32810)


# docker hub
网易镜像中心：https://c.163.com/hub#/m/home/?
daocloud镜像市场：https://hub.daocloud.io/
腾讯云 https://index.tenxcloud.com/tenxcloud/java8

# 〇、docker服务
### 1.启动docker服务
> service docker start/systemctl start docker

### 2.关闭docker服务
> service docker stop/systemctl stop docker

# 一、镜像
### 1.拉取镜像
> docker pull xxx.xxx.xx(镜像地址)


### 2.连接镜像
> docker run -it ubuntu /bin/bash
理解很简单：
docker run：启动container

ubuntu：你想要启动的image

-t：进入终端

-i：获得一个交互式的连接，通过获取container的输入

/bin/bash：在container中启动一个bash shell

###  3.列出所有镜像
> docker images
或者 
> docker image ls -a 

### 4.删除所有镜像
> docker rmi $(docker images -q)


# 二、容器
### 1.列出所有的容器
> docker ps -aq (q:quiet,只显示容器ID)
或者
> docker container ls -a

### 2.启动/停止所有的容器
> docker start/stop $(docker ps -aq)

### 3.启动单个容器 docker start [containerId]/[containerName]
> dokcer ps -a
CONTAINER ID        IMAGE                                 COMMAND             CREATED             STATUS              PORTS               NAMES
b97b22367620        demo:v1                               "/bin/bash"         16 minutes ago      Up 45 seconds       8080/tcp            tender_einstein
d0c954db9b8e        demo:v1                               "/bin/bash"         2 hours ago         Up 6 seconds        8080/tcp            affectionate_einstein
7fa9f272ac04        hub.c.163.com/library/tomcat:latest   "/bin/bash"         11 hours ago        Up 6 seconds        8080/tcp            clever_easley

# 根据Name启动， 若启动多个用空格
> docker start tender_einstein affectionate_einstein

### 4.进入容器
4.1 使用“docker attach”命令进入
(退出时，容器也跟着退出)1.exit 2.ctrl+D
> docker attach goofy_almeida(容器name/id)

4.2 使用“docker exec -it”命令进入
> docker exec -it goofy_almeida(容器name/id) /bin/bash

### 5.宿主机与容器拷贝文件
> docker cp mycontainer:/opt/file.txt /opt/local/docker
> cp /opt/local/file.txt mycontainer:/opt/


三、
### docker 1.13中
> docker image prune --force --all或者docker image prune -f -a` : 删除所有不使用的镜像
> docker container prune: 删除所有停止的容器

# 四、docker hub
```
# 登录
> docker login

# 提交 docker commit <container-name> <hub-user>/<repo-name>[:<tag>]
> docker commit spring-boot-gwt-sample panxiaoan/spring-boot-gwt-sample:latest

# 推送 docker push <hub-user>/<repo-name>:<tag>
> docker push panxiaoan/spring-boot-gwt-sample:latest
```

# 五、开启阿里云加速
```
 docker的镜像仓库在国外，下载会很慢，启用阿里云加速。

 在/etc/docker目录下创建daemon.json文件，添加如下内容
  {
   "registry-mirrors": ["https://almtd3fa.mirror.aliyuncs.com"]
  }
 https://almtd3fa.mirror.aliyuncs.com为阿里云的加速地址。修改后，重启docker

 systemctl daemon-reload
 service docker restart
```

# 六、执行可运行的jar文件
```
 将test.jar放在虚拟机的/usr目录下，然后执行命令启动jar

 docker run -d -p 9090:9090 -v /usr/springboot-1.jar:/usr/springboot-1.jar --name springboot java:8u111 java -jar /usr/springboot-1.jar

 -d 表示在后台启动

 -p 9090:9090 表示将容器的端口 映射成宿主主机的端口，否则9090端口访问不到

 -v /usr/springboot-1.jar:/usr/springboot-1.jar 表示将宿主主机的jar文件，映射到容器中（分号前为宿主主机的路径，分号后为容器中的路径）

 --name springboot 表示为该容器取一个全局唯一的名称，这里我取的名称为springboot

 java:8u111 表示镜像文件的名称和tag

 java -jar /usr/springboot-1.jar 表示运行jar包，注意：这里的jar包为容器中的位置，是通过前面的-v属性映射的
```
