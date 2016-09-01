package wechat.me.UserInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LemonTree on 2016/8/31.
 */
public class MainFrame {
    public static void main(String args[]){
        JFrame f = new JFrame();
        Container contentPane = f.getContentPane();
        contentPane.setLayout(new GridLayout(1, 2));
        f.setTitle("微信2.0");
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\weixingicon.jpg");
        f.setIconImage(icon);
        f.setSize(700,500);
        f.setLocationRelativeTo(null);

        JTextArea jTextArea = new JTextArea();  //存放联系人
        jTextArea.setText("等待微信初始化......");
        jTextArea.setBackground(Color.LIGHT_GRAY);

        contentPane.add(jTextArea);

        JTextArea jTextArea1 = new JTextArea();  //存放消息
        jTextArea1.setCaretColor(Color.black);



        JPanel jPanel = new JPanel(new GridLayout(2,1));
        JButton jButton = new JButton("发送");
        jPanel.add(jTextArea1,BorderLayout.NORTH);

        JPanel jPanel2 = new JPanel(new GridLayout(1,2));

        jPanel2.add(new JTextField("输入消息"));
        jPanel2.add(jButton);
        jPanel.add(jPanel2,BorderLayout.SOUTH);


        contentPane.add(jPanel);

        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
}

