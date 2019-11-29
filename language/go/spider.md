#### 1.限制每个goruntine，抢占的事件 : time.Tick(10 * time.Millisecond)

```go
package main

import (
	"fmt"
	"time"
)

func main() {
	// 开启10个goruntine,设置一秒只能抢夺一次

	var rate = time.Tick(1000 * time.Millisecond)

	i := 0
	for i<10 {
		i++
		go func(i int) {
			<- rate
			fmt.Println(i)
		}(i)

	}

	select {}
}
```

