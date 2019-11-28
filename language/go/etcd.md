# etcd

> [下载](https://github.com/etcd-io/etcd/releases)
>
> [etcd rest api基本操作](https://blog.csdn.net/real_myth/article/details/75661071)
>
> [etcd、etcd-browser、etcdkeeper（支持 etcd v3 api） 简单安装配置](https://blog.csdn.net/fgf00/article/details/80263707)

### 一、命令行操作



```
// 1.put
HP@LAPTOP-8UJ48CLB MINGW64 ~/Desktop/etcd-v3.2.28-windows-amd64
$ ETCDCTL_API=3 ./etcdctl.exe --endpoints=localhost:2379 put foo bar
OK
// 2.get
HP@LAPTOP-8UJ48CLB MINGW64 ~/Desktop/etcd-v3.2.28-windows-amd64
$ ETCDCTL_API=3 ./etcdctl.exe --endpoints=localhost:2379 get foo
foo
bar
// 查看key
http://localhost:2379/v2/keys/?recursive=true
```

http://localhost:2379/v2/keys/?recursive=true