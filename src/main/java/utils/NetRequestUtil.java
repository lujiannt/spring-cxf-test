package utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * HttpClient工具类
 *
 * @author lujian
 */
public class NetRequestUtil {
    /**
     * socket最大延时
     */
    private final static int SOCKET_TIMEOUT = 15000;
    /**
     * 连接最大延时
     */
    private final static int CONNECT_TIMEOUT = 60000;
    /**
     * 默认字符
     */
    private final static String DEF_CHATSET = "UTF-8";


    /**
     * 发送get请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String sendGetRequest(String url, LinkedHashMap<String, String> params) {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        CloseableHttpResponse response = null;
        String rs = null;

        try {
            HttpGet get = new HttpGet(getUrlEncode(url, params));
            response = client.execute(get);

            int statusCode = response.getStatusLine().getStatusCode();
            String statusStr = response.getStatusLine().getReasonPhrase();
            if (statusCode == HttpStatus.SC_OK && statusStr.equals("OK")) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    rs = EntityUtils.toString(entity, DEF_CHATSET);
                }
                System.out.println("respStr = " + rs);
            } else {
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return rs;
    }

    /**
     * 发送post请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String sendPostRequest(String url, LinkedHashMap<String, String> params) {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        CloseableHttpResponse response = null;
        String rs = null;

        try {
            HttpPost post = new HttpPost(url);
            post.setEntity(new UrlEncodedFormEntity(postUrlEncode(params), DEF_CHATSET));
            response = client.execute(post);

            int statusCode = response.getStatusLine().getStatusCode();
            String statusStr = response.getStatusLine().getReasonPhrase();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    rs = EntityUtils.toString(entity, DEF_CHATSET);
                }
                System.out.println("respStr = " + rs);
            } else {
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return rs;
    }

    /**
     * post发送xml
     *
     * @param url
     * @param xmlData
     * @return
     */
    public static String sendXmlPost(String url, String xmlData) {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        CloseableHttpResponse response = null;
        String rs = null;

        HttpPost post = new HttpPost(url);
        try {
            post.setEntity(new StringEntity(xmlData, "UTF-8"));
            response = client.execute(post);
            HttpEntity entity = response.getEntity();
            rs = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(rs);
        return rs;
    }

    /**
     * 将map型转为请求参数型-get专用
     *
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static URI getUrlEncode(String url, LinkedHashMap<String, String> data)
            throws URISyntaxException {
        List<NameValuePair> list = new LinkedList<>();
        URIBuilder uriBuilder = new URIBuilder(url);
        Set<String> keys = data.keySet();
        for (String key : keys) {
            BasicNameValuePair param = new BasicNameValuePair(key, data.get(key));
            list.add(param);
        }

        uriBuilder.setParameters(list);
        URI uri = uriBuilder.build();
        System.out.println("url : " + uri.toString());
        return uri;
    }

    /**
     * 将map型转为请求参数型-post专用
     *
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     */
    public static List<NameValuePair> postUrlEncode(LinkedHashMap<String, String> data) {
        List<NameValuePair> list = new LinkedList<>();
        Set<String> keys = data.keySet();
        for (String key : keys) {
            BasicNameValuePair param = new BasicNameValuePair(key, data.get(key));
            list.add(param);
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("parkingId", "2");
        params.put("plateNo", "11");
//        System.out.println(sendGetRequest("https://www.baidu.com", params));
        System.out.println(sendPostRequest("http://ip-api.com/json", params));
    }
}