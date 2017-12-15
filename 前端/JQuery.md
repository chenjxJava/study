# JQuery
### 一、
#### 1.常用方法
#### 1.1 绑定click事件
<pre>
方法一：
$("button").click(function(){
  $("p").slideToggle();
 }); 

方法二：
$("button").bind("click",function(){
  $("p").slideToggle();
 }); 
</pre>

#### 1.2 eval()----转化json字符串为json对象
<pre>
function(data){
   //获取json对象
   _data = eval("(" + data + ")");
}
</pre>

#### 1.3 enter键
<pre>
// enter键模拟点击登入按钮
$('.login').keyup(function (event) {
    if (event.keyCode == 13) {
         $('#login').click(); //点击登入按钮
    }
});
</pre>
