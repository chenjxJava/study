# ARMS
### 一、ARMS开发文档
[ARMS Open API 概述](https://help.aliyun.com/document_detail/42924.html)

### 二、添加maven依赖
<pre>
1.pom.xml中添加maven依赖
&lt;dependencies>
   &lt;dependency>
      &lt;groupId>com.aliyun</groupId>
      &lt;artifactId>aliyun-java-sdk-arms</artifactId>
      &lt;version>2.1.0</version>
   &lt;/dependency>
   &lt;dependency>
       &lt;groupId>com.aliyun</groupId>
       &lt;artifactId>aliyun-java-sdk-core</artifactId>
       &lt;version>2.2.0</version>
   &lt;/dependency>
&lt;/dependencies>
</pre>

### 三、
<pre>
下钻数据集查询接口

通用数据集查询接口

import java.util.ArrayList;
 import java.util.List;
 import com.aliyuncs.DefaultAcsClient;
 import com.aliyuncs.IAcsClient;
 import com.aliyuncs.arms.model.v20180620.ARMSQueryDataSetRequest;
 import com.aliyuncs.arms.model.v20180620.ARMSQueryDataSetRequest.Dimensions;
 import com.aliyuncs.arms.model.v20180620.ARMSQueryDataSetResponse;
 import com.aliyuncs.exceptions.ClientException;
 import com.aliyuncs.profile.DefaultProfile;
 import com.aliyuncs.profile.IClientProfile;
 public class ARMSGeneralQuery {
     public static void main(String[] args) throws ClientException {
         // 用户主账号/RAM 子账号的 AK，或者 RAM 用户角色的临时安全令牌的 AK
         String accessKeyId = "xxx";
         // 用户主账号/RAM 子账号的 SK，或者 RAM 用户角色的临时安全令牌的 SK
         String accessKeySecret = "xxx";
         //Region和endpoint保持一致，具体内容和ARMS的region绑定，
         String endpoint = "xxx";//例如 cn-hangzhou
         String region = "xxx";//例如 cn-hangzhou
         //产品名请固定填"ARMS"
         String productName = "ARMS";
         //如果是非杭州区域，请改写，如cn-beijing，
         String domain = "arms.cn-hangzhou.aliyuncs.com";
         IClientProfile profile = DefaultProfile.getProfile(region, accessKeyId, accessKeySecret);
         DefaultProfile.addEndpoint(endpoint, region, productName, domain);
         // 设置业务参数
         ARMSQueryDataSetRequest armsQueryDataSetRequest = new ARMSQueryDataSetRequest();
         //DatasetID在数据集管理页面上可查询到
         armsQueryDataSetRequest.setDatasetId(1340l);
         //查询数据的返回间隔，请务必自行保证为60的倍数
         armsQueryDataSetRequest.setIntervalInSec(60);
         //设置查询的起始时间，该例子为查询最近十分钟数据，注意单位为毫秒
         armsQueryDataSetRequest.setMinTime(System.currentTimeMillis() - 600000);
         armsQueryDataSetRequest.setMaxTime(System.currentTimeMillis());
         // ****后续的2、3示例代码插入此处****
         IAcsClient client = new DefaultAcsClient(profile);
         try {
             //发送请求
             ARMSQueryDataSetResponse armsQueryDataSetResponse = client.getAcsResponse(armsQueryDataSetRequest);
             //获取并打印请求结果
             System.out.println(armsQueryDataSetResponse.getData());
         } catch (ClientException e) {
             e.printStackTrace();
         }
     }
 }
</pre>