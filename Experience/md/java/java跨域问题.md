# java跨域问题
### 一、解决方案
使用MappingJacksonValue

<pre>
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {
    
    @RequestMapping("/test_jsonp")
    public MappingJacksonValue test_jsonp(String callback) {
        Object response = null;
        //TODO ...
        
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(response);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}
</pre>

<pre>
1、如果客户端传值callback，那么就会以jsonp的形式返回给客户端

2、如果客户端没有传值callback,那么默认以json的形式返回给客户端

3、callback的参数名称也可以更改，一般不需要。

4、jsonp内部原理还是利用jackson的支持，把数据渲染成js字符串形式返回给客户端
</pre>