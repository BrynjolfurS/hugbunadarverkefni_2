package is.hi.hbv501g.SportAppBackend.Controllers;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.*;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Thread;
import is.hi.hbv501g.SportAppBackend.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ThreadController {
    private final ThreadService threadService;
    private final UserService userService;
    private final CommentService commentService;
    private final MessageService messageService;

    // Má Deleta seinna: Kemur í veg fyrir að dummy gögn séu endalaust búin til
    private int dummyTeljari = 0;

    @Autowired
    public ThreadController(ThreadService threadService, SportService sportService, UserService userService, CommentService commentService, MessageService messageService) {
        this.commentService = commentService;
        this.threadService = threadService;
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/thread/{id}")
    public Thread getThreadById(@PathVariable Long id) {
        Thread thread = threadService.findThreadById(id);
        return thread;
    }

    @GetMapping("/allThreads")
    public List<Thread> getAllThreads() {
//        createDummyData();
        return threadService.findAllThreads();
    }

    @PostMapping("/saveThread")
    public Thread saveThread(@RequestParam String title, @RequestParam String body, @RequestParam String sport, @RequestParam String user) {
        if (userService.findByUsername(user) != null) {
            Thread thread = new Thread();
            thread.setHeader(title);
            thread.setBody(body);
            thread.setSport(sport);
            thread.setUser(userService.findByUsername(user).getUsername());
            thread.setDate(LocalDate.now());
            threadService.save(thread);
            return thread;
        }
        return null;
    }


    // TODO: Bæta við að taka inn thread owner til að senda honum skilaboð
    // TODO: Finna útfrá threadId hver postaði þræðinum og setja það í message
    @PostMapping("/newComment")
    public String addComment(@RequestParam String username, @RequestParam String commentBody, @RequestParam String threadId) {
//        System.out.println(username);
//        System.out.println(commentBody);
//        System.out.println(threadId);
        User poster = userService.findByUsername(username);
        Thread threadPostedIn = threadService.findThreadById(Long.valueOf(threadId));
//        User threadOwner = userService.findByUsername(threadPostedIn.getUsername());
        Comment newComment = new Comment(poster.getUsername(), commentBody, threadPostedIn);




        threadPostedIn.addComment(newComment);
//        threadService.save(threadPostedIn);
        commentService.save(newComment);

        /*
        if (threadOwner != null) {
            Message message = new Message(poster.getUsername() + " commented on your thread " + threadPostedIn.getHeader(), threadOwner, false, threadOwner.getUsername(), "new_comment");
            messageService.save(message);
        }*/

        return "Comment successfully posted!";
    }



    @PutMapping("userInfo/{username}/messages/{id}")
    public void updateMessage(@PathVariable String username, @PathVariable Long id) {
        Message message = messageService.findById(id);
        message.setX(true); // X -> Message been read
        messageService.save(message);
    }


    @GetMapping("/userInfo/{username}/messages")
    public List<Message> getMessages(@PathVariable String username) {
        User user = userService.findByUsername(username);
        List<Message> messages = messageService.findByUsername(user.getUsername());
        if (messages != null) {
            return messages;
        }
        return null;
    }

    @GetMapping("/comments/{id}")
    public List<Comment> getCommentsByThreadId(@PathVariable long id) {
        Thread thread = threadService.findThreadById(id);
        List<Comment> comments = thread.getComments();
        comments.forEach(comment -> System.out.println("Comment id: " + comment.getID()));
        return comments;
    }

    @DeleteMapping("/comments/{id}/delete")
    public String deleteComment(@PathVariable("id") String id) {
        try {
            Comment commentToDelete = commentService.findCommentById(Long.valueOf(id));
            System.out.print("Deleting comment: id - " +commentToDelete.getID() + " comment - " + commentToDelete.getComment());
            commentService.delete(commentToDelete);
        } catch (Exception e) {
            System.out.println("Error deleting comment: " + e.getMessage());
        }
        return "Comment successfully deleted!";
    }

    @RequestMapping(value = "/home/{sport}/createThread", method = RequestMethod.POST)
    public String addThread(HttpSession session, @PathVariable("sport") String sport, String header, String body, String username, String pinned, Model model) {
        //tekur inn thread og sendir hann í repository svo redirectum við til thread með id
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        Thread thread;
        if (sessionUser == null) {
//            thread = threadService.save(new Thread("anonymous", header, body, sport));
            return "redirect:/home/{sport}";
        }
        else {
            thread = new Thread(sessionUser.getUsername(), header, body, sport);
            threadService.save(thread);
        }
        if (pinned != null) {
            thread.setPinned(true);
            threadService.save(thread);
        }

        Long id = thread.getId();
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

//    @RequestMapping(value = "/home/{sport}/thread/{id}", method = RequestMethod.POST)
//    public String addComment(@PathVariable("id") Long id, String comment, HttpSession session, Model model) {
//        User poster = (User) session.getAttribute("LoggedInUser");
//        long posterID = poster.getID();
//        session.removeAttribute("LoggedInUser");
//        poster = userService.findByID(posterID);
//
//        Thread targetThread = threadService.findThreadById(id);
//        Comment newComment = new Comment(poster, comment, targetThread);
//        threadService.addComment(newComment, targetThread);
//        session.setAttribute("LoggedInUser", poster);
//        return "redirect:/home/{sport}/thread/{id}/";
//    }

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
