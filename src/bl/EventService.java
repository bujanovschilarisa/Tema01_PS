package bl;

import java.util.ArrayList;

import models.Event;
import dl.EventDAO;

public class EventService {
    private EventDAO eventDAO = new EventDAO();

    public ArrayList<Event> getEvents() {
        return eventDAO.getEventsFromDB();
    }

    public boolean deleteEvent(Event event) {
        return eventDAO.deleteEventFromDB(event);

    }

    public boolean addEvent(Event event) {
        return eventDAO.addEventInDB(event);
    }

    public boolean updateEvent(Event event) {
        return eventDAO.updateEventInDB(event);
    }
}
