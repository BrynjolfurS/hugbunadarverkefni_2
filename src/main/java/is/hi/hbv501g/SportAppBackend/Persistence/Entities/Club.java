package is.hi.hbv501g.SportAppBackend.Persistence.Entities;

import javax.persistence.*;

/**
 * The Club class contains data about a specific club or organization related to some sport or activities.
 * The @Entity annotation marks it as an object that can be put in persistent storage via the Spring Data JPA to be accessed at a later date.
 */
@Entity
@Table(name = "clubs")
public class Club {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long ID;
    private String clubName;
    private String clubUrl;
    private String clubEmail;
    private String clubLocation;
    /**
     * The @Lob annotation indicates to JPA that the property may contain a large amount of data.
     */
    @Lob
    private String description;
    private String sport;

    public Club() {

    }
    public Club(String clubName, String clubUrl, String clubEmail,
                String clubLocation, String description,String sport) {
        this.clubName = clubName;
        this.clubUrl = clubUrl;
        this.clubEmail = clubEmail;
        this.clubLocation = clubLocation;
        this.description = description;
        this.sport = sport;
    }

    public long getID() {
        return ID;
    }
    public void setID(long id) {
        this.ID = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubUrl() {
        return clubUrl;
    }

    public void setClubUrl(String clubUrl) {
        this.clubUrl = clubUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSport(){return sport;}
    public void setSport(String sport){this.sport=sport;}

    public String getClubLocation() {
        return clubLocation;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }

    public String getClubEmail() {
        return clubEmail;
    }

    public void setClubEmail(String clubEmail) {
        this.clubEmail = clubEmail;
    }
}
