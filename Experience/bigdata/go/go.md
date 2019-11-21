# GOLang

### 〇、学习网站

[Go语言中文网（推荐）](https://studygolang.com)

> 学习，建议先阅读[如何使用Go编程](http://docscn.studygolang.com/doc/code.html#%E7%BB%84%E7%BB%87)，你会对GO有个基本的概念
>
> 进阶，建议阅读[Effective_Go编程](http://docscn.studygolang.com/doc/effective_go.html), [英文网站GolangPrograms](https://www.golangprograms.com/go-language.html)， [《The Way to Go》 ](https://github.com/unknwon/the-way-to-go_ZH_CN/blob/master/eBook/directory.md)
>
> 也可以看下大神的golang学习经历，[我的 Go 语言学习之路](https://studygolang.com/topics/8550#reply24)

# 我的 Go 语言学习之路

### 一、下载\安装

> [golang环境下载](https://studygolang.com/dl),安装GOlang环境，配置GOROOT、GOPATH

#### 1.GOROOT、GOPATH区别说明：

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





### 二、GOLang语法

#### 1.变量

#### 2. 常量

- itoa



#### 6.函数

- 返回值类型写在最后面
- 可返回多个值，主要是error
- 函数作为参数 函数式编程

```go
//函数式编程
func apply(op func(int, int) int, a, b int) int{
	fmt.Printf("Calling %s with %d, %d\n",
		runtime.FuncForPC(reflect.ValueOf(op).Pointer()).Name(), a, b )
	return op(a, b)
}

//重写pow方法
func pow(x, y int) int {
	return int(math.Pow(float64(x), float64(y)))
}

//求函数的可变参数
func main() {
	fmt.Println(apply(pow,2, 3))
}
```

- 没有默认参数，可变参数 ...int 

```go
  func sums(arr ...int) {}
```



#### 7.指针

```go
var a int = 2
var pa *int = &a
*pa = 3
fmt.Println(a)
```



#### 8.数组是值类型

- [10]int 和[20]int是不一样的类型
- 调用func f(arr [10]int)会拷贝数组
- 在go语言中一般不直接使用数组



#### 9.Slice(切片)

> slice相当于直接操作的是数组指针（引用类型，可以改变数组）

![](https://github.com/chenjxJava/photos/blob/master/idea/20171121144825.png?raw=true)

- ptr len cap --> index
- slice可以向后扩展，不可以向前扩展
- s[i]不可以超越len(s),向后扩展不可以超越底层数组cap(s)

```go
arr := [...]int{0, 1, 2, 3, 4, 5, 6, 7}
s1 := arr[2:6] //切片 [2 3 4 5]
s2 := s1[3:5] //reslice
fmt.Printf("s1=%v, len(s1)=%d, ", s1) // [2 3 4 5]
fmt.Println(s2) // [5 6]
```

- slice的create delete (删除中间，删除头，删除尾)



#### 10.Map
##### 10.1 Map的操作
- 创建：make(map[string]int)

- 获取元素：m[key]

- key不存在时，获得Value类型的初始值

- 用value,ok:= m[key]来判断是否存在key

- 用delete删除一个key

  

##### 10.2 map的遍历

- 使用range遍历key， 或者遍历key,value对

- 不保证遍历顺序，如需顺序，需手动对key排序

- 使用len获得元素的个数

  

##### 10.3 map的key

- map使用哈希表，必须可以比较相等
- 除了slice,map,function的内建类型都可以作为key
- struct类型不包含上述字段，也可以作为key

> 例题：[寻找最长不含有重复字符的字串](https://leetcode.com/problems/longest-substring-without-repeating-characters/description)
>
> - abcabcbb		abc
> - bbbbbbb		 b
> - pwwkew		  wke 



#### 11.rune

rune相当于go的ch,四个字节



### 三、面向对象

- go语言仅支持封装，不支持继承和多态
- go语言没有class,只有struct
- 只有使用指针才可以改变结构体内容
- nil指针也可以调用方法！



#### 1.struct

- 显式定义和命名方法接收者（就是方法名前面括号里的内容，可以是指针，也可以是struct对象）

```go
package main

import (
	"fmt"
)

type treeNode struct{
	value int
	left, right *treeNode

}

func (node treeNode) print() {
	fmt.Println(node.value)
}

func (node *treeNode) setValue(value int) {
	if node == nil {
		fmt.Println("Setting value ignored")
		return
	}
	node.value = value
}

// 自定义工厂函数(go语言中没有构造函数)
func createNode(value int) *treeNode {
	return &treeNode{value:value}
}

func main() {
	var root treeNode
	root = treeNode{value:3}
	root.left = &treeNode{}
	root.right = &treeNode{5, nil, nil}
	root.right.left = new(treeNode)

	fmt.Println(root)

	// 定义结构体slice
	nodes := []treeNode {
		{value:3},
		{},
		{6, nil, &root},
	}
	fmt.Println(nodes)

	// 为结构体，定义方法
	node := createNode(5)
	node.print()
	node.setValue(100)
	node.print()


	// 默认pRoot为nil, setValue()中: node.value = value, nil.value会报错
	var pRoot *treeNode
	pRoot.setValue(200)
	pRoot = &root
	pRoot.setValue(300)
	pRoot.print()
}
```



#### 2.值接收者 vs 指针接收者

- 要改变内容必须使用指针接收者
- 结构过大也考虑使用指针接收者
- 一致性：如有指针接收者，最好都是指针接收者
- 值接收者 go语言特有
- 值/指针接收者均可接收值/指针


#### 3.封装
##### 3.1 命名
- 名字一般使用CamelCase
- 首字母大写：public
- 首字母大写：private

##### 3.2 包
- 每个目录一个包
- main包包含可执行入口
- 为结构定义的方法必须放在同一个包内
- 可以是不同的文件

##### 3.3 扩展已有类型

- 如何扩充系统类型或者别人定义的类型

  - 定义别名  

  - 使用组合  

###### 3.3.1 定义别名 

```go
package queue

import "fmt"

type Queue []int // 定义别名

func (q *Queue) Push(in int)  {
	*q = append(*q, in)
}

func (q *Queue) Pop() int {
	head := (*q)[0]
	*q = (*q)[1:]
	return head
}

func (q *Queue) Print()  {
	for _, v := range *q {
		fmt.Print(v, " ")
	}
	fmt.Println()
}

func (q *Queue) IsEmpty() bool {
	return len(*q) == 0
}
```



######    3.3.1 使用组合



##### 3.4 GOPATH

- go install/go build 

- go imports/ gopm

- gopm -g -v -u /golang.org/...

#### 4.接口

##### 1. duck typing

```
大黄鸭是鸭子吗？
- 
-
```



##### 2.系统常用接口

###### 2.1 Stringer（）

- 相当于java中的toString()方法

``` go
// fmt包,print.go文件中
package fmt

type Stringer interface {
String() string
}
```

###### 2.2 Reader、Writer

```
// io包,io.go文件中
type ReadWriter interface {
	Reader
	Writer
}

type Reader interface {
	Read(p []byte) (n int, err error)
}

type Writer interface {
	Write(p []byte) (n int, err error)
}
```



### 四、函数式编程

##### 1.函数式编程 vs 函数指针

- 函数式一等公民：参数，变量，返回值都可以是函数
- 高阶函数
- 函数 --> 闭包

##### 2.''正统''函数时编程

- 不可变性：不能有状态，只有常量和函数

- 函数只能有一个参数

- go中不需作上述严格规定

##### 3.举例子
```
例1：斐波那契fibonacci

例2：为函数写接口

例3：函数遍历树
```



更为自然，不需要修饰如何访问自由变量

go语言中没有lambda表达式，有匿名函数



### 五、资源管理与出错处理

##### 1.defer

- 确保函数在结束时发生
- 何时使用defer调用
  - Open/Close
  - Lock/Unlock
  - PrintHeader/PrintFooter

##### 2.error

- Error是一接口，可以实现接口，自定义error

- error.new(string)
- error.(type)判断是否为对应类型

##### 3.recover

- 仅在defer中可以使用
- 1
- recover



error vs panic

- 意料之中的：使用error。如：文件打不开
- 意料之外的：使用panic。如：数组越界



defer +error + panic

Type Assertion  --> case

函数式编程



### 六、GORuntine


##### 协程 Coroutine
- 轻量级"线程"
- 非抢占式多任务处理，由协程主动交出控制权
- 编译器/解释器/虚拟

```go
// 释放控制权
runtime.GoShced()

// 查看
go run -race main.go
```



- goroutine可能的切换

  - I/O,select

  - channel

  - 等待锁

  - 函数调用（有时）

  - runtime.Goshced()

只是参考，不是肯定切换，只是有机会



### 七、channel

- Don`t communicate by sharing memory;share memory by communicating
- 不要通过共享内存来通信；通过通信来共享内存

```
chan<- int //only receivce

<-chan int //only send

var wg sync.WaitGroup
wg.add(20) // 添加
wg.Wait()  // 等待
wg.Done()  // 结束

```



// 查看接口文档  ，Example生成实例代码

godoc -http :6060 

// 性能测试

go test -bench 





### 八、http库和其他库

##### 1.http服务器的性能

- import - "net/http/pprof"
- 访问/debug/pprof/
- 使用go tool pprof分析性能
  - go to

##### 2.其他库

- bufio
- encoding/json
- time
- log
- regexp
- string/math/rand

###### 2. 2看标准库文档

- godoc :8888





九、广度优先算法

```
6 5
0 1 0 0 0 
0 0 0 1 0
0 1 0 1 0 
1 1 1 0 0
0 1 0 0 1 
0 1 0 0 0 
```



### 十、爬虫项目

##### 1.1 为什么爬虫项目

- 有一定的复杂性

- 可以灵活调整项目的复杂性

```
反爬机制、动态页面 不是重点
```
- 平衡语言/爬虫之间的比重

##### 2.网络爬虫分类
- 通用爬虫，如baidu,google
- 聚焦爬虫，从互联网获取结构化数据

##### 3.go语言的爬虫库/框架

- henrylee2cn/pholcus
- colly
- gocrawl
- hu17889/go_siper

##### 4.本课程框架

- 不使用爬虫框架
- 使用ES作为文件存储
- 使用http的模板

##### 5.如何发现用户（算法）

- 通过[城市列表](http://www.zhenai.com/zhenghun)->城市->(下一页) ->用户
- 通过用户->猜你喜欢
- 通过已有用户id+1来猜测用户id