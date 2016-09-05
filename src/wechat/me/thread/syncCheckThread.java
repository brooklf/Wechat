package wechat.me.thread;

import org.apache.log4j.Logger;
import wechat.me.UserInterface.MainFrame;
import wechat.me.robot.TuringRobot;
import wechat.me.service.Contact;
import wechat.me.service.WxAction;
import wechat.me.service.WxTickets;
import wechat.me.util.RegularExpressionHelp;

import java.util.Map;

/**
 * Created by LemonTree on 2016/8/29.
 */
public class syncCheckThread implements Runnable {
    private static Logger logger = Logger.getLogger(syncCheckThread.class);
    private static int Count = 0;

    @Override
    public void run() {
    }
}
