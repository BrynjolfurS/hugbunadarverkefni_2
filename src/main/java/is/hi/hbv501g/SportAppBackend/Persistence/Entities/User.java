package is.hi.hbv501g.SportAppBackend.Persistence.Entities;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
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

    private List<Message> messages = new ArrayList<>();

    private boolean isAdmin;
    public boolean isAdmin() {
        return isAdmin;
    }

    private boolean isBanned;


    // User profile
    private String userFullName;
    private String userEmailAddress;


    // Messages

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    public List<Message> getMessages() {
//        return messages;
//    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }


    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /*
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }*/


    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @JsonManagedReference
    private boolean loggedIn;

    /*
    @JsonManagedReference
    private List<Comment> comments;*/

    public User() {
    }

    public User(String username, String userPassword, boolean isAdmin) {
        this.username = username;
        this.userPassword = userPassword;
        this.isAdmin = isAdmin;
    }

    // Getters & Setters

    @Id
    @Column(name = "UserID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getID() {
        return ID;
    }
    public void setID(long id) {
        this.ID = id;
    }

    public String getUsername() {
        return username;
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

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }
}
