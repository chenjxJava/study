# beego入门

> 

## 一、quickstart

> [beego快速入门(官网)](https://beego.me/quickstart)

#### 1.下载

```go
$ go get -u github.com/astaxie/beego
$ go get -u github.com/beego/bee
```

#### 2.install/build,

```go
// 在$GO_PATH或者github.com/beego/bee目录下生成bee.exe(window)
$ go install/build github.com/beego/bee
```

#### 3.运行

```
>cd %GOPATH%/src
>bee new hello
>cd hello
>bee run
```

这些指令帮助您：

1. 安装 beego 到您的 $GOPATH 中。
2. 在您的计算机上安装 Bee 工具。
3. 创建一个名为 “hello” 的应用程序。
4. 启动热编译。

一旦程序开始运行，您就可以在浏览器中打开 http://localhost:8080/ 进行访问