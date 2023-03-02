package is.hi.hbv501g.hugbunadarverkefni1.Persistence.Entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * The User class contains data related to a specific user of the application (i.e. a user account).
 * The @Entity annotation marks it as an object that can be put in persistent storage via the Spring Data JPA to be accessed at a later date.
 * This class has a One-to-Many relationship with the Comment class.
 */
@Entity
@Table(name = "users")
public class User {

    private long ID;

    private String username;
    
    private String userPassword;
    private boolean isAdmin;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @JsonManagedReference
    private boolean loggedIn;
    @JsonManagedReference
    private List<Comment> comments;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    @Id
    @Column(name = "UserID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getID() {
        return ID;
    }
    public void setID(long id) {
        this.ID = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public User(String username, String userPassword, boolean isAdmin, List<Comment> comments) {
        this.username = username;
        this.userPassword = userPassword;
        this.comments = comments;
        this.isAdmin = isAdmin;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
