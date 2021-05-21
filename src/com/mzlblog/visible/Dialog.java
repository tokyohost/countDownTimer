package com.mzlblog.visible;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class Dialog {

    public Dialog() {


    }

    public static void showDialog(String msg,String Title){
        JDialog dialog = new JDialog();
        dialog.setTitle(Title);
        JLabel label = new JLabel();
        label.setText(msg);
        label.setFont(new Font("黑体", Font.ROMAN_BASELINE, 25));
        label.setForeground(Color.red);
        label.setBackground(Color.WHITE);

        dialog.add(label);
        dialog.setIconImage(new ImageIcon(VisibleMain.class.getClassLoader().getResource("res/logo.jpg")).getImage());
        dialog.pack();
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸

        dialog.setBounds((screenSize.width/2)-(dialog.getWidth()/2),(screenSize.height/2)-(dialog.getHeight()/2),dialog.getWidth(),dialog.getHeight());
        dialog.setVisible(true);
    }

    /**
     * 显示一个自定义的对话框
     * @param msg 消息
     * @param title 标题
     */
    public static void showCustomDialog(String msg,String title) {
        // 创建一个模态对话框
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle(title);
//        //5秒后自动关闭
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                int limitS = 50;
//
//                while (limitS > 0) {
//                    System.out.println("Count from " + limitS);
//                    if (!dialog.isVisible()) {
//                        break;
//                    }
//
//
//                    System.out.println("remians " + --limitS + " s");
//
//                    try {
//                        TimeUnit.SECONDS.sleep(1); //设置倒计时间隔
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                dialog.dispose();
//
//            }
//        }).start();

        // 设置对话框的宽高
        dialog.setSize(420, 120);
        // 设置对话框大小不可改变
        dialog.setResizable(false);

        // 创建一个标签显示消息内容
        JLabel messageLabel = new JLabel(msg);
        messageLabel.setFont(new Font("等线", Font.ROMAN_BASELINE, 18));

        // 创建一个按钮用于关闭对话框
        JButton okBtn = new JButton("确定");
        okBtn.setBackground(Color.white);
        okBtn.setFont(new Font("等线", Font.ROMAN_BASELINE, 18));
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 关闭对话框
                dialog.dispose();
            }
        });

        // 创建对话框的内容面板, 在面板内可以根据自己的需要添加任何组件并做任意是布局
        JPanel panel = new JPanel();

        // 添加组件到面板
        panel.add(messageLabel);
        panel.add(okBtn);

        // 设置对话框的内容面板
        dialog.setContentPane(panel);

        // 设置对话框相对显示的位置
//        dialog.pack();
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸

        dialog.setBounds((screenSize.width/2)-(dialog.getWidth()/2),(screenSize.height/2)-(dialog.getHeight()/2),dialog.getWidth(),dialog.getHeight());

        // 显示对话框
        dialog.setVisible(true);



    }
}
