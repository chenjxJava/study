授权服务介绍
----------

## 简介

授权微服务，可供网关gateway实现微服务对外权限的授予

## 使用指南

### 初始账号

本例中初使化的客户端与用户账号

client_id:     `test_client`

client_secret: `test_secret`

username: `admin`

password: `password`

### JWT Token介绍

本例中access_token Payload 负载（中间部分）base64解码后结构如下

```json
{
   "user_name": "admin",   //用户username，users表中username字段
   "scope": [
       "read"              //授权权限范围
   ], 
   "organization": "admin", //组织，该字段为自定义，自定义方法见 开发指南中
   "exp": 1531975621,       //过期时间
   "authorities": [         //授权权限，本例子中为用户授权的角色名，roles表中code字段
       "ADMIN"
   ], 
   "jti": "23408d38-8cdc-4460-beac-24c76dc7629a",  //jwt token的id
   "client_id": "test_client"                      //客户端id，oauth_client_details表中client_id
}
```


### 1.密码模式,grant_type=password
> 用途：可用于用户通过前端应用登陆、使用应用，如app，web等终端

##### 1.1 Params列表，socpe=read&grant_type=password 
![](../pic/5f9a64ce569c7.png)

##### 1.2 Authorization 添加Basic Auth,用户名密码test_client,test_secret
![](../pic/5f9a64fb7e344.png)

##### 1.3 Body username=admin,password=password
![](../pic/5f9a651a394dc.png)


请求报文

```
POST /oauth/token?scope=read&grant_type=password HTTP/1.1
Host: localhost:8000
Authorization: Basic dGVzdF9jbGllbnQ6dGVzdF9zZWNyZXQ=
Cache-Control: no-cache
Content-Type: application/x-www-form-urlencoded

username=admin&password=password
```
响应报文

```json
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiYWRtaW4iLCJleHAiOjE2MDM5NjU1MzgsImF1dGhvcml0aWVzIjpbIkFETUlOIl0sImp0aSI6Ii1vbVZITWwxLTBfRldzNEtpM0lpV1VWYjZ2QSIsImNsaWVudF9pZCI6InRlc3RfY2xpZW50In0.yg5B0PHNmBOcrormBmW-bfaf0fPLsW_RXD34yYMdYHE",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiYWRtaW4iLCJhdGkiOiItb21WSE1sMS0wX0ZXczRLaTNJaVdVVmI2dkEiLCJleHAiOjE2MDQwNjYzMzgsImF1dGhvcml0aWVzIjpbIkFETUlOIl0sImp0aSI6ImpqdnlRdk5UWlp1dEg1RXp2ZEZocDJiOWQ0WSIsImNsaWVudF9pZCI6InRlc3RfY2xpZW50In0.Eyf2FCyqBQ00ZLfGtSDt5FVO85e4p0rklnVP7shtn38",
    "expires_in": 7199,
    "scope": "read",
    "organization": "admin",
    "jti": "-omVHMl1-0_FWs4Ki3IiWUVb6vA"
}
```


#### 客户端模式，grant_type=client_credentials

用途：可用于接口开放给第三方商户，商户申请client_id和密码，即可调用授权的接口

![](../pic/5f9a74eb2f8e9.png)

请求报文

```properties
POST /oauth/token?scope=read&grant_type=client_credentials HTTP/1.1
Host: localhost:8000
Authorization: Basic dGVzdF9jbGllbnQ6dGVzdF9zZWNyZXQ=
Cache-Control: no-cache
```
响应报文

