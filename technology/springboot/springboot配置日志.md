
### 一、yml文件,关闭日志
[参考：Spring Boot 日志配置(超详细)](https://blog.csdn.net/inke88/article/details/75007649#级别控制)
			
				
#### 1.application.yml
``` yml
logging:
  level:
    root: off
```

#### 2.application.properties
``` properties
logging.level.root=off
```


【级别控制】
所有支持的日志记录系统都可以在Spring环境中设置记录级别（例如在application.properties中） 
格式为：'logging.level.* = LEVEL'

logging.level：日志级别控制前缀，*为包名或Logger名 
LEVEL：选项TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF

举例：

logging.level.com.dudu=DEBUG：com.dudu包下所有class以DEBUG级别输出
logging.level.root=WARN：root日志以WARN级别输出
