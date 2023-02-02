package is.hi.hbv501g.hugbunadarverkefni1.Controllers;

import is.hi.hbv501g.hugbunadarverkefni1.Persistence.Entities.Comment;
import is.hi.hbv501g.hugbunadarverkefni1.Persistence.Entities.Thread;
import is.hi.hbv501g.hugbunadarverkefni1.Persistence.Entities.User;
import is.hi.hbv501g.hugbunadarverkefni1.Services.SportService;
import is.hi.hbv501g.hugbunadarverkefni1.Services.ThreadService;
import is.hi.hbv501g.hugbunadarverkefni1.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ThreadController {
    private final ThreadService threadService;
    private final UserService userService;

    @Autowired
    public ThreadController(ThreadService threadService, SportService sportService, UserService userService){
        this.threadService = threadService;
        this.userService = userService;
    }

    @GetMapping(name = "/{id}", produces = "application/json")
    public List<Thread> getThreads(@PathVariable int id) {
        return threadService.findAllThreads();
    }

    @RequestMapping(value = "/home/{sport}/createThread", method = RequestMethod.POST)
    public String addThread(HttpSession session, @PathVariable("sport") String sport, String header, String body, String username, String pinned, Model model) {
        //tekur inn thread og sendir hann í repository svo redirectum við til thread með id
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        Thread thread;
        if (sessionUser == null) {
            thread = threadService.save(new Thread("anonymous", header, body, sport));
        }
        else {
            thread = threadService.save(new Thread(sessionUser.getUsername(), header, body, sport));
        }
        if (pinned != null) {
            thread.setPinned(true);
            thread = threadService.save(thread);
        }

        Long id = thread.getID();
        return "redirect:/home/{sport}/thread/"+id;
    }

    @RequestMapping(value = "/home/{sport}/thread/{id}/editThread", method = RequestMethod.GET)
    public String editThread(@PathVariable("id") String id, Model model) {
        model.addAttribute("header", threadService.findThreadById(Long.parseLong(id)).getHeader());
        model.addAttribute("body", threadService.findThreadById(Long.parseLong(id)).getBody());
        return "editThread";
    }

    @RequestMapping(value = "/home/{sport}/thread/{id}/editThread", method = RequestMethod.POST)
    public String postEditThread(@RequestParam String header, String body, @PathVariable Long id) {
        
        return "redirect:/home/{sport}/thread/"+id;
    }

    @RequestMapping(value = "/home/{sport}/thread/{id}", method = RequestMethod.POST)
    public String addComment(@PathVariable("id") Long id, String comment, HttpSession session, Model model) {
        User poster = (User) session.getAttribute("LoggedInUser");
        long posterID = poster.getID();
        session.removeAttribute("LoggedInUser");
        poster = userService.findByID(posterID);

        Thread targetThread = threadService.findThreadById(id);
        Comment newComment = new Comment(poster, comment, targetThread);
        threadService.addComment(newComment, targetThread);
        session.setAttribute("LoggedInUser", poster);
        return "redirect:/home/{sport}/thread/{id}/";
    }

    @RequestMapping(value = "/home/{sport}/thread/{id}/comment/stuff", method = RequestMethod.POST)
    public String deleteComment(Model model) {
        //duno
        return "redirect:/home/{sport}/thred/{id}";
    }

    @RequestMapping(value = "/home/{sport}/thread/{id}/delete", method = RequestMethod.POST)
    public String deleteThread(Model model) {
        //tekur inn thred id og deletes thred svo redirectum við á sport
        return "redirect:/home/{sport}";
    }



}
