# DataX的Job设置

> [alibaba_datax项目](https://github.com/alibaba/DataX)

 ## 一 、json任务文件的配置

基本结构为：

```json
{
	"setting": {},
	"job": {
		"setting": {
            "speed": {
                 "channel": 5, 
            	 //设置传输速度，单位为byte/s，DataX运行会尽可能达到该速度但是不超过它.
                 "byte": 1048576, 
				 "record": 10000
            },
                //出错限制
                "errorLimit": {
                //出错的record条数上限，当大于该值即报错。
                "record": 0,
                //出错的record百分比上限 1.0表示100%，0.02表示2%
                "percentage": 0.02
            }
		},
		"content": [{
			"reader": {},
			"writer": {}
		}]
	}
}
```

 job块中的setting部分，分为任务流控、脏数据限制设置，输入流，输出流三大块。

### 1.1 setting

#### 1.1.1 speed

  流量控制，控制作业速度，让作业可以在承受范围内达到最佳同步速度，共有三种模式：

channel，为其定义管道数（并发）

byte：记录流速度，单位为byte/s，DataX会尽可能接近该值但是不会超过它。

record：字节流

```json
"speed": {
	"channel": 5,
	"byte": 1048576,
	"record": 10000
}
```

#### 1.1.2 errorLimit

  脏数据限制，record:脏数据的上限，如果一个表超过该值，那么就会导入失败并报错

  percentage：脏数据所占比例上限，1.0代表100%，当前2%

### 1.2 输入输出

  见其他部分：DataX的Reader、DataX的Writer。