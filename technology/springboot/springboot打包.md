# springboot打包
1.pom.xml中添加mainClass

``` xml
<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                    <mainClass>com.chenjx.redwars.RedwarsApplication</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

2.启动类继承SpringBootServletInitializer，并重写config方法
```xml
@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.chenjx.redwars.dao")
@EnableSwagger2
public class RedwarsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RedwarsApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
```

3.maven clean install打包即可

### 注意：springboot2.0.4不需要第二步操作