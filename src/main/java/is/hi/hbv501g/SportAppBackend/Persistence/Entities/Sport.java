package is.hi.hbv501g.SportAppBackend.Persistence.Entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sport")
public class Sport {


    private long ID;
    private String sport;
    private String slug;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public SportModerator getModerates() {
        return moderates;
    }

    public void setModerates(SportModerator moderates) {
        this.moderates = moderates;
    }

    private SportModerator moderates;

    public Sport() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Sport(String sport, String slug, String username) {
        this.sport = sport;
        this.slug = slug;
    }
}
