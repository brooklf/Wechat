package wechat.me.service;

import org.apache.log4j.Logger;
import wechat.me.robot.TuringRobot;
import wechat.me.util.RegularExpressionHelp;
import java.util.Map;

/**
 * Created by JulyLe on 2016/8/23.
 * 主函数，主要进行相关整体的测试
 */
public class MainAction {
    private static Logger logger = Logger.getLogger(MainAction.class);
    private static String myNickName="你的名字";
    private static int Count=0;
    private static boolean autoReply = true;
    public static void WxStart(){
        WxInit.generateUUID();
        WxInit.generateQRcode();
        WxInit.waitForLogin("1");
        WxInit.getInitParam();
        WxInit.getWebwxInit();
        Contact.initContactList(WxAction.getContact(),myNickName);
    }

    public static void main(String args[]){
        WxStart();
        while(WxTickets.getRetcode().contains("0")){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WxAction.syncCheck();
            if(WxTickets.getSelector().contains("2")) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String OriginMessageJson = WxAction.Webwxsync();
                Map<String,String> message =RegularExpressionHelp.getMessage(OriginMessageJson);
                if(message.equals("")) {
                    Count++;
                    logger.info("你在手机上玩微信"+Count+"次，被我抓到了");
                }
                else{
                    if(Contact.getRemarkNameorNiceName(message.get("username")).equals(myNickName)){
                        Count++;
                        logger.info("你在手机上玩微信"+Count+"次，被我抓到了");
                    }
                    else {
                        logger.info("您有新的消息...:");
                        logger.info(Contact.getRemarkNameorNiceName(message.get("username")) + ":" + message.get("content"));
                        if (autoReply){
                            try {
                                int sleepTime =1000 * ((int) (Math.random() * 10));
                                System.out.println("喝口水，停"+sleepTime/1000+"秒再发送....");
                                Thread.sleep(sleepTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            String autoMessage = TuringRobot.getAnswer(message.get("content"));
                            logger.info("自动回复的信息为："+autoMessage);
                            WxAction.sendMsg(autoMessage,message.get("username"));
                            WxAction.Webwxsync();
                        }
                    }

                }
            }else if(WxTickets.getSelector().contains("0")){
                logger.info("您的微信静悄悄的......");
            }else{
                break;
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


