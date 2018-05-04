# JSON解析
### 一、常用框架
<pre>
1.jsonlib

2.fastjson

3.jackson
</pre>

### 二、
<pre>
/**
	 * 解析json请求参数
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> readRequestParam(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 读取请求内容
		// BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String paramJson = sb.toString();

		JSONObject jsonObject = JSONObject.fromObject(paramJson);

		logger.info(new StringBuffer("请求json= {'identityId':'").append(jsonObject.get("identityId")).append("','sign':'").append(jsonObject.get("sign")).append("'}").toString());

		return jsonObject;
	}
</pre>