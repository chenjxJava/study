# HttpClient
<pre>
protected Map<String, String> checkSensitive(String content, Integer point) {
        Map<String, String> data = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        params.put("content", content);

        String postUrl = ADMINAPI + "filter/getScore";
        String json = HttpClientUtil.postRequest(postUrl, params);
        String words = "";
        if (json == null || json.equals("")) {
            // 通过不替换
            data.put("result", "0");
            data.put("score", "0");
        } else {
            JSONObject jsonObject = JSONObject.fromObject(json);
            JsonOut jsonOut = (JsonOut) JSONObject.toBean(jsonObject, JsonOut.class);
            JSONArray filters = jsonOut.getFilters();
            List<String> sensi = new ArrayList<>();
            for (int i = 0, len = filters.size(); i < len; i++) {
                String word = filters.getJSONObject(i).get("word").toString();
                words += word;
                sensi.add(word);
                if (i < len - 1) {
                    words += ", ";
                }
            }

            if (jsonOut.getScore() == 0) {
                // 通过不替换
                data.put("result", "0");
            } else {
                if (jsonOut.getScore() <= point) {
                    for (String s : sensi) {
                        content = content.replaceAll(s, stringMake("*", s.length()));
                    }
                    // 通过并替换
                    data.put("result", "1");
                } else {
                    // 不通过不替换
                    data.put("result", "2");
                }
            }
            data.put("score", jsonOut.getScore() + "");
        }

        data.put("content", content);
        data.put("words", words);

        // logger.info("敏感词 == " + data);

        return data;
    }
</pre>

<pre>
// HttpClientUtil

package com.yuelan.util;

import com.yuelan.service.impl.BookChapterServiceImpl;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Http client util.
 *
 * @author sunzq  Date 2015年9月28日 上午10:23:27
 */
public class HttpClientUtil {

    private static Logger logger = Logger.getLogger(BookChapterServiceImpl.class);

    /**
     * build query string
     *
     * @param data map对象
     * @return string
     */
    private static String httpBuildQuery(Map<String, String> data) {
        String query = "";
        if (data == null) {
            return query;
        }
        for (Map.Entry<String, String> pair : data.entrySet()) {
            query += pair.getKey() + "=" + pair.getValue() + "&";
        }
        return query.substring(0, query.length() - 1);
    }

    /**
     * 获取XML响应字段
     *
     * @param responseEntity 响应实体
     * @return string
     * @throws Exception 异常
     */
    private static String getResponseContent(HttpEntity responseEntity) throws Exception {
        byte[] bytes = EntityUtils.toByteArray(responseEntity);
        return new String(bytes, "UTF-8");
    }

