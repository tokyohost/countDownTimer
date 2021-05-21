package com.mzlblog.features;


import java.awt.event.*;

import javax.swing.*;

public class awaysOnTop extends JFrame implements WindowFocusListener {
    public awaysOnTop() {
        addWindowFocusListener(this);
        setAlwaysOnTop(true);
        this.setFocusable(true);
        // this.setFocusableWindowState(true);
        panel = new JPanel();
        //setSize(WIDTH,HEIGHT);
        setUndecorated(true);
        setLocation(X, Y);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        if (e.getNewState() != e.WINDOW_CLOSED) {
            toFront();
            requestFocus();
            setAlwaysOnTop(false);
            setAlwaysOnTop(true);
            requestFocusInWindow();
            System.out.println("focus lost");
        }

    }

    private JPanel panel;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int X = 100;
    private static final int Y = 100;

    public static void main(String args[]) {
        new awaysOnTop();
    }
}
