package is.hi.hbv501g.SportAppBackend.Controllers;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.*;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Thread;
import is.hi.hbv501g.SportAppBackend.Services.SportService;
import is.hi.hbv501g.SportAppBackend.Services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * The NavController handles general navigation between the webpage's different sections.
 * Therefore it exclusively handles HTTP requests of type 'GET' and constructs a response by asking the
 * appropriate service for the data needed.
 *
 */
@RestController
public class NavController {

    private final SportService sportService;
    private final ThreadService threadService;


    // Return single thread by id from thread service and return json data
    @RequestMapping(value = "/home/{sport}/thread/{id}", method = RequestMethod.GET)
    public Thread getThreadById(@PathVariable Long id) {
        return threadService.findThreadById(id);
    }



    @Autowired
    public NavController(SportService sportService, ThreadService threadService){
        this.sportService = sportService;
        this.threadService = threadService;
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
        CreateDummyData(session);
        return "redirect:/home";
    }

    @RequestMapping(value = "/home/{sport}", method = RequestMethod.GET)
    public String goToSport(@PathVariable("sport") String sport, Model model, HttpSession session) {

        //add threads from {sport} to model
        if(!sportService.isSport(sport))
            return "redirect:/home";

        List<Thread> sportThreads = threadService.findAllThreadsBySport(sport);
        Collections.sort(sportThreads, Collections.reverseOrder());
        model.addAttribute("threads", sportThreads);
        model.addAttribute("sports", sportService.findAllSports());
        model.addAttribute("events", sportService.findAllEventsBySport(sport));

        return "sport";
    }

    @RequestMapping(value = "/home/{sport}/about", method = RequestMethod.GET)
    public String goToAboutSport(@PathVariable("sport") String sport) {
        //done
        if(!sportService.isSport(sport))
            return "redirect:/home";
        return "about"+sport;
    }

    @RequestMapping(value = "/home/{sport}/events", method = RequestMethod.GET)
    public String goToEvents(@PathVariable("sport") String sport, Model model) {
        if(!sportService.isSport(sport))
            return "redirect:/home";

        model.addAttribute("events", sportService.findAllEventsBySport(sport));
        model.addAttribute("sports", sportService.findAllSports());
        model.addAttribute("sport", sport);
        return "events";
    }

    @RequestMapping(value = "/home/{sport}/clubs", method = RequestMethod.GET)
    public String goToClubs(@PathVariable("sport") String sport, Model model) {
        if(!sportService.isSport(sport))
            return "redirect:/home";

        model.addAttribute("clubs", sportService.findAllClubsBySport(sport));
        model.addAttribute("sports", sportService.findAllSports());
        model.addAttribute("sport", sport);
        return "clubs";
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

    public void CreateDummyData(HttpSession session) {
        Thread tips1 = new Thread("Íþróttasíða", "Beginner tips & FAQ", "Here are some useful tips..", "badminton");
        Thread tips2 = new Thread("Íþróttasíða", "Beginner tips & FAQ", "Here are some useful tips..", "pilukast");
        Thread tips3 = new Thread("Íþróttasíða", "Beginner tips & FAQ", "Here are some useful tips..", "Extreme Ironing");
        tips1.setPinned(true);
        tips2.setPinned(true);
        tips3.setPinned(true);
        threadService.save(tips1);
        threadService.save(tips2);
        threadService.save(tips3);

        for (int i = 0; i < 10; i++) {
            threadService.save(new Thread("User", "Dummy Thread " + i, "Dummy Body", "badminton"));
            threadService.save(new Thread("User", "Dummy Thread " + i, "Dummy Body", "pilukast"));
            threadService.save(new Thread("User", "Dummy Thread " + i, "Dummy Body", "Extreme Ironing"));
            sportService.saveEvent(new Event("Dummy Event " + i, "Dummy Description", "badminton", LocalDate.of(2022,i+1,1+i*2)));
            sportService.saveEvent(new Event("Dummy Event " + i, "Dummy Description", "pilukast", LocalDate.of(2022,i+1,1+i*2)));
            sportService.saveEvent(new Event("Dummy Event " + i, "Dummy Description", "Extreme Ironing", LocalDate.of(2022,i+1,1+i*2)));
        }
        sportService.saveClub(new Club("Badmintonfélag Hafnarfjarðar", "https://www.badmintonfelag.is/", "bh@bhbadminton.is",
                "Strandgötu 53, 220 Hafnarfirði",
                "Badmintonfélag Hafnarfjarðar var stofnað 7.október 1959. " +
                        "Félagið hefur aðsetur í Íþróttahúsinu við Strandgötu í Hafnarfirði þar sem æfingar í badminton og borðtennis fara fram. " +
                        "Einnig er hægt að iðka tennis hjá Badmintonfélagi Hafnarfjarðar en æfingar í tennis fara fram í Tennishöllinn í Kópavogi. " +
                        "Boðið er uppá æfingar fyrir börn frá 5 ára aldri. Upplýsingar um badminton og borðtennis má finna á vefnum badmintonfelag.is " +
                        "en upplýsingar um tennis hjá Tennishöllinni í Kópavogi.", "badminton"));

    }
}
