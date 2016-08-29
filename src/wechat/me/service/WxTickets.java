package wechat.me.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JulyLe on 2016/8/23.
 * 存放微信登陆过程中的各种凭证
 */
public class WxTickets {
    private static String uuid;
    private static String redirectUrl;
    private static String deviceId="e312495766220816";
    private static String pass_ticket;
    private static String skey;
    private static String sid;
    private static String wxuin;
    private static String webwxuvid;
    private static String contactCookie;
    private static String basicUrl=null;
    private static String syncKey;
    private static String webwx_data_ticket;
    private static String syncCookie;
    private static String originSyncKey;
    private static String retcode="0";
    private static String selector="0";

    public static String getSelector() {
        return selector;
    }

    public static void setSelector(String selector) {
        WxTickets.selector = selector;
    }

    public static String getRetcode() {
        return retcode;
    }

    public static void setRetcode(String retcode) {
        WxTickets.retcode = retcode;
    }

    public static String getOriginSyncKey() {
        return originSyncKey;
    }

    public static void setOriginSyncKey(String originSyncKey) {
        WxTickets.originSyncKey = originSyncKey;
    }

    public static String getSyncCookie() {
        syncCookie ="wxuin="+getWxuin()+";wxsid="+getSid()+";webwx_data_ticket="+getWebwx_data_ticket();
        return syncCookie;
    }

    public static String getWebwx_data_ticket() {
        return webwx_data_ticket;
    }

    public static void setWebwx_data_ticket(String webwx_data_ticket) {
        WxTickets.webwx_data_ticket = webwx_data_ticket;
    }

    public static String getSyncKey() {
        return syncKey;
    }

    public static void setSyncKey(String syncKey) {
        WxTickets.syncKey = syncKey;
    }

    public static String getBasicUrl(){
        if(basicUrl==null){
            String redirectpattern = "(.*?)/webwxnewloginpage";
            Pattern r2 = Pattern.compile(redirectpattern);
            Matcher m2 = r2.matcher(redirectUrl);
            if (m2.find()) {
                basicUrl= m2.group(1);
            }
        }
        return basicUrl;
    }

    public static String getUuid() {
        return uuid;
    }

    public static void setUuid(String uuid) {
        WxTickets.uuid = uuid;
    }

    public static String getRedirectUrl() {
        return redirectUrl;
    }

    public static void setRedirectUrl(String redirectUrl) {
        WxTickets.redirectUrl = redirectUrl;
    }

    public static String getDeviceId() {
        return deviceId;
    }

    public static void setDeviceId(String deviceId) {
        WxTickets.deviceId = deviceId;
    }

    public static String getPass_ticket() {
        return pass_ticket;
    }

    public static void setPass_ticket(String pass_ticket) {
        WxTickets.pass_ticket = pass_ticket;
    }

    public static String getSkey() {
        return skey;
    }

    public static void setSkey(String skey) {
        WxTickets.skey = skey;
    }

    public static String getSid() {
        return sid;
    }

    public static void setSid(String sid) {
        WxTickets.sid = sid;
    }

    public static String getWxuin() {
        return wxuin;
    }

    public static void setWxuin(String wxuin) {
        WxTickets.wxuin = wxuin;
    }

    public static String getWebwxuvid() {
        return webwxuvid;
    }

    public static void setWebwxuvid(String webwxuvid) {
        WxTickets.webwxuvid = webwxuvid;
    }

    public static String getContactCookie() {
        contactCookie="wxuin="+getWxuin()+";wxsid="+getSid();
        return contactCookie;
    }
}
