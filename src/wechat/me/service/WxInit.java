package wechat.me.service;

import com.sun.net.httpserver.Headers;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import wechat.me.util.HttpRequestHelp;
import wechat.me.util.HttpResponseHelp;
import wechat.me.util.RegularExpressionHelp;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JulyLe on 2016/8/23.
 * 微信登陆过程的初始化，包括登陆，二维码生成，获取相关凭证的过程
 */

class ImageViewerFrame extends JFrame {
    public ImageViewerFrame(String filename){
        setTitle("扫描微信二维码");
        setLocation(200,100);
        setSize(400,400);
        JLabel label = new JLabel();
        add(label);
        label.setIcon(new ImageIcon(filename));
    }
}
public class WxInit {

    private static String appid = "wx782c26e4c19acffb";   //微信网页接口的appid
    private static String fun = "new";
    private static String lang = "zh_CN";
    private static String uuidrl="https://login.weixin.qq.com/jslogin";  // 获取uuid的url
    private static String qrcodeUrl="https://login.weixin.qq.com/qrcode/"; //获取微信登录二维码的地址
    private static String filepath = "D:\\qrcode.jpg";                    // 二维码保存地址
    private static String basicUrl="https://login.weixin.qq.com/cgi-bin/mmwebwx-bin";
    private static Logger logger = Logger.getLogger(WxInit.class);

    /**
     * 生成uuid
     */
    public static void generateUUID(){
        /*获取uuid所需要的四个参数*/
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("appid",appid));
        nvps.add(new BasicNameValuePair("fun",fun));
        nvps.add(new BasicNameValuePair("lang",lang));
        nvps.add(new BasicNameValuePair("_", String.valueOf(System.currentTimeMillis())));
        HttpEntity entity = null;
        try {
            entity =new UrlEncodedFormEntity(nvps);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpResponseHelp httpResponseHelp = new HttpResponseHelp();
        httpResponseHelp.setHttpResponse(HttpRequestHelp.post(uuidrl,null,false,entity));
        WxTickets.setUuid(httpResponseHelp.getUuid());
    }

    /**
     * 生成二维码，并且进行渲染
     */
    public static void generateQRcode(){
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("t","webwx"));
        nvps.add(new BasicNameValuePair("_",String.valueOf(System.currentTimeMillis())));
        HttpEntity entity = null;
        try {
            entity =new UrlEncodedFormEntity(nvps);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpResponse response=HttpRequestHelp.post(qrcodeUrl+WxTickets.getUuid(),null,false,entity);
        OutputStream os=null;
        try {
            logger.info("开始生成二维码......");
            os = new FileOutputStream(filepath);
            if(os==null)
                logger.info("二维码生成失败！");
            else
                logger.info("二维码生生成成功......");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            response.getEntity().writeTo(os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showImage();
    }
    public static void showImage(){
        logger.info("开始渲染二维码......");
        JFrame frame = new ImageViewerFrame(filepath);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        logger.info("二维码渲染成功请扫描......");
    }

    /**
     * 等待登陆
     * @param tip   标记位，1代表未扫描，0代表已扫描
     */
    public static void waitForLogin(String tip){
        String result ="";    //保存登陆结果
        String statecode="";   //状态码  408登陆超时，200确认登陆，201扫描成功
        int n=1;
        int count =3;
        logger.info("请扫码进行登陆......");
        while(count!=n) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("总共进行"+count+"次验证等待,这是第"+n+"次尝试验证.......");
            HttpResponse response = HttpRequestHelp.get(basicUrl+"/login"+ "?tip=" + tip +"&uuid="+WxTickets.getUuid()+ "&_=" + String.valueOf(System.currentTimeMillis()), null);
            HttpResponseHelp httpResponseHelp = new HttpResponseHelp();
            httpResponseHelp.setHttpResponse(response);
            statecode = httpResponseHelp.getStateCode();
            if(statecode.equals("201")){
                logger.info("扫码成功.....请点击确认......");
            }else if(statecode.equals("200")) {
                logger.info("微信登陆成功......等待进行下一步操作...");
                WxTickets.setRedirectUrl(httpResponseHelp.getRedirecturl());
                break;
            }else if(statecode.equals("408"))
                logger.info("******第"+n+"次尝试登陆超时......******");
            n++;
        }
        if(statecode.equals("408"))
            logger.info("登陆超时......");
        else if(statecode.equals("200"))
            ;
        else
            logger.info("登陆异常......");
    }

    /**
     * 获取微信初始化参数
     */
    public static void getInitParam(){
        logger.info("开始获取微信初始化凭证......");
        HttpResponseHelp httpResponseHelp = new HttpResponseHelp();
        HttpResponse response = HttpRequestHelp.get(WxTickets.getRedirectUrl()+"&fun=new", null);
        httpResponseHelp.setHttpResponse(response);
        String text = httpResponseHelp.getEntityString();
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = DocumentHelper.parseText(text);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Header[] headerses = response.getAllHeaders();
        for(Header header:headerses){
            System.out.println(header.getName()+"******"+header.getValue());
            String value = header.getValue();
            if(RegularExpressionHelp.getWebwx_data_ticket(value)!=null)
                WxTickets.setWebwx_data_ticket(RegularExpressionHelp.getWebwx_data_ticket(value));
        }
        Element root = document.getRootElement();
        WxTickets.setSkey(root.element("skey").getText());
        WxTickets.setSid(root.element("wxsid").getText());
        WxTickets.setWxuin(root.element("wxuin").getText());
        WxTickets.setPass_ticket(root.element("pass_ticket").getText());
        logger.info("微信初始化凭证获取成功");

    }
    /**
     * 微信初始化，获取当前同步秘钥
     */
    public static void getWebwxInit(){
        Map<String,String> headers= new HashMap<String,String>();

        JSONObject jsonParam = new JSONObject();
        JSONObject baseJsonParam = new JSONObject();
        jsonParam.put("Uin",WxTickets.getWxuin());
        jsonParam.put("Sid",WxTickets.getSid());
        jsonParam.put("Skey",WxTickets.getSkey());
        jsonParam.put("DeviceID",WxTickets.getDeviceId());
        baseJsonParam.put("BaseRequest",jsonParam.toString());
        StringEntity entity = new StringEntity(baseJsonParam.toString(),"utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");

        HttpResponse response = HttpRequestHelp.post(WxTickets.getBasicUrl()+"/webwxinit"+"?pass_ticket="+WxTickets.getPass_ticket()+"&skey="+WxTickets.getSkey()+"&r="+String.valueOf(System.currentTimeMillis()),null,true,entity);
        HttpEntity entity1 = response.getEntity();
        /**
         * 处理初始化信息
         */
        try {
            String json= EntityUtils.toString(entity1,"utf-8");
            JSONObject getobj = JSONObject.fromObject(json);
            JSONObject obj =JSONObject.fromObject(getobj.getString("SyncKey"));
            WxTickets.setOriginSyncKey(obj.toString());
            System.out.println(obj.toString());
            JSONArray list = obj.getJSONArray("List");
            JSONObject js = (JSONObject) list.get(0);
            String Synckey=String.valueOf(js.get("Key"))+"_"+String.valueOf(js.get("Val"));;
            for(int i=1;i<list.size();i++){
                js = (JSONObject) list.get(i);
                Synckey +="|"+String.valueOf(js.get("Key"))+"_"+String.valueOf(js.get("Val"));
            }
            WxTickets.setSyncKey(Synckey);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    }
