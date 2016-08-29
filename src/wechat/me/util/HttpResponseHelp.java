package wechat.me.util;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JulyLe on 2016/8/23.
 * 处理和解析httpResponse的解析类
 */

public class HttpResponseHelp {
    private Logger logger = Logger.getLogger(HttpResponseHelp.class);
    private HttpResponse httpResponse=null;
    private  String result=null;
    /**
     * 获取微信返回参数的状态码
     * @return  成功返回正确的响应码，失败返回null
     */
    public  String getStateCode(){
        logger.info("开始解析返回的状态码......");
        String statecodepattern = "(\\d+)";
        String statecode =null;
        try {
            if(result==null) {
                result = EntityUtils.toString(httpResponse.getEntity());
            }

            Pattern r1 = Pattern.compile(statecodepattern);
            Matcher m1 = r1.matcher(result);
            if (m1.find( )) {
                statecode=m1.group(0);
                logger.info("获取响应码成功......");
            }else{
                logger.info("获取响应码失败！");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return statecode;
    }
    /**
     * 获取返回体的String串
     * @return
     */
    public  String getEntityString(){
        if(result==null){
            try {
                result = EntityUtils.toString(httpResponse.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取返回中的uuid信息
     * @return  成功返回正确的uuid信息，失败返回null
     */
    public  String getUuid(){
        logger.info("开始解析uuid......");
        String uuidpattern = "uuid = \"(.+)\";";
        Pattern r2 =Pattern.compile(uuidpattern);
        String uuid=null;
        try {
            if(result==null) {
                result = EntityUtils.toString(httpResponse.getEntity());
            }
            Matcher m2 = r2.matcher(result);
            if(m2.find()){
                uuid=m2.group(1);
                logger.info("uuid生成成功......");
            }else {
                logger.info("uuid生成失败！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uuid;
    }
    public  String getRedirecturl(){
        String redirectpattern="=\"(.*)\"";
        Pattern r2=Pattern.compile(redirectpattern);
        Matcher m2 = r2.matcher(result);
        if (m2.find( )) {
            return m2.group(1);
        }
        return null;
    }

    public void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }
}

