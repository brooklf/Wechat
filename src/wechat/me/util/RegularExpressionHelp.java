package wechat.me.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import wechat.me.service.WxTickets;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JulyLe on 2016/8/24.
 */
public class RegularExpressionHelp {

    /**
     * 获取retcode的正则匹配
     * @param result
     */
    public static String getRetcode(String result){
        String retcodePattern="retcode:(.*?),";
        Pattern r2=Pattern.compile(retcodePattern);
        Matcher m2 = r2.matcher(result);
        if (m2.find( )) {
            return m2.group(1);
        }
        return null;
    }

    /**
     * 获取getSelector的正则匹配
     * @param result
     */
    public static String  getSelector(String result){
        String selectorPattern="selector:(.*?)}";
        Pattern r=Pattern.compile(selectorPattern);
        Matcher m = r.matcher(result);
        if (m.find( )) {
            return m.group(1);
        }
        return null;

    }

    /**
     * 获取webwx_data_ticket
     * @param result
     * @return  成功返回webwx_data_ticket,失败返回null
     */
    public static String getWebwx_data_ticket(String result){
        String getWebwx_data_ticketPattern="webwx_data_ticket=(.*?);";
        Pattern r2=Pattern.compile(getWebwx_data_ticketPattern);
        Matcher m2 = r2.matcher(result);
        if (m2.find( )) {
            return m2.group(1);
        }
        return null;
    }

    public static Map<String,String> getMessage(String result){
        JSONObject messageJson = JSONObject.fromObject(result);
        JSONArray AddMsgList = (JSONArray) messageJson.get("AddMsgList");
        JSONObject AddMsg = (JSONObject) AddMsgList.get(0);
        String FromUserName = AddMsg.getString("FromUserName");
        String Content = AddMsg.getString("Content");
        Map<String,String> message = new HashMap<String,String>();
        message.put("username",FromUserName);
        message.put("content",Content);
        return message;
    }



    public static void main(String args[]){
        /*String result ="window.synccheck={retcode:\"0\",selector:\"2\"}";
        System.out.println(RegularExpressionHelp.getRetcode(result));
        System.out.println(RegularExpressionHelp.getSelector(result));*/
        String result1=null;
        JSONObject messageJson = JSONObject.fromObject(result1);
        JSONArray AddMsgList = (JSONArray) messageJson.get("AddMsgList");
        JSONObject AddMsg = (JSONObject) AddMsgList.get(0);
        System.out.println(AddMsg.getString("Content"));

    }

}
