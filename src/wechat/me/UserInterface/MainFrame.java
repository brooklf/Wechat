package wechat.me.UserInterface;

import com.sun.deploy.panel.JavaPanel;
import org.apache.log4j.Logger;
import wechat.me.service.Contact;
import wechat.me.service.MainAction;
import wechat.me.service.WxAction;
import wechat.me.service.WxTickets;
import wechat.me.thread.syncCheckThread;
import wechat.me.util.RegularExpressionHelp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Created by LemonTree on 2016/8/31.
 */
public class MainFrame {
    private static JTextArea chatArea;
    private static JTextArea contactArea;
    private static JComboBox contactBox;
    private static JLabel imageHead;
    private static JButton sendButton;
    private static JTextField sendMessageField;
    private static JFrame f;
    private static int Count=0;
    private static Logger logger = Logger.getLogger(MainFrame.class);

    public static void main(String args[]) {
        MainAction.WxStart();
        String []strs=new String[Contact.getMemberCount()];
        int i=0;
        for(String str:Contact.getContactsList().keySet()){
            strs[i]=str;
            i++;
        }
        init(strs);
        logger.info("进行同步刷新......");
        while (WxTickets.getRetcode().contains("0")) {
            WxAction.syncCheck();
            if (WxTickets.getSelector().contains("2")) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String OriginMessageJson = WxAction.Webwxsync();
                Map<String, String> message = RegularExpressionHelp.getMessage(OriginMessageJson);
                if (message.equals("")) {
                    Count++;
                    logger.info("你在手机上玩微信" + Count + "次，被我抓到了");
                } else {
                    if (Contact.getRemarkNameorNiceName(message.get("username")).equals(Contact.getMyUsername())) {
                        Count++;
                        logger.info("你在手机上玩微信" + Count + "次，被我抓到了");
                    } else {
                        if (message.get("username") != null) {
                            logger.info("您有新的消息...:");
                            logger.info(Contact.getRemarkNameorNiceName(message.get("username")) + ":" + message.get("content"));
                            MainFrame.getChatArea().append(Contact.getRemarkNameorNiceName(message.get("username")) + ":" + message.get("content") + "\n");
                        }
                    }
                }
            } else
                logger.info("您的微信静悄悄的......");
        }
    }
    public static void init(String []strs){
        f = new JFrame();
        Container contentPane = f.getContentPane();
        GridLayout layout = new GridLayout(2,1);
        contentPane.setLayout(layout);
        f.setTitle("微信2.0");
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\weixingicon.jpg");
        f.setIconImage(icon);
        f.setSize(700,500);
        f.setLocationRelativeTo(null);
        chatArea = new JTextArea();  //存放连天记录
        JScrollPane scrollPane=new JScrollPane(chatArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        contactArea = new JTextArea(strs.toString());
        contactArea.setBackground(Color.pink);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(contactArea);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,5));
        contactBox =new JComboBox(strs);
        Checkbox c= new Checkbox("AutoReply");
        imageHead = new JLabel("头像");
        sendMessageField = new JTextField();
        sendButton = new JButton("发送");
        panel2.add(contactBox);
        panel2.add(c);
        panel2.add(imageHead);
        panel2.add(sendMessageField);
        panel2.add(sendButton);
        panel.add(panel2);
        contentPane.add(scrollPane);
        contentPane.add(panel);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setVisible(true);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickName = contactBox.getSelectedItem().toString();
                WxAction.sendMsg(sendMessageField.getText(),Contact.getUsername(nickName));
                chatArea.append(contactBox.getSelectedItem().toString()+":"+sendMessageField.getText()+"\n");
                sendMessageField.setText("");
                WxAction.Webwxsync();
            }
        });

    }

    public static JTextArea getChatArea() {
        return chatArea;
    }

    public static void setChatArea(JTextArea chatArea) {
        MainFrame.chatArea = chatArea;
    }

    public static JTextArea getContactArea() {
        return contactArea;
    }

    public static void setContactArea(JTextArea contactArea) {
        MainFrame.contactArea = contactArea;
    }

    public static JComboBox getContactBox() {
        return contactBox;
    }

    public static void setContactBox(JComboBox contactBox) {
        MainFrame.contactBox = contactBox;
    }

    public static JLabel getImageHead() {
        return imageHead;
    }

    public static void setImageHead(JLabel imageHead) {
        MainFrame.imageHead = imageHead;
    }

    public static JButton getSendButton() {
        return sendButton;
    }

    public static void setSendButton(JButton sendButton) {
        MainFrame.sendButton = sendButton;
    }

    public static JTextField getSendMessageField() {
        return sendMessageField;
    }

    public static void setSendMessageField(JTextField sendMessageField) {
        MainFrame.sendMessageField = sendMessageField;
    }

    public static JFrame getF() {
        return f;
    }

    public static void setF(JFrame f) {
        MainFrame.f = f;
    }
}

