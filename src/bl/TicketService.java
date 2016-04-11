package bl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVWriter;

import models.Ticket;
import dl.TicketDAO;

public class TicketService {
    private TicketDAO ticketDAO = new TicketDAO();

    public boolean addTicket(Ticket newTicket) {
        ArrayList<Ticket> tickets = ticketDAO.getTicketsFromDB();
        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            if (ticket.getRow() == newTicket.getRow() && ticket.getNumber() == newTicket.getNumber()) {
                return false;
            }
        }
        return ticketDAO.addTicketInDB(newTicket);
    }

    public ArrayList<Ticket> getTickets() {
        return ticketDAO.getTicketsFromDB();

    }

    public void exportCSV() throws IOException {
        ArrayList<Ticket> tickets = getTickets();
        String csv = "export.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv), ';');
        // Create record
        String[] record = new String[] { "Nume Spectacol", "Numar bilet", "Rand" };
        // Write the record to file
        writer.writeNext(record);
        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            // Create record
            record = new String[] { String.valueOf(ticket.getNameShow()), String.valueOf(ticket.getNumber()),
                    String.valueOf(ticket.getRow()) };
            // Write the record to file
            writer.writeNext(record);
        }
        // close the writer
        writer.close();
    }
}