```json
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJyZWFkIl0sIm9yZ2FuaXphdGlvbiI6InRlc3RfY2xpZW50IiwiZXhwIjoxNTMxOTczMzAzLCJqdGkiOiI0NjBlYWRkNi1iNjU3LTRkNzAtYTFjZi00MWJjYWM5OTFkNzgiLCJjbGllbnRfaWQiOiJ0ZXN0X2NsaWVudCJ9.d_7f1N81hKWakA0eQeHOqW88-mYjYGgXHChMR_S6d6w",
  "token_type": "bearer",
  "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ6aG91dGFvbyIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiemhvdXRhb28iLCJhdGkiOiI1MWI4NjhkMS0wY2UxLTRmYjgtOTAxZC05YzdiZmZjMGFkYmIiLCJleHAiOjE1MzQ1MjI2MTgsImF1dGhvcml0aWVzIjpbIkFETUlOIiwiSVQiXSwianRpIjoiMGU2N2Q5MDEtOThlMC00ZTk3LTkwNzgtODllMTBmZTRjOGI2IiwiY2xpZW50X2lkIjoidGVzdF9jbGllbnQifQ.zNtWWG8xxPsjTZKghOjyGNDjnhHqnPvikfqN1uynh3U",
  "expires_in": 43199,
  "scope": "read",
  "organization": "test_client",
  "jti": "460eadd6-b657-4d70-a1cf-41bcac991d78"
}
```

#### 授权码模式，grant_type=authorization_code

用途：可用开放平台账户给第三方商户，商户申请client_id和密码请求用户授权，用户授权商户即可调用平台授权的接口获取数据，类似微信、支付宝授权登陆

**第一步：用户登陆授权陆**

1. 用户跳转至平台

`http://host1:8000/oauth/authorize?response_type=code&client_id=test_client&scope=read&state=test&redirect_uri=http://baidu.com`

```
client_id： 商户申请的client_id(oauth_client_details表中的记录)
state： 该参数在跳转回去时原样带回
redirect_uri： 该参数要与商户申请client_id时登记的url(oauth_client_details表中的web_server_redirect_uri字段)一样
```

2. 用户进入登陆页面，输入用户名和密码登陆

![](../pic/5f9a750ea6917.png)

3. 用户点击 "授权"或"拒绝"

![](../pic/5f9a752ebecc9.png)

4. 用户同意授权后，浏览器自动重定向至redirect_uri并带上code和state参数

![](../pic/5f9a75547a51e.png)


**第二步：根据url上带的code获取用户的access_token**

![](../pic/5f9a76120cb05.png)
请求报文

```
POST /oauth/token?grant_type=authorization_code&code=A32sYi&redirect_uri=http://baidu.com HTTP/1.1
Host: localhost:8000
Authorization: Basic dGVzdF9jbGllbnQ6dGVzdF9zZWNyZXQ=
Cache-Control: no-cache
```
响应报文

```json
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJyZWFkIl0sIm9yZ2FuaXphdGlvbiI6InRlc3RfY2xpZW50IiwiZXhwIjoxNTMxOTczMzAzLCJqdGkiOiI0NjBlYWRkNi1iNjU3LTRkNzAtYTFjZi00MWJjYWM5OTFkNzgiLCJjbGllbnRfaWQiOiJ0ZXN0X2NsaWVudCJ9.d_7f1N81hKWakA0eQeHOqW88-mYjYGgXHChMR_S6d6w",
  "token_type": "bearer",
  "expires_in": 43199,
  "scope": "read",
  "organization": "test_client",
  "jti": "460eadd6-b657-4d70-a1cf-41bcac991d78"
}
```

#### 刷新access_token

用途：使用refresh_token更新access_token

![](../pic/5f9a757cf36e2.png)

请求报文

```
POST /oauth/token?scope=read&amp;grant_type=refresh_token&amp;refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiYWRtaW4iLCJhdGkiOiJlODA5MDRkYi1mMDBkLTRkNDAtOGFlNS0xMWY2OTVlMzZjMTEiLCJleHAiOjE1MzQ1MjQzMTQsImF1dGhvcml0aWVzIjpbIkFETUlOIl0sImp0aSI6Ijk0OGUxZTYxLTBkOTUtNGYzMC04YWNlLWFmNDcyNjU2ZWNiNCIsImNsaWVudF9pZCI6InRlc3RfY2xpZW50In0.XrvwAi14NTJXm029CGFD3BsPgZdYQ7u1nszYlf42Eo8 HTTP/1.1
Host: host1:8000
Authorization: Basic dGVzdF9jbGllbnQ6dGVzdF9zZWNyZXQ=
Cache-Control: no-cache
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW
```
响应报文

