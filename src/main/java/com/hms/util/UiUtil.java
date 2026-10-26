package com.hms.util;
import javax.swing.*;
import java.awt.*;
public final class UiUtil {
    private UiUtil(){}
    public static void error(Component parent, Exception e){JOptionPane.showMessageDialog(parent,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);}
    public static JPanel form(){JPanel p=new JPanel(new GridLayout(0,2,8,8));p.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));return p;}
}
