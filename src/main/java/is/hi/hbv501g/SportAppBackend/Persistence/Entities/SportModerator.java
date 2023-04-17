package is.hi.hbv501g.SportAppBackend.Persistence.Entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "moderators")
public class SportModerator {


    private long ID;
    private String sportName;
    private String username;

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public SportModerator() {
    }

    public SportModerator(String sportName, String username) {
        this.sportName = sportName;
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