    /**
     * 根据xml内容获取节点值
     *
     * @param xml      xml
     * @param nodeName 节点
     * @return string xml mess
     */
    @SuppressWarnings("unchecked")
    public static String getXMLMess(String xml, String nodeName) {
        String message = "";
        try {
            message = DocumentHelper.parseText(xml).getRootElement().element(nodeName).getTextTrim();
        } catch (Exception e) {
            try {
                xml = xml.replaceAll("&", "&amp;");
                message = DocumentHelper.parseText(xml).getRootElement().element(nodeName).getTextTrim();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return message;
    }

    /**
     * 获取元素的值
     *
     * @param ele  元素
     * @param node 节点
     * @return 节点内容 val
     */
    public static String getVal(Element ele, String node) {
        String val = null;
        try {
            Element find = ele.element(node);
            if (find != null) {
                val = find.getStringValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }

    /**
     * HTTP请求
     *
     * @param url 请求URL
     * @return string string
     */
    public static String post_comment(String url, List<NameValuePair> nvps) {
        String sHtml = null;
        org.apache.http.client.HttpClient httpClient = new org.apache.http.impl.client.DefaultHttpClient();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            httpPost.setHeader("Accept", "application/x-www-form-urlencoded;charset=UTF-8");
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            sHtml = getResponseContent(entity);
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return sHtml;
    }

    public static String post_comment(String url) {
        return post_comment(url, new ArrayList<NameValuePair>());
    }

    /**
     * HTTP请求
     *
     * @param url 请求url
     * @param xml 请求xml实体
     * @return string string
     */
    public static String postByXml(String url, String xml) {
        String sHtml = null;
        PostMethod post = new PostMethod(url);
        post.setRequestContentLength(EntityEnclosingMethod.CONTENT_LENGTH_CHUNKED);
        post.setRequestBody(xml);//这里添加xml字符串
        post.setRequestHeader("Content-type", "text/xml; charset=utf-8");
        HttpClient httpclient = new HttpClient();//创建 HttpClient 的实例
        int result;
        try {
            result = httpclient.executeMethod(post);
            if (result == 200) {
                Header locationHeader = post.getResponseHeader("appendinfo");
                String errormsg = URLDecoder.decode(locationHeader.getValue(), "UTF-8");
                sHtml = post.getResponseBodyAsString() + ";" + errormsg;
            }
            post.releaseConnection();//释放连接
            ((SimpleHttpConnectionManager)httpclient.getHttpConnectionManager()).shutdown();
            logger.error("post url="+url+";http result code ="+result);
          
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sHtml;
    }

    /**
     * Post XML && Response XML
     *
     * @param url 请求url
     * @param xml 请求xml实体
     * @return string string
     */
    public static String postXml(String url, String xml) {
        PostMethod post = new PostMethod(url);
        post.setRequestContentLength(EntityEnclosingMethod.CONTENT_LENGTH_CHUNKED);
        post.setRequestBody(xml); // 这里添加xml字符串
        post.setRequestHeader("Content-type", "text/xml; charset=utf-8");
        post.setRequestHeader("Accept", "text/xml; charset=utf-8");

        String sHtml = null;
        try {
            HttpClient httpclient = new HttpClient(); // 创建 HttpClient 的实例
            Integer result = httpclient.executeMethod(post);
            if (result == 200) {
                sHtml = new String(post.getResponseBody(), "UTF-8");
            } else {
                sHtml = "[status]" + result.toString();
            }
            post.releaseConnection(); // 释放连接
            ((SimpleHttpConnectionManager)httpclient.getHttpConnectionManager()).shutdown();
            logger.error("post url="+url+";http result code ="+result);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sHtml;
    }

    /**
     * Post XML && Response XML
     *
     * @param url 请求url
     * @param xml 请求xml实体
     * @return string string
     */
    public static String postXmlXunFei(String url, String xml) {
        PostMethod post = new PostMethod(url);
        post.setRequestContentLength(EntityEnclosingMethod.CONTENT_LENGTH_CHUNKED);
        post.setRequestBody(xml); // 这里添加xml字符串
        post.setRequestHeader("Content-type", "text/xml; charset=utf-8");
        post.setRequestHeader("Accept", "application/xml; charset=utf-8");

        String sHtml = null;
        try {
            HttpClient httpclient = new HttpClient(); // 创建 HttpClient 的实例
            Integer result = httpclient.executeMethod(post);
            if (result == 200) {
                sHtml = new String(post.getResponseBody(), "UTF-8");
            } else {
                sHtml = "[status]" + result.toString();
            }
            post.releaseConnection(); // 释放连接
            ((SimpleHttpConnectionManager)httpclient.getHttpConnectionManager()).shutdown();
            logger.error("post url="+url+";http result code ="+result);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sHtml;
    }

    /**
     * Post XML && Response XML (含请求头)
     *
     * @param url     请求url
     * @param xml     请求xml实体
     * @param headers 请求头
     * @return string string
     */
    public static String postXml(String url, String xml, Map<String, String> headers) {
        PostMethod post = new PostMethod(url);
        post.setRequestContentLength(EntityEnclosingMethod.CONTENT_LENGTH_CHUNKED);
        post.setRequestBody(xml); // 这里添加xml字符串
        // post.setRequestHeader("x-sourceid", "204019");
        // post.setRequestHeader("x-sourceip", x_sourceip);
        // post.setRequestHeader("x-remoteip", x_remoteip);
        if (headers != null) {
            for (Map.Entry<String, String> item : headers.entrySet()) {
                post.setRequestHeader(item.getKey(), item.getValue());
            }
        }
        post.setRequestHeader("Content-type", "text/xml; charset=utf-8");
        post.setRequestHeader("Accept", "text/xml; charset=utf-8");

        String sHtml = null;
        try {
            HttpClient httpclient = new HttpClient(); // 创建 HttpClient 的实例
            Integer result = httpclient.executeMethod(post);
            if (result == 200) {
                sHtml = new String(post.getResponseBody(), "UTF-8");
            } else {
                sHtml = "[status]" + result.toString();
            }
            post.releaseConnection(); // 释放连接
            ((SimpleHttpConnectionManager)httpclient.getHttpConnectionManager()).shutdown();
            logger.error("post url="+url+";http result code ="+result);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sHtml;
    }

    /**
     * Get 请求带参数
     *
     * @param url    请求url
     * @param params 参数
     * @return string request
     */
    public static String getRequest(String url, Map<String, String> params) {
        String sHtml = null;
        org.apache.http.client.HttpClient httpClient = new org.apache.http.impl.client.DefaultHttpClient();
        try {
            if (params != null) {
                String paramStr = httpBuildQuery(params);
                if (url.contains("?")) {
                    url += "&" + paramStr;
                } else {
                    url += "?" + paramStr;
                }
            }
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
            httpGet.setHeader("Accept", "application/json;charset=UTF-8");
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            sHtml = getResponseContent(entity);
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return sHtml;
    }

    /**
     * Post 请求
     *
     * @param url    请求地址
     * @param params 参数
     * @return string string
     */
    public static String postRequest(String url, Map<String, String> params) {
        String sHtml = null;
        org.apache.http.client.HttpClient httpClient = new org.apache.http.impl.client.DefaultHttpClient();
        try {
            HttpPost httpPost = new HttpPost(url);
            if (params != null) {
                List<NameValuePair> paramNvp = new ArrayList<>();
                for (Map.Entry<String, String> pair : params.entrySet()) {
                    paramNvp.add(new BasicNameValuePair(pair.getKey(), pair.getValue()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(paramNvp, "UTF-8"));
            }
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            httpPost.setHeader("Accept", "application/json;charset=UTF-8");
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            sHtml = getResponseContent(entity);
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return sHtml;
    }

    /**
     * 手机短信功能
     *
     * @param toNums
     * @param content
     */
    public static Integer postSMSRequest(String toNums, String content) {
        ConnectionManager connectionManager = new ConnectionManager(800);
        org.apache.http.client.HttpClient client = connectionManager.getHttpClient();
        // logger.info("postSMSRequest(), " + "toNums: " + toNums + " content: " + content);
        try {
            StringBuilder uriString = new StringBuilder();

            // if (GlobalConstant.isServerTestEnvironment) {
            // 	uriString.append("http://10.211.58.186:9703/mktweb/mo?from=10086&transactionid=123456&channelid=4&msgtype=2&servicetype=30&priority=30");
            // } else {
            // 	uriString.append("http://192.168.5.90:9709/mktweb/mo?from=10086&transactionid=123456&channelid=4&msgtype=2&servicetype=30&priority=30");
            // }

            // uriString.append("http://192.168.5.90:9709/mktweb/mo?from=10086&transactionid=123456&channelid=4&msgtype=2&servicetype=30&priority=30");
            uriString.append("http://192.168.5.90:9703/mktweb/mo?from=10086&transactionid=123456&channelid=4&msgtype=2&servicetype=30&priority=30");
            uriString.append("&to=" + toNums);
            uriString.append("&content=");
            uriString.append(URLEncoder.encode(content, "UTF-8"));

            HttpPost httpUriRequest = new HttpPost(uriString.toString());
            HttpResponse httpResponse = client.execute(httpUriRequest);
            int status = httpResponse.getStatusLine().getStatusCode();

            // logger.info("postSMSRequest(), " + "Response Code : " + status);
            // BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            // StringBuffer result = new StringBuffer();
            // String line = "";
            // while ((line = rd.readLine()) != null) {
            //     result.append(line);
            // }
            // logger.info("postSMSRequest(), result:" + result.toString());

            connectionManager.destroy();
            return status;
        } catch (Exception e) {
            e.printStackTrace();
            return 500;
        }
    }

}

</pre>