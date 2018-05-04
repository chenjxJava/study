# 重定向传递数据
参考：[重定向时传输数据](https://blog.csdn.net/november22/article/details/55518009)

### 
概要：

当一个处理器方法完成之后，该方法所指定的模型数据将会复制到请求中，并作为请求中的属性，请求会转发(forward)到视图上进行渲染。因为控制器方法和视图所处理的是同一个请求，所以在转发的过程中，请求属性能够得以保存。

但是，当控制器的结果是重定向的话，原始的请求就结束了，并且会发起一个新的GET请求。原始请求中所带有的模型数据也随着请求消亡了。



有两种方式可以在重定向中传输数据 ：

①使用URL模板以路径变量和/或查询参数的形式传递参数。//只能传递简单类型的参数

②通过flash属性发送数据。


<pre>
方式一的示例：

@RequestMapping(method=RequestMethod.GET)

public String spittles(Model model){


//map类型也可以做为模型

model.addAttribute("pid", "wahahah");

model.addAttribute("username", "haha");

return "redirect:/spi/{username}";

//冲向的路径形式是：/spi/haha?pid=wahahah

}


@RequestMapping("/spi/{username}")

public void tets(@PathVariable("username")String username,String pid ){

System.out.println(username);

System.out.println(pid);

}

方式二示例：

通过flash其实是将数据放到会话中，然后再取出。

@RequestMapping(method=RequestMethod.GET)

public String spittles(RedirectAttributes model){


//map类型也可以做为模型

model.addFlashAttribute("spittles", spittleRespository.findSpittles(Long.MAX_VALUE, 20));

model.addAttribute("pid", "wahahah");

model.addAttribute("username", "haha");

return "redirect:/spittles/spi/{username}";

}


@RequestMapping("/spi/{username}")

public void tets(@PathVariable("username")String username,String pid ,Model model){

if(model.containsAttribute("spittles")){

Map<String, Object> asMap = model.asMap();

ArrayList<Spittle> list = (ArrayList<Spittle>) asMap.get("spittles");

System.out.println(Arrays.toString(list.toArray()));

}

System.out.println(username);

System.out.println(pid);

}
</pre>