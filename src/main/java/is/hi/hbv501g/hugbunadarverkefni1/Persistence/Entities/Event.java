package is.hi.hbv501g.hugbunadarverkefni1.Persistence.Entities;


import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;
    private String sport;

    //------------------test-------------------------------
    public Event(String eventName, String eventDescription ,String sport, LocalDate date) {
        this.eventName = eventName;
        this.eventDescription = eventDescription+" og "+eventDescription;
        this.sport = sport;
        this.eventDate = date;
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

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getSport(){return sport;}
    public void setSport(String sport){this.sport=sport;}


}
