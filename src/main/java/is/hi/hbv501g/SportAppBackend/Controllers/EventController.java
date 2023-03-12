package is.hi.hbv501g.SportAppBackend.Controllers;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Event;
import is.hi.hbv501g.SportAppBackend.Services.EventService;
import is.hi.hbv501g.SportAppBackend.Services.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***********************************************************
 * Nafn: Brynjólfur Steingrímsson
 * Email: brs26@hi.is
 *
 * Lýsing:
 *
 *
 ***********************************************************/
@RestController
public class EventController {

    private final EventService eventService;
    private final SportService sportService;

    @Autowired
    public EventController(EventService eventService, SportService sportService) {
        this.eventService = eventService;
        this.sportService = sportService;
    }

    @RequestMapping(value = "/home/{sport}/events", method = RequestMethod.GET)
    public List<Event> goToEvents(@PathVariable("sport") String sport, Model model) {
        if (!sportService.isSport(sport)) {
            System.out.println("Sport not found");
            return null;
        }
        return eventService.findAllEventsBySport(sport);
    }

    @RequestMapping(value = "/home/{sport}/events/edit/{id}", method = RequestMethod.POST)
    public String editEvent(@PathVariable("id") Long id, Event event, Model model) {
        //takes in object and saves changes
        event.setID(id);
        eventService.save(event);
        return "redirect:/home/{sport}/events/edit";
    }

    @RequestMapping(value = "/home/{sport}/events/save", method = RequestMethod.POST)
    public String saveEvent(Event event, Model model) {
        //takes in object and saves changes
        System.err.println("Today's date: " + event.getEventDate());
        eventService.save(event);
        return "redirect:/home/{sport}/events";
    }

    @RequestMapping(value = "/home/{sport}/events/delete/{id}", method = RequestMethod.GET)
    public String deleteEvent(@PathVariable("id") Long id, Model model) {
        //takes in object and saves changes
        eventService.delete(id);
        return "redirect:/home/{sport}/events/edit";
    }
}
