package is.hi.hbv501g.hugbunadarverkefni1.Persistence.Entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "threads")
public class Thread {


    private long ID;
    private String username;
    private boolean isPinned = false;
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
}
