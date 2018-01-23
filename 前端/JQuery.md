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

#### 1.4 编码
<pre>
// 转为unicode 编码  
function encodeUnicode(str) {  
    var res = [];  
    for ( var i=0; i<str.length; i++ ) {  
    res[i] = ( "00" + str.charCodeAt(i).toString(16) ).slice(-4);  
    }  
    return "\\u" + res.join("\\u");  
}  
  
// 解码  
function decodeUnicode(str) {  
    str = str.replace(/\\/g, "%");  
    return unescape(str);  
}  
</pre>

Jquery解码：decodeURIComponent(url);

Jquery编码：encodeURIComponent(url);

### 1.5 限制浏览器后退
<pre>
//防止页面后退
history.pushState(null, null, document.URL);
window.addEventListener('popstate', function () {
	history.pushState(null, null, document.URL);
});


<form>
	<input type="hidden" name="refreshedFlag" value="no">
</form>
<script type="text/javascript">
	onload=function(){
		var e = $("input[type='hidden']")[0];
		if(e.value=="no") {
			e.value="yes";
		}else{
			e.value="no";
			location.reload();
		}
	}
</script>
</pre>

### 1.6 浏览器后退，页面刷新
<pre>
&lt;script type="text/javascript"&gt;
	window.onload = function(){
		if(window.name!="hasLoad"){
			location.reload();
			window.name = "hasLoad";
		}else{
			window.name="";
		}
	}
&lt;/script&gt;
</pre>


