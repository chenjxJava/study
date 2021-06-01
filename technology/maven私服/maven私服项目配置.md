# maven 私服相关
- 参考文献：
[Nexus Repository Manager 3 私服搭建 —— windows版](https://blog.csdn.net/cuncaojin/article/details/81270897 "Nexus Repository Manager 3 私服搭建 —— windows版")
[maven deploy 快照与发布 -- RELEASE与SNAPSHOT](https://blog.csdn.net/justlpf/article/details/84333238 "maven deploy 快照与发布 -- RELEASE与SNAPSHOT")
[Maven的使用教程](https://blog.csdn.net/qq_22172133/article/details/81666926 "Maven的使用教程")


## 一、maven根路径/conf/settings.xml,添加用户配置
```xml
<server>
	<id>releases</id> <!-- 对应pom文件中的repository的id -->
	<username>admin</username> <!-- 真实账号密码，如有需要联系运维 -->
	<password>admin123</password>
</server>
<server>
	<id>snapshots</id>
	<username>admin</username>
	<password>admin123</password>
</server>
```
## 二、在项目pom.xml下，添加releases库和snapshots库
```xml
<distributionManagement>
    <!-- 快照 对应x.x.-SNAPSHOT.jar,都会depoly到这个地址 -->
    <snapshotRepository>
        <id>snapshots</id> <!-- 对应settings.xml文件中的server的id,找密码用的 -->
        <url>
            http://101.68.86.229:8084/repository/maven-snapshots/
        </url>
    </snapshotRepository>
    <!-- 稳定版本 对应x.x.x.jar,例如，uucs_connect-1.1.0.jar都会depoly到这个地址 -->
	<repository>
		<id>releases</id>
		<url>
			http://101.68.86.228:8084/repository/maven-releases/
		</url>
	</repository>
</distributionManagement>
```

## 三、进行maven deploy
根据version,是否为SNAPSHOT,将包分为快照和release版本
![](https://www.showdoc.cc/server/api/common/visitfile/sign/6d7dfa14dc18c1a4276a5f4fa1914f21?showdoc=.jpg)

## 四、设置匿名用户访问，拉取jar包不需要登入
![](https://www.showdoc.cc/server/api/common/visitfile/sign/e3389d9d308f81d0cb9a1280f0edc033?showdoc=.jpg)

## 五、项目pom中引入私服地址

```xml
<dependencies>
    <dependency>
	    <groupId>xxxx</groupId>
	    <artifactId>xxxxx</artifactId>
    </dependency>
</dependencies>
<repositories>
        <repository>
            <id>releases</id>
            <url>
                http://101.68.86.228:8084/repository/maven-releases/
            </url>
        </repository>
</repositories>
```