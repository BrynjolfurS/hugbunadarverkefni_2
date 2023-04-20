package is.hi.hbv501g.SportAppBackend.Controllers;

import com.google.firebase.messaging.FirebaseMessagingException;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.*;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Thread;
import is.hi.hbv501g.SportAppBackend.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;

/**
 * The NavController handles general navigation between the webpage's different sections.
 * Therefore, it exclusively handles HTTP requests of type 'GET' and constructs a response by asking the
 * appropriate service for the data needed.
 *
 */
@RestController
public class NavController {

    private final SportService sportService;
    private final ThreadService threadService;
    private final CommentService commentService;
    private final UserService userService;
    private final MessageService messageService;
    private final SportModeratorService sportModeratorService;

    // Return single thread by id from thread service and return json data
    @RequestMapping(value = "/home/{sport}/thread/{id}", method = RequestMethod.GET)
    public Thread getThreadById(@PathVariable Long id) {
        return threadService.findThreadById(id);
    }

    @Autowired
    public NavController(SportService sportService, ThreadService threadService, CommentService commentService, UserService userService, MessageService messageService, SportModeratorService sportModeratorService) {
        this.sportService = sportService;
        this.threadService = threadService;
        this.commentService = commentService;
        this.userService = userService;
        this.messageService = messageService;
        this.sportModeratorService = sportModeratorService;
        CreateDummyData();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToRoot() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String goToHome(HttpSession session, Model model, User user) {

        List<Thread> allThreads = threadService.findAllThreads();
        Collections.sort(allThreads, Collections.reverseOrder());
        model.addAttribute("threads", allThreads);
        List<String> sports = sportService.findAllSports();
        model.addAttribute("sports", sports);
        model.addAttribute("user", user);
        return "home";
    }

    @RequestMapping(value = "/dummydata", method = RequestMethod.GET)
    public String createDummyData(HttpSession session) {
        CreateDummyData();
        return "redirect:/home";
    }

    @RequestMapping(value = "/home/{sport}", method = RequestMethod.GET)
    public List<Thread> goToSport(@PathVariable("sport") String sport, Model model, HttpSession session) {

        //add threads from {sport} to model
        if(!sportService.isSport(sport))
            return null;

        List<Thread> sportThreads = threadService.findAllThreadsBySport(sport);

        return sportThreads;
    }

    @RequestMapping(value = "/home/{sport}/about", method = RequestMethod.GET)
    public String goToAboutSport(@PathVariable("sport") String sport) {
        //done
        if(!sportService.isSport(sport))
            return "redirect:/home";
        return "about"+sport;
    }



    @RequestMapping(value = "/home/{sport}/clubs", method = RequestMethod.GET)
    public List<Club> goToClubs(@PathVariable("sport") String sport, Model model) {
        if(!sportService.isSport(sport)) {
            return null;

        }
        return sportService.findAllClubsBySport(sport);
    }

    @RequestMapping(value = "/home/{sport}/clubs/edit", method = RequestMethod.GET)
    public String goToEditClubs(@PathVariable("sport") String sport, Model model) {
        if(!sportService.isSport(sport))
            return "redirect:/home";

        model.addAttribute("club", new Club());
        model.addAttribute("clubs", sportService.findAllClubsBySport(sport));
        model.addAttribute("sports", sportService.findAllSports());
        model.addAttribute("sport", sport);
        return "editClubs";
    }
    @RequestMapping(value = "/home/{sport}/events/edit", method = RequestMethod.GET)
    public String goToEditEvent(@PathVariable("sport") String sport, Model model) {
        if(!sportService.isSport(sport))
            return "redirect:/home";

        model.addAttribute("event", new Event());
        model.addAttribute("events", sportService.findAllEventsBySport(sport));
        model.addAttribute("sports", sportService.findAllSports());
        model.addAttribute("sport", sport);
        return "editEvents";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error404(Model model) {
        model.addAttribute("sports", sportService.findAllSports());
        return "error";
    }

    @RequestMapping(value = "/home/{sport}/createThread", method = RequestMethod.GET)
    public String newThread(@PathVariable("sport") String sport, Model model) {
        if(!sportService.isSport(sport))
            return "redirect:/home";

        List<String> sports = sportService.findAllSports();
        List<Event> events = sportService.findAllEventsBySport(sport);
        model.addAttribute("events", events);
        model.addAttribute("sports", sports);
        return "createThread";
    }

    /*
    @RequestMapping(value = "/home/{sport}/thread/{id}", method = RequestMethod.GET)
    public String goToThread(@PathVariable("id") Long id, Model model, HttpSession session) {
        //add thred með {id} i model
        Thread thread = threadService.findThreadById(id);
        model.addAttribute("newComment", new Comment());
        model.addAttribute("thread", thread);
        model.addAttribute("sports", sportService.findAllSports());
        model.addAttribute("comments", thread.getComments());
        model.addAttribute("id", id);

        return ;
    }*/

    @GetMapping("/sports")
    public List<String> getAllSports() {
        return sportService.findAllSports();
    }


   @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String goToLogin() {
        //done
        return "login";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String goToSignUp(Model model) {
        model.addAttribute("user", new User());
        return "signUp";
    }

    public void CreateDummyData() {
        User user = userService.findByUsername("admin");
        if (user == null) {
            User admin = new User("admin","admin",true);
            userService.save(new User("notAdmin","notAdmin",false));
            userService.save(admin);

            SportModerator sm = new SportModerator("badminton", "notAdmin");
            sportModeratorService.save(sm);

            Thread tips1 = new Thread("admin", "Beginner tips & FAQ", "Here are some useful tips..", "badminton");
            Thread tips2 = new Thread("admin", "Beginner tips & FAQ", "Here are some useful tips..", "pilukast");
            Thread tips3 = new Thread("admin", "Beginner tips & FAQ", "Here are some useful tips..", "Extreme Ironing");
            tips1.setPinned(true);
            tips2.setPinned(true);
            tips3.setPinned(true);
            Comment comment = new Comment("admin", "Þetta er flottur þráður!", tips1);
            Comment comment2 = new Comment("Joi", "Klárlega!", tips1);
            tips1.addComment(comment);
            tips1.addComment(comment2);
//        threadService.addComment(comment,tips1);
//        threadService.addComment(comment2, tips1);
            threadService.save(tips1);
            threadService.save(tips2);
            threadService.save(tips3);
            commentService.save(comment);
            commentService.save(comment2);

            for (int i = 0; i < 10; i++) {
                threadService.save(new Thread("admin", "Dummy Thread " + i, "Dummy Body", "badminton"));
                threadService.save(new Thread("admin", "Dummy Thread " + i, "Dummy Body", "pilukast"));
                threadService.save(new Thread("admin", "Dummy Thread " + i, "Dummy Body", "Extreme Ironing"));
                sportService.saveEvent(new Event("Dummy Event " + i, "Dummy Description", "badminton", LocalDateTime.of(2022,Month.of(i+1),1+i*2,i+2,i+10)));
                sportService.saveEvent(new Event("Dummy Event " + i, "Dummy Description", "pilukast", LocalDateTime.of(2022,Month.of(i+1),1+i*2,i+1,i+20)));
                sportService.saveEvent(new Event("Dummy Event " + i, "Dummy Description", "Extreme Ironing", LocalDateTime.of(2022,Month.of(i+1),1+i*2,i+3,i+30)));
                sportService.saveEvent(new Event("Dummy Event " + i, "Dummy Description", "bogfimi", LocalDateTime.of(2022,Month.of(i+1),1+i*2,i+6,i+15)));
            }
            sportService.saveClub(new Club("Badmintonfélag Hafnarfjarðar", "https://www.badmintonfelag.is/", "bh@bhbadminton.is",
                    "Strandgötu 53, 220 Hafnarfirði",
                    "Badmintonfélag Hafnarfjarðar var stofnað 7.október 1959. " +
                            "Félagið hefur aðsetur í Íþróttahúsinu við Strandgötu í Hafnarfirði þar sem æfingar í badminton og borðtennis fara fram. " +
                            "Einnig er hægt að iðka tennis hjá Badmintonfélagi Hafnarfjarðar en æfingar í tennis fara fram í Tennishöllinn í Kópavogi. " +
                            "Boðið er uppá æfingar fyrir börn frá 5 ára aldri. Upplýsingar um badminton og borðtennis má finna á vefnum badmintonfelag.is " +
                            "en upplýsingar um tennis hjá Tennishöllinni í Kópavogi.", "badminton"));

            sportService.saveClub(new Club("Pilukastfélag Hafnarfjarðar", "https://www.pilukastfelag.is/", "bh@bhpilukast.is",
                    "Strandgötu 53, 220 Hafnarfirði", "Pilukastfélag Hafnarfjarðar var stofnað 7.október 1959.", "pilukast"));

            sportService.saveClub(new Club("Bogfimifélag Hafnarfjarðar", "https://www.bogfimifelag.is/", "bh@bhbogfimi.is",
                    "Álfheimum 53, 104 Reykjavík", "Bogfimifélag Reykjavíkur var stofnað 2.janúar 1995.", "bogfimi"));

            sportService.saveClub(new Club("Pilukastfélag Reykjavíkur", "https://www.pilukastfelagrvk.is/", "rvk@rvkpilukast.is",
                    "Lágmúla 2, 105 Reykjavík", "Pilukastfélag Reykjavíkur var stofnað 1.desember 1923.", "pilukast"));

            sportService.saveClub(new Club("Staujararnir", "https://www.straujararnir.is/", "bh@straujararnir.is",
                    "Hamragorg 17, 200 Kópavogi", "Straujararnir voru stofnaðir 15.maí 2005.", "Extreme Ironing"));
        }
    }
}
