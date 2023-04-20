package is.hi.hbv501g.SportAppBackend.Persistence.Entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Thread implements Comparable<Thread> {
    private long id;
    private String username;
    private boolean isPinned = false;
    private ArrayList<Comment> comments = new ArrayList<>();
    private String header;
    private String body;
    private LocalDate date;
    private String sport;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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

    // VIRKAR ÞEGAR ORPHAN REMOVAL ER TEKIÐ BURT!!!!??
    @OneToMany(targetEntity = Comment.class, mappedBy = "thread", cascade = CascadeType.ALL)
    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        List<Comment> newComments = new ArrayList<>();
        newComments.add(comment);
        this.comments.clear();
        this.comments.addAll(newComments);
        comment.setThread(this);
    }

    public void setComments(List<Comment> newComments) {
        this.comments.clear();
        this.comments.addAll(newComments);
        this.comments.forEach(comment -> comment.setThread(this));
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
