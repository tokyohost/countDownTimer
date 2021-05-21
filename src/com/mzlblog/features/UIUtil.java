package com.mzlblog.features;


import javax.swing.*;
import java.awt.*;

public class UIUtil {



    public static ImageIcon changeIcon(ImageIcon image, double i){//  i 为放缩的倍数

        int width=(int) (image.getIconWidth()*i);
        int height=(int) (image.getIconHeight()*i);
        Image img=image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);//第三个值可以去查api是图片转化的方式
        ImageIcon image2=new ImageIcon(img);

        return image2;

    }
}
