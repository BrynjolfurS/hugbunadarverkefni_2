package is.hi.hbv501g.hugbunadarverkefni1.Persistence.Repositories;

import is.hi.hbv501g.hugbunadarverkefni1.Persistence.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    public Event save(Event event);
    public void delete(Event event);
    public Event findByID(Long id);
    public List<Event> findAll();
    public List<Event> findBySport(String sport);
}