package is.hi.hbv501g.hugbunadarverkefni1.Persistence.Entities;


import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The Comment class contains data related to a comment posted in a specific thread by a specific user.
 * The @Entity annotation marks it as an object that can be put in persistent storage via the Spring Data JPA to be accessed at a later date.
 * This class has a Many-To-One relationship with both the Thread class and the User class.
 */
@Entity
@Table(name = "comments")
public class Comment {

    private long ID;
    private User user;
    private LocalDate dateCommented;

    private LocalTime timeCommented;

    /**
     * The columnDefinition property is changed to allow for longer comments.
     */
    @Column(columnDefinition="LONGVARCHAR")
    private String comment;

    private Thread thread; // Thread ID Betra? Dno

    public Comment() {
    }

    public Comment(User user, String comment, Thread thread) {
        this.user = user;
        this.dateCommented = LocalDate.now();
        this.timeCommented = LocalTime.now();
        this.comment = comment;
        this.thread = thread;
    }

    @Id
    @Column(name = "CommentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getID() {
        return ID;
    }
    public void setID(Long id) {
        this.ID = id;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "UserID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
        @JoinColumn(name = "ThreadID")
    public Thread getThread() {return thread;}

    public void setThread(Thread thread) {this.thread = thread;}

    public LocalDate getDateCommented() {
        return dateCommented;
    }

    public void setDateCommented(LocalDate dateCommented) {
        this.dateCommented = dateCommented;
    }

    public LocalTime getTimeCommented() {
        return timeCommented;
    }

    public void setTimeCommented(LocalTime timeCommented) {
        this.timeCommented = timeCommented;
    }
    

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
