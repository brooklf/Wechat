package wechat.me.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by JulyLe on 2016/8/23.
 * 发送http请求的辅助类，主要有get,方法和post方法
 */
public class HttpRequestHelp {
    /**
     * 构建get请求
     * @param url  请求地址
     * @param headers  请求头，根据key-value对构建请求头信息
     * @return 请求结果成功返回response对象，失败返回null；
     */
    public static HttpResponse get(String url, Map<String,String> headers){
        System.setProperty("jsse.enableSNIExtension", "false");  //因为jdk原因请求https必须加这一句
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response=null;
        HttpGet httpGet = new HttpGet(url);
        if(headers!=null){                                      //如果头信息不为空，则给请求加上头信息
            Set<String> keySet = headers.keySet();
            for(String key:keySet){
                httpGet.addHeader(key,headers.get(key));
            }
        }
        try {
            response =client.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response!=null){
            return response;
        }else{
            return response;
        }
    }

    /**
     *构建post请求
     * @param url   请求地址
     * @param headers  请求头信息
     * @param IsJson    是否为json请求
     * @param entity    请求体
     * @return 请求成功返回response，失败返回null；
     */
    public static HttpResponse post(String url, Map<String,String> headers, boolean IsJson, HttpEntity entity) {
        System.setProperty("jsse.enableSNIExtension", "false");  //因为jdk原因请求https必须加这一句
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response=null;
        HttpPost post = new HttpPost(url);
        if(headers!=null){                                      //如果头信息不为空，则给请求加上头信息
            Set<String> keySet = headers.keySet();
            for(String key:keySet){
                post.addHeader(key,headers.get(key));
            }
        }
        if(IsJson){
            post.setHeader("ContentType","application/json; charset=UTF-8");
        }
        post.setEntity(entity);
        try {
            response =client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response!=null){
            return response;
        }else{
            return response;
        }
    }
}
