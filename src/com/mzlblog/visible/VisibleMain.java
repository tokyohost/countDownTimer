package com.mzlblog.visible;


import com.mzlblog.features.UIUtil;
import com.sun.javafx.collections.MappingChange;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class VisibleMain {
    JLabel title = new JLabel();
    JLabel Time_lab = new JLabel();
    public JWindow jWindow;
    static boolean isShowWindow = true;

    public static final String title_s = "CountDownTimer";
    private static FindGame findGame;




    public VisibleMain() {
        System.setProperty("jna.encoding", "GBK");
        findGame = new FindGame();

        title.setText("距离--年江苏专转本还剩下");
        Font font = new Font("黑体", Font.BOLD, 25);
        title.setFont(font);

        title.setForeground(new Color(150, 84, 84));
        this.jWindow = new JWindow();
        jWindow.add(title);
        jWindow.setName(title_s);


    }

    public void initView() {
        jWindow.pack();
        jWindow.setLocationRelativeTo(null);
        jWindow.setBackground(new Color(0, 0, 0, 0));

        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        jWindow.setBounds(screenWidth - 380, 0, 450, 65);
        inVisibleSystemTray();
        initTime();
        initMouseLinster();

        jWindow.setVisible(true);
        jWindow.setAlwaysOnTop(true);


    }

    void initMouseLinster() {
        title.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //鼠标移入
                title.setVisible(false);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                //鼠标移出
                title.setVisible(true);
            }
        });

    }

    //初始化倒计时时间
    void initTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DateFormat yearFormat = new SimpleDateFormat("yyyy");
                DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date bt=new Date(System.currentTimeMillis());
                while (true) {

                    int year = (Integer.valueOf(yearFormat.format(new Date(System.currentTimeMillis()))));
                    try {

                        Date et=sdf.parse(year+"-03-10 00:00:00");
                        Date endData ;
                        if (bt.before(et)){
                            //表示bt小于et
                            endData = dateFormat2.parse((year)+"-03-10 00:00:00");

                        }else{
                            endData = dateFormat2.parse((year+1)+"-03-10 00:00:00");
                            year +=1;
                        }



                        title.setText("<html><p align='center'>距离"+year+"年江苏专转本还剩下<br>" + TimerCount.getDatePoor(endData, bt) + "</p></html>");
//                        jWindow.setAlwaysOnTop(false);
                        jWindow.setAlwaysOnTop(true);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        jWindow.add(Time_lab);

    }

    public void inVisibleSystemTray() {
//        Native lib =  Native.load()
        ImageIcon img = new ImageIcon(VisibleMain.class.getClassLoader().getResource("res/icon.png"));
        img = UIUtil.changeIcon(img,1);
        //得到当前系统托盘
        SystemTray systemtray = SystemTray.getSystemTray();

        //定义弹出菜单
        PopupMenu pm = new PopupMenu();

        //定义弹出菜单项
        MenuItem hidemenu = new MenuItem("Hide CountDownTimer");
        MenuItem showmenu = new MenuItem("Show CountDownTimer");
        MenuItem exitmenu = new MenuItem("Quite CountDownTimer");

        //添加弹出菜单项到弹出菜单
        pm.add(hidemenu);
        pm.add(showmenu);
        pm.add(exitmenu);

        //创建带指定图像、工具提示和弹出菜单的 TrayIcon
        TrayIcon trayicon = new TrayIcon(img.getImage(), "CountDownTimer", pm);

        //将TrayIcon添加到系统托盘
        try {
            systemtray.add(trayicon);
        } catch (AWTException e1) {
            e1.printStackTrace();
        }


        //定义ActionListener监听器
        ActionListener MenuListen = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getActionCommand().equals("Hide CountDownTimer")) {
                    if (!jWindow.isVisible()) {
                        return;
                    }

                    jWindow.setVisible(false);
                    isShowWindow = false;
                    try {
                        //1小时后自动重新打开
                        CountDown countDown = new CountDown(60 * 60);
//                        Dialog.showDialog("计时器已隐藏！但是1小时后将重新显示","不要玩游戏！你要悄悄努力，然后惊艳所有人！");
                        Dialog.showCustomDialog("<html><body><p align=\\\"center\\\">计时器已隐藏！但是1小时后将重新显示<br/>\n期间将自动检测Steam以及腾讯游戏是否运行</p></body></html>", "不要玩游戏！你要悄悄努力，然后惊艳所有人！");

                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else if (e.getActionCommand().equals("Quite CountDownTimer")) {
                    Dialog.showCustomDialog("<html><p align='center'>已退出程序.<br>愿您好自为之,前程似锦!</p></html>", "Bye");
                    System.exit(520);
                } else if (e.getActionCommand().equals("Show CountDownTimer")) {
                    title.setVisible(true);
                    jWindow.setVisible(true);
                    isShowWindow = true;
                }


            }

        };
        hidemenu.addActionListener(MenuListen);
        exitmenu.addActionListener(MenuListen);
        showmenu.addActionListener(MenuListen);


    }

    class CountDown {

        private int limitSec;


        public CountDown(final int limitSec) throws Exception {
            this.limitSec = limitSec;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Count from " + limitSec);
                    int limitS = limitSec;
                    while (limitSec > 0) {
                        //判断是否已经展示
                        //如果已经展示，则取消后台计时
                        if (jWindow.isVisible()) {
                            break;
                        }


                        System.out.println("remians " + --limitS + " s");
                        try {
                            TimeUnit.SECONDS.sleep(1); //设置倒计时间隔
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!isShowWindow) {
                            //扫描是否正在打游戏
                            //游戏列表
                            findGame.startSearch("Steam", "TBSWebRenderer", "腾讯手游助手", "WeGame");

                        }

                    }
                    jWindow.setVisible(true);
                }
            }).start();


        }


    }

    public interface User32 extends StdCallLibrary {
        User32 INSTANCE = (User32) Native.load("User32", User32.class);//加载系统User32 DLL文件，也可以是C++写的DLL文件

        int SendMessageA(int hwnd, int msg, int wparam, int lparam);

        void BlockInput(boolean isBlock);

        int MessageBoxA(WinDef.HWND hWnd, String lpText, int lpCaption, int uType);

    }


    class FindGame {
        DateFormat dateFormat;
        public FindGame() {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        public void startSearch(String... gameList) {

            for (String item : gameList) {
                try {
                    WinDef.HWND hwnd = com.sun.jna.platform.win32.User32.INSTANCE.FindWindow(null, item);
                    if (hwnd != null) {

                        if (isShowWindow == false) {
//                            User32.INSTANCE.MessageBoxA(hwnd,"玩尼玛的"+item+"呢，还上不上学了？"+title.getText(), 0, 0);//调用消息对话框DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                            Date endData = dateFormat.parse("2021-03-10 00:00:00");

                            Dialog.showDialog("<html><body><p align='center'R>玩尼玛的游戏呢，还上不上学了？<br/>" + "距离2021年江苏专转本还剩<br>" + TimerCount.getDatePoorNotFormate(endData, new Date(System.currentTimeMillis())) + "</p></body></html> \n", "玩玩玩！你TM都没书读了！！");
//                            jWindow.setVisible(true);
                            isShowWindow = true;

                        }

                    } else {
                        System.out.println("未发现在玩" + item);
                    }
                } catch (UnsatisfiedLinkError | ParseException e) {
                    System.out.println("扫描出错" + item);
                    System.out.println(e.getMessage());
                }

            }

        }


    }
}
