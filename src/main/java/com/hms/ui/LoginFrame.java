package com.hms.ui;

import com.hms.service.AuthService;
import com.hms.model.User;
import com.hms.util.UiUtil;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    public LoginFrame(AuthService auth) {
        super("HMS - Sign in");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel form=UiUtil.form(); JTextField user=new JTextField(); JPasswordField pass=new JPasswordField();
        form.add(new JLabel("Username")); form.add(user); form.add(new JLabel("Password")); form.add(pass);
        JButton login=new JButton("Sign in"); form.add(new JLabel()); form.add(login); add(form);
        login.addActionListener(e->{try{User u=auth.authenticate(user.getText().trim(),new String(pass.getPassword())); if(u==null) JOptionPane.showMessageDialog(this,"Invalid username or password"); else {dispose(); new MainFrame(u).setVisible(true);}}catch(Exception ex){UiUtil.error(this,ex);}});
        pack(); setLocationRelativeTo(null);
    }
}
