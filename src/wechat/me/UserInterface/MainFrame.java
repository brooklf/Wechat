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
        GridBagLayout layout = new GridBagLayout();
        contentPane.setLayout(layout);
        f.setTitle("微信2.0");
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\weixingicon.jpg");
        f.setIconImage(icon);
        f.setSize(700,500);
        f.setLocationRelativeTo(null);

        JLabel jLabel1=new JLabel("用户名");
        JButton jbutton = new JButton("空白");
        JButton jbutton1 = new JButton("空白1");
        JTextArea jTextArea = new JTextArea();  //存放联系人
        jTextArea.setText("等待微信初始化......");
        JTextArea jTextArea1 = new JTextArea("消息记录");
        jTextArea1.setBackground(Color.pink);

        contentPane.add(jLabel1);
        contentPane.add(jbutton);
        contentPane.add(jbutton1);
    /*    contentPane.add(jTextArea);
        contentPane.add(jTextArea1);*/

        GridBagConstraints ctr1 = new GridBagConstraints();
        ctr1.fill = GridBagConstraints.BOTH;

        ctr1.gridwidth=1;  //该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        ctr1.weightx=0;     //该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        ctr1.weighty=0;     //该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(jLabel1,ctr1);

        ctr1.gridwidth=5;
        ctr1.weightx=0;
        ctr1.weighty=0;
        layout.setConstraints(jbutton,ctr1);

        ctr1.gridwidth=2;
        ctr1.weightx=0;
        ctr1.weighty=0;
        layout.setConstraints(jbutton1,ctr1);

/*        ctr1.gridwidth=1;
        ctr1.weightx=0;
        ctr1.weighty=1;
        layout.setConstraints(jTextArea,ctr1);

        ctr1.gridwidth=0;
        ctr1.weightx=0;
        ctr1.weighty=0;
        layout.setConstraints(jTextArea1,ctr1);*/



/*        JTextArea jTextArea1 = new JTextArea();  //存放消息
        jTextArea1.setCaretColor(Color.black);



        JButton jButton = new JButton("发送");*/


        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
}

