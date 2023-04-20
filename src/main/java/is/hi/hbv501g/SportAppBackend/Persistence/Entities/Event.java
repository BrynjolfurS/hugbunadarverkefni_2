package is.hi.hbv501g.SportAppBackend.Persistence.Entities;


import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private boolean inLessThan24Hours;

    public boolean isInLessThan24Hours() {
        return inLessThan24Hours;
    }

    public void setInLessThan24Hours(boolean inLessThan24Hours) {
        this.inLessThan24Hours = inLessThan24Hours;
    }

    private String eventStartTime;
    private LocalDateTime eventDate;
    private String sport;

    public List<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<User> subscribers) {
        this.subscribers = subscribers;
    }

    public void addSubscriber(User user) {
        if (subscribers == null) {
            subscribers = new ArrayList<>();
        }
        subscribers.add(user);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> subscribers = new ArrayList<>();

    //------------------test-------------------------------
    public Event(String eventName, String eventDescription ,String sport, LocalDateTime date) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.sport = sport;
        this.eventDate = date;
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm");
        this.eventStartTime = date.format(f);
    }

    public Event(String eventName, String eventDescription ,String sport, LocalDateTime date, String image) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.sport = sport;
        this.eventDate = date;
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm");
        this.eventStartTime = date.format(f);
        this.image = image;
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

    public void setEventStartTime(String eventDate) {
        this.eventStartTime = eventDate;
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm");
        this.eventDate = LocalDateTime.parse(eventDate, f);
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getSport(){return sport;}
    public void setSport(String sport){this.sport=sport;}

//    public void addSubscriber(Long l) {this.subscribers.add(l);}
//
//    public List<Long> getSubscribers() {return subscribers;}
}
