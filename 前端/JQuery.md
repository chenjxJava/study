### 一、
#### 1.常用方法
#### 1.1 绑定click事件
>      方法一：
>      $("button").click(function(){
>		  $("p").slideToggle();
>	     }); 
>		
>      方法二：
>     $("button").bind("click",function(){
>		  $("p").slideToggle();
>	     }); 

#### 1.2 eval()----转化json字符串为json对象
>     function(data){
	   //获取json对象
	   _data = eval("(" + data + ")");
	}

