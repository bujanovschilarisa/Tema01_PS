package dl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import models.Event;

public class EventDAO {

    public ArrayList<Event> getEventsFromDB() {
        ArrayList<Event> events = new ArrayList<Event>();
        try {
            Connection connection = DataBaseConnection.getDBConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from show_event WHERE sters = false");
            while (resultSet.next()) {
                Event event = new Event();
                event.setName(resultSet.getString("name_show"));
                event.setRegie(resultSet.getString("regie"));
                event.setDistributie(resultSet.getString("distributie"));

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String date;
                date = simpleDateFormat.format(resultSet.getDate("data_show"));
                event.setDataPremierei(date);
                event.setNumberOfTockets(resultSet.getInt("number_of_tickets"));
                events.add(event);
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public boolean addEventInDB(Event event) {

        try {
            Connection connection = DataBaseConnection.getDBConnection();
            Statement statement = connection.createStatement();
            statement
                    .executeUpdate("INSERT INTO show_event (name_show, regie, distributie, data_show, number_of_tickets, sters) VALUES ('"
                            + event.getName()
                            + "', '"
                            + event.getRegie()
                            + "', '"
                            + event.getDistributie()
                            + "', '"
                            + event.getDataPremierei() + "', " + event.getNumberOfTockets() + ", false);");
            connection.close();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateEventInDB(Event event) {
        try {
            Connection connection = DataBaseConnection.getDBConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE show_event SET regie = '" + event.getRegie() + "', distributie = '"
                    + event.getDistributie() + "', data_show = '" + event.getDataPremierei()
                    + "', number_of_tickets = " + event.getNumberOfTockets() + " WHERE name_show = '" + event.getName()
                    + "';");
            statement.getResultSet();
            connection.close();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteEventFromDB(Event event) {
        try {
            Connection connection = DataBaseConnection.getDBConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE show_event SET sters = true WHERE name_show = '" + event.getName() + "';");
            statement.getResultSet();
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
