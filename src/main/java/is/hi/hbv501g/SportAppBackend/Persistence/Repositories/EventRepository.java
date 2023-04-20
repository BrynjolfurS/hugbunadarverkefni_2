package is.hi.hbv501g.SportAppBackend.Persistence.Repositories;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Event;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    public Event save(Event event);
    public void delete(Event event);
    public Event findByID(Long id);
    public List<Event> findAll();
    public List<Event> findBySport(String sport);
}