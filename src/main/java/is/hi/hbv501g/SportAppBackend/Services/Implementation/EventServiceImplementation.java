package is.hi.hbv501g.SportAppBackend.Services.Implementation;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Event;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.User;
import is.hi.hbv501g.SportAppBackend.Persistence.Repositories.EventRepository;
import is.hi.hbv501g.SportAppBackend.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/***********************************************************
 * Nafn: Brynjólfur Steingrímsson
 * Email: brs26@hi.is
 *
 * Lýsing:
 *
 *
 ***********************************************************/
@Service
public class EventServiceImplementation implements EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventServiceImplementation(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event findEventById(Long id) {
        return eventRepository.findByID(id);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findBySport(String sport) {
        return eventRepository.findBySport(sport);
    }

    @Override
    public void delete(Long id) {

    }

    public List<Event> findByUser(User user) {
        List<Event> events = eventRepository.findAll();
        List<Event> userSubbed = new ArrayList<>();
        for (Event e : events) {
            if (e.getSubscribers().contains(user)) {
                userSubbed.add(e);
            }
        }
        return userSubbed;
    }


}
