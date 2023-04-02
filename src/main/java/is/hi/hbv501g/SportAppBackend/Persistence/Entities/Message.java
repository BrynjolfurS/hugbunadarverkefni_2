package is.hi.hbv501g.SportAppBackend.Persistence.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


@Entity
@Table(name = "messages")
public class Message {
    private long Id;
    private String message;

    private String username;

    private String threadCreator;

    public String getThreadCreator() {
        return threadCreator;
    }

    public void setThreadCreator(String threadCreator) {
        this.threadCreator = threadCreator;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", nullable = false)
    @JsonBackReference
    public User getUser() {
        return user;
    }

    public Message(String message, User user, boolean x, String threadCreator) {
        this.message = message;
        this.user = user;
        this.x = x;
        this.threadCreator = threadCreator;
    }

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public Message() {
        // Required empty public constructor
    }


    // Veit að þetta er ruglandi, en af einhverri vangefinni ástæðu vildi þetta
    // ekki keyra þegar ég reyndi að kalla þetta isRead, read, beenRead etc.
    private boolean x;

    // Þetta er = messageBeenRead, read, beenRead etc.
    public boolean isX() {
        return x;
    }

    public void setX(boolean x) {
        this.x = x;
    }
}
