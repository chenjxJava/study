### json字符串
（1）JSON 对象是满足 JSON 数据格式的 JS 对象
    例如： {name:"abc"}
    最正规的 JSON 数据是连属性名称都要被包括起来的
    例如： {"name":"abc"}

（2）JSON 格式的字符串的关键问题-- 它仅仅是一个字符串，只不过满足了 JSON 的数据格式 
    例如：'{"name":"abc"}'


###### 　注意：通常在回调函数的function(data){}中，需要对返回的json字符串，进行
	function(data){
	   //获取json对象
	   _data = eval("(" + data + ")");
	}