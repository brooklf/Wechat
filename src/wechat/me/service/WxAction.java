package wechat.me.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import sun.plugin2.message.Message;
import wechat.me.util.HttpRequestHelp;
import wechat.me.util.JsonHelp;
import wechat.me.util.RegularExpressionHelp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JulyLe on 2016/8/23.
 */
public class WxAction {
    private static Logger logger = Logger.getLogger(WxAction.class);


    /**
     * 获取联系人列表
     */
    public static String getContact(){
        logger.info("开始获取联系人信息......");
        Map<String,String> headers= new HashMap<String,String>();
        headers.put("cookie",WxTickets.getContactCookie());
        HttpResponse response = HttpRequestHelp.post(WxTickets.getBasicUrl()+"/webwxgetcontact"+"?pass_ticket="+WxTickets.getPass_ticket(),headers,true,null);
        try {
            String getContact= EntityUtils.toString(response.getEntity(),"utf-8");
            return getContact;
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("联系人信息获取成功......");
        return null;
    }

    /**
     * 发送微信消息
     */
    public static boolean sendMsg(String message,String to){
        logger.info("开始发送信息......");
        Map<String,String> headers= new HashMap<String,String>();
        headers.put("cookie",WxTickets.getContactCookie());

        StringEntity entity = new StringEntity(JsonHelp.getSendMessage(message,to),"utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");

        HttpResponse response = HttpRequestHelp.post(WxTickets.getBasicUrl()+"/webwxsendmsg?pass_ticket="+WxTickets.getPass_ticket(),headers,true,entity);
        HttpEntity entity1 = response.getEntity();
        try {
            /**
             * 判断是否发送成功逻辑
             */
            System.out.println(EntityUtils.toString(entity1,"utf-8"));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *  同步刷新，探知微信的状态
     *  window.synccheck={retcode:"xxx",selector:"xxx"}
     retcode:
     0 正常
     1100 失败/登出微信
     selector:
     0 正常
     2 新的消息
     7 进入/离开聊天界面
     */
    public static Map<String,String> syncCheck(){
        List<String> listurl = new ArrayList<String>();
        listurl.add("https://webpush.wx.qq.com/cgi-bin/mmwebwx-bin/synccheck");  //同步的地址
/*        listurl.add("https://webpush.weixin.qq.com/cgi-bin/mmwebwx-bin/synccheck");
        listurl.add("https://webpush1.wechat.com/cgi-bin/mmwebwx-bin/synccheck");
        listurl.add("https://webpush2.wechat.com/cgi-bin/mmwebwx-bin/synccheck");
        listurl.add("https://webpush.wechat.com/cgi-bin/mmwebwx-bin/synccheck");*/
     /*   System.out.println(WxTickets.getRedirectUrl());*/
        for(int i=0;i<listurl.size();i++){
            String times=String.valueOf(System.currentTimeMillis());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String time1=String.valueOf(System.currentTimeMillis());
            String time2=String.valueOf(System.currentTimeMillis());
            params.add(new BasicNameValuePair("r", time2));
            params.add(new BasicNameValuePair("skey",WxTickets.getSkey()));
            params.add(new BasicNameValuePair("sid",WxTickets.getSid()));
            params.add(new BasicNameValuePair("uin",WxTickets.getWxuin()));
            params.add(new BasicNameValuePair("deviceid",WxTickets.getDeviceId()));
            params.add(new BasicNameValuePair("synckey", WxTickets.getSyncKey()));
            params.add(new BasicNameValuePair("_", time1));
            Map<String,String> headers = new HashMap<String,String>();
            headers.put("cookie",WxTickets.getSyncCookie());
            String str = null;
            try {
                 str = EntityUtils.toString(new UrlEncodedFormEntity(params, "utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String url = listurl.get(i)+"?"+str;
            HttpResponse response=HttpRequestHelp.get(url,headers);
            //提取状态码
            try {
                String result =EntityUtils.toString(response.getEntity());
                System.out.println(result);
                Map<String,String> synccheck = new HashMap<String,String>();
                synccheck.put("retcode", RegularExpressionHelp.getRetcode(result));
                synccheck.put("selector",RegularExpressionHelp.getSelector(result));
                WxTickets.setRetcode(RegularExpressionHelp.getRetcode(result));
                WxTickets.setSelector(RegularExpressionHelp.getSelector(result));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 收取微信消息，返回所有消息的json串
     */
    public static String Webwxsync(){
        String url = WxTickets.getBasicUrl()+"/webwxsync?sid="+WxTickets.getSid()+"&skey="+WxTickets.getSkey()+"&pass_ticket="+WxTickets.getPass_ticket();
        JSONObject params = new JSONObject();
        params.put("BaseRequest",JsonHelp.getBaseRequest());
        params.put("SyncKey",WxTickets.getOriginSyncKey());
        params.put("rr",String.valueOf(Math.abs(~System.currentTimeMillis())));
        StringEntity entity = new StringEntity(params.toString(),"utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("cookie",WxTickets.getSyncCookie());
        HttpResponse response = HttpRequestHelp.post(url,headers,false,entity);
        try {
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
          /*  System.out.print(result);*/
            JSONObject getobj = JSONObject.fromObject(result);
            JSONObject obj =JSONObject.fromObject(getobj.getString("SyncKey"));
            WxTickets.setOriginSyncKey(obj.toString());
           /* System.out.println(obj.toString());*/
            JSONArray list = obj.getJSONArray("List");
            JSONObject js = (JSONObject) list.get(0);
            String Synckey=String.valueOf(js.get("Key"))+"_"+String.valueOf(js.get("Val"));;
            for(int i=1;i<list.size();i++){
                js = (JSONObject) list.get(i);
                Synckey +="|"+String.valueOf(js.get("Key"))+"_"+String.valueOf(js.get("Val"));
            }
            WxTickets.setSyncKey(Synckey);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

