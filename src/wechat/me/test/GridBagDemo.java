package wechat.me.test;

/**
 * Created by LemonTree on 2016/9/1.
 */
import org.apache.log4j.Logger;
import wechat.me.service.Contact;
import wechat.me.service.MainAction;
import wechat.me.service.WxAction;
import wechat.me.service.WxInit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.*;

public class GridBagDemo extends JFrame {
    private JLabel j1;
    private JButton j2;
    private JButton j3;
    private JPanel j4;
    private JComboBox j5;
    private JTextField j6;
    private JButton j7;
    private JList j8;
    private JTextArea j9;
    private static Logger logger = Logger.getLogger(MainAction.class);
    private static String myNickName="袁翔";
    private static int Count=0;
    private static boolean autoReply = true;
    public static void main(String args[]) {
        WxInit.generateUUID();
        WxInit.generateQRcode();
        WxInit.waitForLogin("1");
        WxInit.getInitParam();
        WxInit.getWebwxInit();
        Contact.initContactList(WxAction.getContact(),myNickName);
        String []strs=new String[Contact.getMemberCount()];
        int i=0;
        for(String str:Contact.getContactsList().keySet()){
            strs[i]=str;
            i++;
        }


     //   String[] strs = {"11111","22222","33333","44444","55555"};
        GridBagDemo demo = new GridBagDemo(strs);
    }

    public GridBagDemo(String [] str) {

        init(str);
        this.setSize(600,600);
        this.setLocation(300,400);
        this.setVisible(true);
    }

    public void init(String [] str) {

        j5 = new JComboBox(str);
        j6 = new JTextField();
        j7 = new JButton("发送");
        j8 = new JList(str);
        j9 = new JTextArea();
        j9.setBackground(Color.lightGray);//为了看出效果，设置了颜色
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        this.add(j5);
        this.add(j6);
        this.add(j7);
        this.add(j8);
        this.add(j9);

        j7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String toNickname =j5.getSelectedItem().toString();
                WxAction.sendMsg(j6.getText(),Contact.getUsername(toNickname));
                j9.append(j5.getSelectedItem()+":"+j6.getText()+"\n");
                j6.setText("");
            }
        });


        //布局
        GridBagConstraints s= new GridBagConstraints();//定义一个GridBagConstraints，
        //是用来控制添加进的组件的显示位置
        s.fill = GridBagConstraints.BOTH;
        //该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //NONE：不调整组件大小。
        //HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
        //VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
        //BOTH：使组件完全填满其显示区域。
/*      s.gridwidth=2;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间*/
        s.gridwidth=2;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(j5, s);

        s.gridwidth=4;
        s.weightx = 1;
        s.weighty=0;
        layout.setConstraints(j6, s);

        s.gridwidth=0;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(j7, s);

        s.gridwidth=2;
        s.weightx = 0;
        s.weighty=1;
        layout.setConstraints(j8, s);

        s.gridwidth=5;
        s.weightx = 0;
        s.weighty=1;
        layout.setConstraints(j9, s);
    }



}