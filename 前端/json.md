### json字符串
（1）JSON对象，是满足JSON数据格式的JS对象
<pre>
例如： {name:"abc"}
  最正规的 JSON 数据是连属性名称都要被包括起来的
  例如： {"name":"abc"}
</pre>
（2）JSON 格式的字符串的关键问题-- 它仅仅是一个字符串，只不过满足了 JSON 的数据格式 
<pre>
  例如：'{"name":"abc"}'
</pre>
<pre>
注意：通常在回调函数的function(data){}中，需要对返回的json字符串，转为json对象
 function(data){
   //方法一：eval()
   _data = eval("(" + data + ")");
   //方法二：$.parseJson()
   $data = $.parseJson(data)
 }
</pre>