package wechat.me.UserInterface;

import com.sun.deploy.panel.JavaPanel;

import javax.swing.*;
import java.awt.*;

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


    public static void main(String args[]){
        String[] strs = {"11111","22222","33333","44444","55555"};
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
        chatArea.setText("等待聊天......");
        JScrollPane scrollPane=new JScrollPane(chatArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        contactArea = new JTextArea("联系人列表");
        contactArea.setBackground(Color.pink);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(contactArea);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,5));
        contactBox =new JComboBox(strs);
        Checkbox c= new Checkbox("AutoReply");
        imageHead = new JLabel("hello world");
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
    }
}

