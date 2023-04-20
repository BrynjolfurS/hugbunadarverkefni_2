package is.hi.hbv501g.SportAppBackend.Persistence.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Comment class contains data related to a comment posted in a specific thread by a specific user.
 * The @Entity annotation marks it as an object that can be put in persistent storage via the Spring Data JPA to be accessed at a later date.
 * This class has a Many-To-One relationship with both the Thread class and the User class.
 */
@Entity
@Table(name = "comments")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
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

    // fetch = FetchType.LAZY ??
    @ManyToOne(targetEntity = Thread.class, fetch = FetchType.LAZY)
    public Thread getThread() {return thread;}

    public void setThread(Thread thread) {this.thread = thread;}

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
