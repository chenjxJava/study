# GOLang

### 〇、学习网站

[Go语言中文网（推荐）](https://studygolang.com)



### 一、下载\安装

> [golang环境下载](https://studygolang.com/dl),安装GOlang环境，配置GOROOT、GOPATH
>
> [GOLang入门Helloworld(**必读**)](http://docscn.studygolang.com/doc/code.html#%E7%BB%84%E7%BB%87)

##### 1.GOROOT、GOPATH区别说明：

```tex
C:\Go    <<--- GOROOT 指向的位置
    --src                 <<--- Go 语言自带的源代码
    --pkg                 <<--- 编译的中间文件放在此文件夹
    --bin                 <<--- 编译的目标文件放在此文件夹
D:\MyWorks  <<--- GOPATH 指向的位置
    --src                 <<--- 项目源代码放置在此文件夹。!!!警告：一个常犯的错误是把 GOPATH 指向此处!!!
        --HelloWorld      <<--- 我们项目源代码所在的文件夹。!!!警告：一个常犯的错误是把 GOPATH 指向此处!!!
        --vendor          <<--- 第三方开源代码文件夹
            --github.com
                --...
    --pkg                 <<--- 编译的中间文件放在此文件夹，Go编译器自动生成此文件夹
    --bin                 <<--- 编译的目标文件放在此文件夹，Go编译器自动生成此文件夹
```

#### 2.idea开发GOLang

##### a.File->setting->Plugins搜索GO\GO template，进行install

![](https://github.com/chenjxJava/photos/blob/master/go/helloworld/idea_plugin_install.png?raw=true)

##### b.配置GOPATH，即为项目工程路径workspace,建议如下配置

![](https://github.com/chenjxJava/photos/blob/master/go/helloworld/idea_golang_project_struct.png?raw=true)