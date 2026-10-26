package com.hms.ui;
import com.hms.config.DatabaseConfig;
import com.hms.dao.UserDao;
import com.hms.service.AuthService;
import javax.swing.*;
public final class Application {
    private Application(){}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { var c=new DatabaseConfig().openConnection(); new LoginFrame(new AuthService(new UserDao(c))).setVisible(true); }
            catch(Exception e){JOptionPane.showMessageDialog(null,e.getMessage(),"Startup error",JOptionPane.ERROR_MESSAGE);}
        });
    }
}
