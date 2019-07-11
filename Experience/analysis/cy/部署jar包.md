# 一、部署jar包

##### 1.bike.jar

```
nohup java -Xms256m -Xmx2048m -XX:PermSize=256m -XX:MaxPermSize=512m -XX:MaxNewSize=512m -jar bike.jar > bike.log &
```

##### 2.road.jar

```
nohup java -Xms256m -Xmx2048m -XX:PermSize=256m -XX:MaxPermSize=512m -XX:MaxNewSize=512m -jar road.jar > road.log &
```

##### 3.riverorder.jar

```
nohup java -Xms256m -Xmx2048m -XX:PermSize=256m -XX:MaxPermSize=512m -XX:MaxNewSize=512m -jar riverorder.jar > river.log &
```

