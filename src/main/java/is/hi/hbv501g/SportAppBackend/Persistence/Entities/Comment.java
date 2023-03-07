package is.hi.hbv501g.SportAppBackend.Persistence.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Comment class contains data related to a comment posted in a specific thread by a specific user.
 * The @Entity annotation marks it as an object that can be put in persistent storage via the Spring Data JPA to be accessed at a later date.
 * This class has a Many-To-One relationship with both the Thread class and the User class.
 */
@Entity
@Table(name = "comments")
public class Comment {
    private long ID;

    private String userName;
//    private LocalDate dateCommented;

    private String timeCommented;

    /**
     * The columnDefinition property is changed to allow for longer comments.
     */
    @Column(columnDefinition="LONGVARCHAR")
    private String comment;

    @JsonBackReference
    private Thread thread; // Thread ID Betra? Dno

    public Comment() {
    }

    public Comment(String user, String comment, Thread thread) {
        this.userName = user;
//        this.dateCommented = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ISO_DATE_TIME;
        this.timeCommented = now.format(f);
        this.comment = comment;
        this.thread = thread;
    }

    @Id
    @Column(name = "CommentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getID() {
        return ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // TODO: Skoða betur bidirectional relation ef comment á að vísa í user object
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
//    @JoinColumn(name = "UserID")
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "ThreadID")
    public Thread getThread() {return thread;}

    public void setThread(Thread thread) {this.thread = thread;}

//    public LocalDate getDateCommented() {
//        return dateCommented;
//    }
//
//    public void setDateCommented(LocalDate dateCommented) {
//        this.dateCommented = dateCommented;
//    }

    public String getTimeCommented() {
        return timeCommented;
    }

    public void setTimeCommented(String timeCommented) {
        this.timeCommented = timeCommented;
    }
    

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
