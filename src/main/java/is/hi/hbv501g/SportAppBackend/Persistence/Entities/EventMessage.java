package is.hi.hbv501g.SportAppBackend.Persistence.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class EventMessage {
    private long Id;
    private String message;

    private String msgType;

    private String username;

    private String receiver;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getUsername() {
        return sender.getUsername();
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
    public User getSender() {
        return sender;
    }

    public EventMessage(String message, User sender, boolean x, String receiver, String type) {
        this.message = message;
        this.msgType = type;
        this.sender = sender;
        this.receiver = receiver;
        this.x = x;
    }

    private User sender;

    public void setSender(User sender) {
        this.sender = sender;
    }

    public EventMessage() {
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
