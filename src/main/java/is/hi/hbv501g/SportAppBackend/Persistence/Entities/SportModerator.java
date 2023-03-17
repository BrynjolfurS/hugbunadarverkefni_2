package is.hi.hbv501g.SportAppBackend.Persistence.Entities;


import javax.persistence.*;

@Entity
@Table(name = "moderators")
public class SportModerator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String sport;
    private String username;

    public SportModerator() {
    }

    public SportModerator(String sport, String username) {
        this.sport = sport;
        this.username = username;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
