# go mod

> 参考：[Go mod 使用](https://segmentfault.com/a/1190000018536993)



#### 1.idea，go mod使用代理

- File--->Settings-->Languages & Framworks--->Go--->Go Muodules(vgo)-->Proxy:填写 https://goproxy.io

![image](https://github.com/chenjxJava/photos/blob/master/go/mod/idea_gomod_proxy.png?raw=true)



#### 2.go mod cli
```
Usage:

        go mod <command> [arguments]

The commands are:

        download    download modules to local cache
        edit        edit go.mod from tools or scripts
        graph       print module requirement graph
        init        initialize new module in current directory
        tidy        add missing and remove unused modules
        vendor      make vendored copy of dependencies
        verify      verify dependencies have expected content
        why         explain why packages or modules are needed
```