# channel

> golang语言中，channel存在与goruntine之间的通信



## 一、基础部分

### 1.channel(一个协程发，必须要一个协程收)

```go
fun main() {    
    c := make(chan int)
	// 错误x
	// fmt.Println(<-c)
    
    // 正确√,go开启一个协程
	go func(){ 
		for value := range c {
			fmt.Println(value)
		}
	}()

	c <- 1
	c <- 2
	close(c) // 关闭channel
    
	time.Sleep(time.Minute)
}
```

### 2.buffered channel

### 3.range

```go
// 1.ok代表获取到值，可以用来判断channel是否发送完
c := make(chan int)

go func(){
    for{
        value, ok := <- c
        if !ok {
            break;
        }
        fmt.Println(value)
    }
}()

//2.range遍历channel(推荐)
go func(){
    for value := range c {
        fmt.Println(value)
    }
}()

c <- 1
c <- 2
close(c)
time.Sleep(time.Minute)
```

### 4.channel方向

- 只可发送 chan<-
- 只可接收 <-chan

### 5.生产者，消费者概念

```go
// 1.创建一个worker，发送数据，返回一个channel
createWorker(id int) chan int

// 2.开启goruntine，接收channel
worker(c chan int)
```

### 6.sync.WaitGroup

```go
package main

import (
	"fmt"
	"sync"
)

func chanDemo() {
	var wg sync.WaitGroup
	// wg.Add(20)

	var workers [10]worker
	for i := 0; i < 10; i++ {
		workers[i] = createWorker(i, &wg)
	}

	for i, worker := range workers {
		worker.in <- 'a' + i
        wg.Add(1) // 添加 
	}
	for i, worker := range workers {
		worker.in <- 'A' + i
        wg.Add(1) // 添加
	}
	// 等待
	wg.Wait()
}

func createWorker(id int, group *sync.WaitGroup)  worker {
	w := worker{
		in: make(chan int),
		done: group.Done,
	}
	go dowork(id, w)
	return w
}

type worker struct {
	in chan int
	done func()
}

func dowork(id int,
	w worker) {
	for v := range w.in {
		fmt.Printf("Worker %d received %c\n",
			id, v)
        // 完成
		w.done()
	}
}

func main() {
	chanDemo()
}
```

### 7.select

> 使用Select来进行调度

#### a.Select的使用

#### b.定时器的使用

time.After()

time.Tick()

#### c.在Select中使用Nil Channel







