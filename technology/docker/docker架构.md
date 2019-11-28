# Docker架构
Docker使用客户端-服务器（c/s）架构模式，使用远程API来管理和创建Docker容器。
Docker容器通过Docker镜像来创建。
容器与镜像的关系里斯与面向对象编程中的对象与类。

note1：
也就是说，一个镜像，可以创建多个容器。

概念：
1.Docker镜像（Images） Docker镜像是用于创建Docker容器的模板。
2.Docker容器（Container） 容器是独立运行的一个或一组应用。
3.Docker客户端（Client） Docker客户端通过命令行或者其他工具使用Docker API((https://docs.docker.com/reference/api/docker_remote_api)与Docker的守护进程通信。
4.Docker主机（Host）一个物理或者虚拟的机器用户执行Docker守护进程和容器。
5.Docker仓库（Registry） Docker仓库用来保存镜像，可以理解为代码控制中的代码仓库。
	Docker Hub(https://hub.docker.com)提供了庞大的镜像集合供使用
6.Docker Machine Docker Machine是一个简化Docker安装的命令行工具，通过一个简单的命令行即可在相应的平台上安装Docker,比如Virtual Box、 Digital Ocean、Microsoft Azure。
