package wechat.me.service;

import org.apache.log4j.Logger;
import wechat.me.thread.syncCheckThread;

import java.util.Map;

/**
 * Created by JulyLe on 2016/8/23.
 * 主函数，主要进行相关整体的测试
 */
public class MainAction {
    private static Logger logger = Logger.getLogger(MainAction.class);
    private static String myNickName="袁翔";
    public static void main(String args[]){
        WxInit.generateUUID();
        WxInit.generateQRcode();
        WxInit.waitForLogin("1");
        WxInit.getInitParam();
        WxInit.getWebwxInit();
        System.out.println("webwx_data_ticket="+WxTickets.getWebwx_data_ticket());
        while(WxTickets.getRetcode().contains("0")){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           /* new Thread(new syncCheckThread()).start();*/
            WxAction.syncCheck();
            System.out.println(WxTickets.getSelector());
            if(WxTickets.getSelector().contains("2")) {
                logger.info("您收到了新的消息......");
                String message = WxAction.Webwxsync();
              /*  System.out.println(message);*/
            }else{
                logger.info("您的微信静悄悄的......");
            }

        }
/*        for(int i=0;i<1;i++) {
            WxAction.syncCheck();
            WxAction.Webwxsync();
        }*/

/*      Contact.initContactList(WxAction.getContact(),myNickName);
        Map<String,Contact> contactMap = Contact.getContactsList();
        System.out.println(WxTickets.getRedirectUrl());
        int n=0;
        System.out.println("您一共有"+Contact.getMemberCount()+"位联系人......");
        for(String key: contactMap.keySet()){
            System.out.print(key+"："+contactMap.get(key).getRemarkName()+"*************");
            if(n%5==0)
                System.out.println();
            n++;
        }*/
    //    WxAction.sendMsg("hello world "+n,"filehelper");

        }


    }


