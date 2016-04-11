package dl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Ticket;

public class TicketDAO {

    public ArrayList<Ticket> getTicketsFromDB() {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();

        try {
            Connection connection = DataBaseConnection.getDBConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ticket_event");
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setNameShow(resultSet.getString("nume_show"));
                ticket.setNumber(resultSet.getInt("numar_rezervat"));
                ticket.setRow(resultSet.getInt("rand_rezervat"));
                tickets.add(ticket);
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    public boolean addTicketInDB(Ticket ticket) {
        try {
            Connection connection = DataBaseConnection.getDBConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO ticket_event (nume_show, numar_rezervat, rand_rezervat) VALUES ('"
                    + ticket.getNameShow() + "', " + ticket.getNumber() + ", " + ticket.getRow() + ");");
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
