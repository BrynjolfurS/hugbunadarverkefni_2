package is.hi.hbv501g.SportAppBackend.Services;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Event;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.User;

import java.util.List;

/***********************************************************
 * Nafn: Brynjólfur Steingrímsson
 * Email: brs26@hi.is
 *
 * Lýsing:
 *
 *
 ***********************************************************/
public interface EventService {
    public Event save(Event event);
    public Event findEventById(Long id);
    public List<Event> findAll();
    public List<Event> findBySport(String sport);;
    public void delete(Long id);
    public List<Event> findByUser(User user);
}
