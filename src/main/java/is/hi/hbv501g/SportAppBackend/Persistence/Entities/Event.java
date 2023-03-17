package is.hi.hbv501g.SportAppBackend.Persistence.Entities;


import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The Event class contains data about an event related to a specific sport or activity.
 * The @Entity annotation marks it as an object that can be put in persistent storage via the Spring Data JPA to be accessed at a later date.
 */
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long ID;
    private String eventName;
    private String eventDescription;

    private String eventStartTime;
    private LocalDateTime eventDate;
    private String sport;
//    private List<User> subscribers = new List<User>();

    //------------------test-------------------------------
    public Event(String eventName, String eventDescription ,String sport, LocalDateTime date) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.sport = sport;
        this.eventDate = date;
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm");
        this.eventStartTime = date.format(f);
    }
    //------------------test end-------------------------------

    public Event() {
    }

    public long getID() {
        return ID;
    }
    public void setID(long id) {
        this.ID = id;
    }

    public String getEventName() {

        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String geteventStartTime() {
        return eventStartTime;
    }

    public void seteventStartTime(String eventDate) {
        this.eventStartTime = eventDate;
    }

    public String getSport(){return sport;}
    public void setSport(String sport){this.sport=sport;}

//    public void addSubscriber(Long l) {this.subscribers.add(l);}
//
//    public List<Long> getSubscribers() {return subscribers;}
}
