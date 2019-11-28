# java基本开发流程
> 就是大概写一下，SSM（Spring Springmvc Mybatis）框架的基本流程。<br>
> 如果没有框架,可以clone我的ssm-empty工程。
### 0.你的现有一张表
<pre>
// user_t.sql
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE `user_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL,
  `age` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_t
-- ----------------------------
INSERT INTO `user_t` VALUES ('1', 'admin', 'admin', '18');
INSERT INTO `user_t` VALUES ('2', 'zhu', '123456', '2');
</pre>
### 1.编写User实体类
<pre>
package com.chenjx.sys.model;

import java.util.List;

public class User {
	private Long id;
	private String username;
	private String password;
	private String salt;
	
	public User() {
	}

	public User(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", username='" + username + '\'' +
			", password='" + password + '\'' +
			", salt='" + salt + '\'' +
			", roles=" + roles +
			'}';
	}
}
</pre>
### 2.对应实体类，编写UserMapper.xml映射文件
<pre>
先从项目里面随便拷贝一份，或者复制我这份。
&lt;?xml version="1.0" encoding="UTF-8" ?&gt;
&lt;!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" &gt;
&lt;mapper namespace="com.chenjx.sys.dao.UserMapper"&gt;
	&lt;resultMap id="User_ResultMap" type="com.chenjx.sys.model.User"&gt;
		&lt;id column="id" property="id" /&gt;
		&lt;result column="username" property="username" jdbcType="VARCHAR" /&gt;
		&lt;result column="password" property="password" jdbcType="VARCHAR" /&gt;
		&lt;result column="salt" property="salt" jdbcType="VARCHAR" /&gt;
	&lt;/resultMap&gt;

	&lt;select id="findAllList"  resultType="com.chenjx.sys.model.User"&gt;
		SELECT * FROM sys_users u /&gt;
	&lt;/select&gt;
&lt;/mapper&gt;
</pre>

### 3.UserMapper.xml中，namespace对应的就是接口UserMapper
<pre>
package com.chenjx.sys.dao;

import com.chenjx.sys.model.User;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserMapper {
	// 查询所有用户
	List<User> findAllList();
}
</pre>

### 4.编写UserService,可以copy一份UserMapper.
<pre>
package com.chenjx.sys.service;

import com.chenjx.sys.model.User;
import java.util.List;

/**
 * @Author: chenjx
 * @Description:
 * @Date: Created in 17:20 2017-09-04
 * @Modified By:
 */
public interface UserService {
	// 查询所有用户
	List<User> findAllList();
}
</pre>

### 5.编写UserServiceImpl,继承UserService
<pre>
package com.chenjx.sys.service.impl;

import com.chenjx.sys.dao.UserMapper;
import com.chenjx.sys.model.User;
import com.chenjx.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenjx
 * @Description:
 * @Date: Created in 17:22 2017-09-04
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {
	// 注入dao
	@Autowired
	protected UserMapper dao;
	// 普通增删改查，service直接调用dao
	public List<User> findAllList() {
		return dao.findAllList();
	}
}

/* 注意：1.@Service注解必须要加上，为了被扫描到。
		2.必须注入UserMapper
*/
</pre>

### 6.编写Controller
<pre>
package com.javen.controller;

import com.chenjx.sys.model.User;
import com.chenjx.sys.service.UserService;
import common.entity.WebResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * create by chenjx 2017/12/5
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getAllUser")
    public void getAllUser(HttpServletRequest request, HttpServletResponse response) {
        WebResultEntity result = new WebResultEntity();
        List<User> userList = userService.findAllList();
        result.setData(userList);
        result.setMessage("success");
        result.printJson(result,response);
    }
}
</pre>
/**
注意：这里封装了一个WebResultEntity用于数据封装。
*/
<pre>
package common.entity;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import common.utils.password.EncryptAndDecodeHelper;

public class WebResultEntity {
	private String success;
	private String msg;
	private Object data;
	private Integer total_page;
	private Long total_count;

	public Long getTotal_count() {
		return total_count;
	}

	public void setTotal_count(Long total_count) {
		this.total_count = total_count;
	}

	public Integer getTotal_page() {
		return total_page;
	}

	public void setTotal_page(Integer total_page) {
		this.total_page = total_page;
	}

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setErrorMessage(String msg) {
		this.success = "0";
		this.msg = msg;
	}

	public void setMessage(String msg) {
		this.success = "1";
		this.msg = msg;
	}

	public void setMessageAndData(String msg, Object obj) {
		this.success = "1";
		this.msg = msg;
		this.data = obj;
	}
	//session过期的返回
	public void setSessionErrorMessage(String msg) {
		this.success = "2";
		this.msg = msg;
	}
	//实名认证的错误
	public void setRealNameErrorMessage(String msg) {
		this.success = "3";
		this.msg = msg;
	}

	public void setDataSucMessage(String success, String msg) {
		this.success = success;
		this.msg = msg;
	}
	
	public void setErrorMessageAndData(String msg, Object obj)
	{
		this.success = "0";
		this.msg = msg;
		this.data = obj;
	}

	public void printJson(Object obj, HttpServletResponse response) {
		try {
			String json = JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[0]);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printJson_encode(Object obj, HttpServletResponse response) {
		try {
			String json = JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[0]);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(EncryptAndDecodeHelper.encoder(json, "GBK"));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getData() {
		return this.data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}

</pre>