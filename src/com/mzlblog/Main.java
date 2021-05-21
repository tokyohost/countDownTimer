package com.mzlblog;

import com.mzlblog.visible.VisibleMain;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //启动倒计时Window

        VisibleMain visibleMain = new VisibleMain();
        visibleMain.jWindow.setType(JWindow.Type.UTILITY);
        visibleMain.initView();




    }
}
