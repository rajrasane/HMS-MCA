package com.hms.service;
import com.hms.dao.UserDao;
import com.hms.model.User;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;
public class AuthService {
    private final UserDao users;
    public AuthService(UserDao users){this.users=users;}
    public User authenticate(String username,String password) throws SQLException {
        String hash=users.passwordHash(username).orElse("");
        return !hash.isEmpty() && BCrypt.checkpw(password,hash) ? users.findByUsername(username).orElse(null) : null;
    }
}
