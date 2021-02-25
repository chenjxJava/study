### 嵌套map工具类

```go
func main() {
	//maps := iris.Map{"data": iris.Map{"code": 200}}
	maps := iris.Map{}
	setKeyValue(maps, "data.success.haha", "ok")
    
	fmt.Println(maps) // map[data:map[success:map[haha:ok]]]
}

/**  
	json[code.success] = ok 
	setKeyValue(json, data.success, ok) 
*/
func SetKeyValue(json map[string]interface{}, key,value string) {
	split := strings.Split(key, ".")
	if len(split)>0 {
		temp := json
		for index , key := range split {

			if index == len(split)-1 {
				temp[key] = value
				return
			}else {
				// 能获取到key
				if _, ok := temp[key]; !ok {
					temp[key] = new(map[string]interface{})
				}

				temp = temp[key].(map[string]interface{})
			}
		}
	}
}
```

