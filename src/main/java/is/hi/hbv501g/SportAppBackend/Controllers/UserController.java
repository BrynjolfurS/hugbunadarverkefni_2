package is.hi.hbv501g.SportAppBackend.Controllers;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.User;
import is.hi.hbv501g.SportAppBackend.Services.UserService;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * The UserController handles all requests that have to do with a user's account.
 * This includes requests reserved for users with administrative priviliges.
 *
 */
@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a user with admin priviliges for setup/development purposes.
     */
    @RequestMapping(value = "/createadmin", method = RequestMethod.GET)
    public String createAdmin() {
        userService.createAdmin();
        return "redirect:/home";
    }

    /**
     * Page for users with admin priviliges.
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminGet(Model model, HttpSession session) {
        return "admin";
    }

    /**
     * Handles request from an admin searching for a user by his/her username.
     * @param model Will contain the user's data if found.
     * @param findUser The username of the user.
     * @return Back to admin page with the updated model.
     */
    @RequestMapping(value = "/admin/finduser", method = RequestMethod.GET)
    public String adminFindUserByName(Model model, String findUser) {
        model.addAttribute("userList", userService.findByUsername(findUser));
        return "admin";
    }

    /**
     * Handles request from an admin who wants a list of all the users in the database.
     * @param model Will contain data of all users in the database.
     * @return Back to admin page with the updated model.
     */
    @RequestMapping(value = "/admin/getallusers")
    public String adminFindAllUsers(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "admin";
    }

    /**
     * Handles request from an admin who wants to grant a specific user admin priviliges.
     * @param id The UserId of the user who shall be granted admin priviliges.
     * @return Redirect back to admin page with the updated model.
     */
    @RequestMapping(value = "admin/grantadmin/{id}", method = RequestMethod.GET)
    public String grantAdminPriviliges(@PathVariable("id") long id) {
        User userToElevate = userService.findByID(id);
        userToElevate.setIsAdmin(true);
        userService.save(userToElevate);
        return "redirect:/admin";
    }

    /**
     * Handles request from an admin who wants to delete a specific user account.
     * @param id The UserId of the user who shall be deleted
     * @return Redirect back to admin page.
     */
    @RequestMapping(value = "admin/deleteUser/{id}", method = RequestMethod.GET)
    public String deleteUserAccount(@PathVariable("id") long id) {
        User userToDelete = userService.findByID(id);
        userService.delete(userToDelete);
        return "redirect:/admin";
    }

    /**
     * Handles requests for creating a new user account for the web page.
     * The method checks whether the username is already taken by another account.
     * @param user Contains the requested username and password.
     * @param result Holds result of the binding and contains possible errors.
     * @return Redirect back to the home page.
     */
    @RequestMapping(value="/signUp", method= RequestMethod.POST)
    public User signupPOST(User user, BindingResult result) {
        if(result.hasErrors()) {
            return null;
        }
        User exists = userService.findByUsername(user.getUsername());
        if(exists == null) {
            userService.save(user);
        }
        return user;
    }

    /*
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginPOST(User user, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()) {
            return "redirect:/home";
        }
        User exists = userService.login(user);
        if (exists != null) {
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            return "redirect:/";
        }
        return "redirect:/home";
    }*/

    @PostMapping("/login")
    public User loginPOST(String username, String password) {
        try {
            User user = userService.findByUsername(username);
            if (user.getUserPassword().equals(password)) {
                user.setLoggedIn(true);
                return user;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @PostMapping("/logout")
    public void logout(String username) {
        User user = userService.findByUsername(username);
        user.setLoggedIn(false);
        userService.save(user);
    }

    /**
     * Handles requests for logging out a currently logged-in user.
     * @param session User data will be removed from the session object.
     * @return Redirect back to the home page.
     */
    /*
    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public String logOut(HttpSession session) {
        session.removeAttribute("LoggedInUser");
        return "redirect:/home";
    }*/

    @GetMapping("/userInfo")
    public User getUserInfo(String username) {
        User user = userService.findByUsername(username);
        return user;
    }

    @PostMapping("/checkUsername")
    public boolean checkUsernameExists(@RequestParam String username) {
        return userService.findByUsername(username) != null;
    }

    @PostMapping("/register")
    public User register(@RequestParam String username, @RequestParam String password) {
        User user = new User(username, password, false);
        try {
            if (userService.findByUsername(username) != null) {
                return null;
            }
            userService.save(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value="/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if (sessionUser != null) {
            model.addAttribute("LoggedInUser", sessionUser);
            return "loggedInUser";
        }
        return "redirect:/";
    }
}
