package bl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.swing.JTextField;

import models.Event;
import models.User;
import dl.UserDAO;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User isUser(String username, String password) {
        ArrayList<User> users = userDAO.getUsersFromDB();
        String passwordMd5 = getMd5Hash(password);
        User user = null;
        for (int i = 0; i < users.size(); i++) {
            user = users.get(i);
            if (user.getUsername().equals(username) && user.getPassword().equals(passwordMd5)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return userDAO.getUsersFromDB();
    }

    public boolean addUser(User user) {
        String password = user.getPassword();
        String newPassword = getMd5Hash(password);
        user.setPassword(newPassword);
        return userDAO.addEmployeeInDB(user);
    }

    public String getForgotPassword(String username) {
        String newPassword = "";
        for (int i = 0; i < 7; i++) {
            double randomNumber = Math.random();
            long primeleDouaZecimale = Math.round(randomNumber * 100);
            int caracterAles = (int) (primeleDouaZecimale % 36);
            if (caracterAles > 9) {
                newPassword = newPassword + (char) (caracterAles + 87);
            } else {
                newPassword = newPassword + caracterAles;
            }
        }
        String passwordMd5 = getMd5Hash(newPassword);
        boolean isOk = userDAO.updatePassword(username, passwordMd5);
        if (isOk == false) {
            return null;
        } else {
            return newPassword;
        }
    }

    public static String getMd5Hash(String input) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean uploadatEvent(Event uploadEvent) {
        return userDAO.updateEvent(uploadEvent);

    }

    public boolean findUsername(String username) {
        ArrayList<User> users = userDAO.getUsersFromDB();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