```json
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiYWRtaW4iLCJleHAiOjE1MzE5NzU2MjEsImF1dGhvcml0aWVzIjpbIkFETUlOIl0sImp0aSI6IjIzNDA4ZDM4LThjZGMtNDQ2MC1iZWFjLTI0Yzc2ZGM3NjI5YSIsImNsaWVudF9pZCI6InRlc3RfY2xpZW50In0.qawS1Z4j_h4vNx10GBC_Y_PHM1LLSQt64eniWLGzsJY",
  "token_type": "bearer",
  "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiYWRtaW4iLCJhdGkiOiIyMzQwOGQzOC04Y2RjLTQ0NjAtYmVhYy0yNGM3NmRjNzYyOWEiLCJleHAiOjE1MzQ1MjQzMTQsImF1dGhvcml0aWVzIjpbIkFETUlOIl0sImp0aSI6Ijk0OGUxZTYxLTBkOTUtNGYzMC04YWNlLWFmNDcyNjU2ZWNiNCIsImNsaWVudF9pZCI6InRlc3RfY2xpZW50In0.eHRWxFZDJDq_2RDPL4DhyMy7IwF8GHn_LQU5j7ZkF8k",
  "expires_in": 43199,
  "scope": "read",
  "organization": "admin",
  "jti": "23408d38-8cdc-4460-beac-24c76dc7629a"
}
```

#### 自定义手机验证码模式，grant_type=mobile

用途：可用于用户通过手机和验证码在前端应用登陆、使用应用

![](../pic/5f9a733dd009b.png)

请求报文

```
POST /oauth/token HTTP/1.1
Host: http://101.68.86.228:8000
Cache-Control: no-cache
Content-Type: application/x-www-form-urlencoded

username=15619841000&password=123456&client_id=test_client&client_secret=test_secret&scope=read&grant_type=mobile
```
响应报文

```json
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDM5NjUxODEsInVzZXJfbmFtZSI6IjE1NjE5ODQxMDAwIiwianRpIjoieFBGbFBHWlkxUXo2N1NPd3NHeE41T2YyTFpvIiwiY2xpZW50X2lkIjoidGVzdF9jbGllbnQiLCJzY29wZSI6WyJyZWFkIl0sIm9yZ2FuaXphdGlvbiI6IjE1NjE5ODQxMDAwIn0.ag9Must8fsf8PH8CpDNNGXqMlKARTVXJCYD95XNMCFI",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiIxNTYxOTg0MTAwMCIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiMTU2MTk4NDEwMDAiLCJhdGkiOiJ4UEZsUEdaWTFRejY3U093c0d4TjVPZjJMWm8iLCJleHAiOjE2MDQwNjU5ODEsImp0aSI6ImxmNVZyOTI0Y2x5WGxnTFctVEhvbTRWU0RFOCIsImNsaWVudF9pZCI6InRlc3RfY2xpZW50In0.oRL8fSCMPG_2Yey3IJPcUqJxG8P6qdrO1fxfcQ6EJgs",
    "expires_in": 7199,
    "scope": "read",
    "organization": "15619841000",
    "jti": "xPFlPGZY1Qz67SOwsGxN5Of2LZo"
}
```

### 开发指南

#### token自定义

见CustomTokenEnhancer类

```java
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = Maps.newHashMap();
        //自定义token内容，加入组织机构信息
        additionalInfo.put("organization", authentication.getName());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
```

#### jwt使用

* jwt配置

见AuthenticationServerConfig类，本例中jwt使用对称加密算法，
也可使用非对称，这里不做实现，如有需要，请自行研究。

``` java
@Bean
public JwtAccessTokenConverter accessTokenConverter() {
   JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
   converter.setSigningKey(signingKey);
   return converter;
}
```

* jwt对称密钥配置项

```yml
spring:
  security:
    oauth2:
      jwt:
        signingKey: 123456
```