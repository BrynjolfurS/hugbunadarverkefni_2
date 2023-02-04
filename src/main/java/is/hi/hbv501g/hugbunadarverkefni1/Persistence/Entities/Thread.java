package is.hi.hbv501g.hugbunadarverkefni1.Persistence.Entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The Thread class contains data related to a forum thread posted by a specific user.
 * The @Entity annotation marks it as an object that can be put in persistent storage via the Spring Data JPA to be accessed at a later date.
 * This class a One-To-Many relationship with the Comment class.
 */
@Entity
@Table(name = "threads")
public class Thread implements Comparable<Thread> {


    private long ID;
    private String username;
    private boolean isPinned = false;

    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();
    private String header;
    private String body;
    private LocalDate date;

    private String sport;

    public Thread() {
    }



    public Thread(String user, String header, String body, String sport) {
        this.username = user;
        this.header = header;
        this.body = body;
        this.sport=sport;
        this.date = LocalDate.now();
    }
    @Id
    @Column(name = "threadId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getID() {
        return ID;
    }
    public void setID(long id) {
        this.ID = id;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Comment> getComments() {
        if (comments != null) return comments;
        return new ArrayList<>();
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int NumberOfComments() {
        return getComments().size();
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSport(){return sport;}
    public void setSport(String sport){this.sport=sport;}

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int compareTo(Thread that) {
        if (this.isPinned && !that.isPinned) return 1;
        if (this.isPinned && that.isPinned) return 0;
        if (!this.isPinned && !that.isPinned) {
            return this.date.compareTo(that.date);
        }
        return -1;
    }
}
