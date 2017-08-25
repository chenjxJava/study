package com.scm.modules.sys.service;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.scm.modules.sys.dao.SendSmsDao;
import com.scm.modules.sys.entity.SendSms;

/**
 * 详情参考：https://help.aliyun.com/document_detail/55284.html?spm=5176.product44282.4.4.eDb4n9
 * 短信API产品的DEMO服务
 * 工程依赖了2个jar包
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 * <p>
 *
 * @author wuch
 */
@Service
@Transactional(readOnly = true)
public class SendSmsService {

	// 产品名称:云通信短信API产品,开发者无需替换
	static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	static final String domain = "dysmsapi.aliyuncs.com";
	// 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	static final String accessKeyId = "LTAINH08ZLoOoveJ";
	static final String accessKeySecret = "oyVtd9p2baMDJIWrwYeQDhwO8hXwae";
	@Autowired
	SendSmsDao sendSmsDao;

	/**
	 * Send sms send sms response.
	 *
	 * @param mobile the mobile
	 * @return the send sms response
	 * @throws ClientException the client exception
	 */
	@Transactional(readOnly = false)
	public SendSmsResponse sendSms(String mobile) throws ClientException {
		//可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		//初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		Random ne = new Random();//实例化一个random的对象ne
		int code = ne.nextInt(9999 - 1000 + 1) + 1000;//为变量赋随机值1000-9999

		//组装请求对象-具体描述见控制台-文档部分内容
		SendSms sendSms = new SendSms();
		//必填:待发送手机号
		sendSms.setPhoneNumbers(mobile);
		//必填:短信签名-可在短信控制台中找到
		sendSms.setSignName("数联中国");
		//必填:短信模板-可在短信控制台中找到
		sendSms.setTemplateCode("SMS_72905080");
		//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		sendSms.setTemplateParam("{\"number\":\"" + code + "\"}");
		//必填:添加记录的时间
		sendSms.setCreateDate(new Date());
		//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		//request.setOutId("yourOutId");

		//hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = null;
		try {
			sendSmsResponse = acsClient.getAcsResponse(sendSms);
			// 成功发送后保存验证码和记录
			if (sendSmsResponse != null && sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				sendSms.setCode(String.valueOf(code));
				sendSmsDao.insert(sendSms);
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return sendSmsResponse;
	}

	/**
	 * Find list by mobile send sms.
	 *
	 * @param phoneNumbers the mobile
	 * @return the send sms
	 */
	public SendSms findListByMobile(String phoneNumbers) {
		return sendSmsDao.findList(phoneNumbers);
	}

}
