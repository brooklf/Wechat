package wechat.me.util;

import net.sf.json.JSONObject;
import wechat.me.service.Contact;
import wechat.me.service.WxTickets;

/**
 * Created by JulyLe on 2016/8/23.
 */
public class JsonHelp {

    /**
     * 获取baseRequest json串  包括uin,sid,skey,deviceid四个参数
     */
    public static String getBaseRequest(){
        JSONObject getobj = new JSONObject();
        getobj.put("Uin", WxTickets.getWxuin());
        getobj.put("Sid",WxTickets.getSid());
        getobj.put("Skey",WxTickets.getSkey());
        getobj.put("DeviceID",WxTickets.getDeviceId());
        return getobj.toString();
    }

    /**
     *
     * @param message  消息内容
     * @param to       发送的联系人
     * @return         构造好的文字消息串
     */
    public static String getTextMeassagejson(String message,String to){
        JSONObject getobj = new JSONObject();
        getobj.put("Type","1");
        getobj.put("Content",message);
        getobj.put("FromUserName", Contact.getMyUsername());
        getobj.put("ToUserName",to);
        String times = String.valueOf(System.currentTimeMillis());
        times = times + String.valueOf(Math.random() * 10000000).substring(0,4);
        getobj.put("LocalID",times);
        getobj.put("ClientMsgId",times);
        return getobj.toString();
    }
    public static String getSendMessage(String message,String to){
        JSONObject getobj = new JSONObject();
        getobj.put("BaseRequest",getBaseRequest());
        getobj.put("Msg",getTextMeassagejson(message,to));
        getobj.put("Scene","0");
        return getobj.toString();
    }


}
