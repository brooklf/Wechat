package wechat.me.thread;

import org.apache.log4j.Logger;
import wechat.me.service.WxAction;

/**
 * Created by LemonTree on 2016/8/29.
 */
public class syncCheckThread implements Runnable{
    private static Logger logger = Logger.getLogger(syncCheckThread.class);
    @Override
    public void run() {
        logger.info("进行同步刷新......");
        WxAction.syncCheck();
    }
}
