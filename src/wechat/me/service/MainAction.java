package wechat.me.service;

import java.util.Map;

/**
 * Created by JulyLe on 2016/8/23.
 * 主函数，主要进行相关整体的测试
 */
public class MainAction {
    private static String myNickName="袁翔";
    public static void main(String args[]){
        WxInit.generateUUID();
        WxInit.generateQRcode();
        WxInit.waitForLogin("1");
        WxInit.getInitParam();
        WxInit.getWebwxInit();
        System.out.println("webwx_data_ticket="+WxTickets.getWebwx_data_ticket());
        for(int i=0;i<5;i++) {
            WxAction.syncCheck();
            WxAction.Webwxsync();
        }

   /*   Contact.initContactList(WxAction.getContact(),myNickName);
        Map<String,Contact> contactMap = Contact.getContactsList();
        System.out.println(WxTickets.getRedirectUrl());
        int n=0;
        System.out.println("您一共有"+Contact.getMemberCount()+"位联系人......");
        for(String key: contactMap.keySet()){
            System.out.print(key+"                                   ");
            if(n%5==0)
                System.out.println();
            n++;
        }*/
/*         n=1000;
        while(n!=0){

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WxAction.sendMsg("hello world "+n,"filehelper");
           */ ;
        }


    }


