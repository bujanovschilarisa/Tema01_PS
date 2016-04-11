package dl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Event;
import models.User;

public class UserDAO {

    public ArrayList<User> getUsersFromDB() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            Connection connection = DataBaseConnection.getDBConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name_user"));
                user.setPassword(resultSet.getString("password_user"));
                user.setUsername(resultSet.getString("username"));
                user.setRol(resultSet.getString("rol"));
                users.add(user);
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean addEmployeeInDB(User user) {

        try {
            Connection connection = DataBaseConnection.getDBConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into user (name_user, password_user, username, rol) values ('"
                    + user.getName() + "', '" + user.getPassword() + "', '" + user.getUsername() + "', 'employee');");
            connection.close();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updatePassword(String username, String newPassword) {
        try {
            Connection connection = DataBaseConnection.getDBConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE user SET password_user = '" + newPassword + "' WHERE username = '"
                    + username + "';");
            connection.close();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateEvent(Event uploadEvent) {
        try {
            Connection connection = DataBaseConnection.getDBConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE show_event SET regie = '" + uploadEvent.getRegie() + "' , distributie = '"
                    + uploadEvent.getDistributie() + "' , data_show = '" + uploadEvent.getDataPremierei()
                    + "', number_of_tickets = " + uploadEvent.getNumberOfTockets() + " WHERE name_show = '"
                    + uploadEvent.getName() + "';");
            connection.close();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
