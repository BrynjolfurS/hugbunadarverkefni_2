package is.hi.hbv501g.SportAppBackend.Services;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Event;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Message;
import is.hi.hbv501g.SportAppBackend.Persistence.Repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class EventChecker {
    EventRepository eventRepository;

    @Autowired
    public EventChecker(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        checkRecords();
    }
    @Scheduled(fixedRate = 5000)
    public void checkRecords() {
        List<Event> events = eventRepository.findAll();

        for (Event event : events) {
            // if event is in less than 24 hours
            if (event.getEventDate().isAfter(LocalDateTime.now().minus(24, ChronoUnit.HOURS))) {
                event.setInLessThan24Hours(true);
                eventRepository.save(event);
            }
        }
    }
}
