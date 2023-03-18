package is.hi.hbv501g.SportAppBackend.Controllers;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Club;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Event;
import is.hi.hbv501g.SportAppBackend.Services.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
public class SportController {

    private final SportService sportService;


    @Autowired
    public SportController(SportService sportService){
        this.sportService = sportService;

    }

    @GetMapping(value = "/userInfo/{username}/moderates/{sport}")
    public boolean isModerator(@PathVariable String username, @PathVariable String sport){
        return false; // TODO: Implement this method
    }


//-----------------------CLUBS---START-------------------------------

    @RequestMapping(value = "/home/{sport}/clubs/edit/{id}", method = RequestMethod.POST)
    public String editClub(@PathVariable("id") Long id,Club club, Model model) {
        //takes in object and saves changes
        club.setID(id);
        sportService.saveClub(club);
        return "redirect:/home/{sport}/clubs/edit";
    }

    @RequestMapping(value = "/home/{sport}/clubs/save", method = RequestMethod.POST)
    public String saveClub(Club club, Model model) {
        //takes in object and saves changes
        System.out.println(club.getSport());
        sportService.saveClub(club);
        return "redirect:/home/{sport}/clubs";
    }

    @RequestMapping(value = "/home/{sport}/clubs/delete/{id}", method = RequestMethod.GET)
    public String deleteClub(@PathVariable("id") Long id, Model model) {
        //takes in object and saves changes
        sportService.deletClubById(id);
        return "redirect:/home/{sport}/clubs/edit";
    }
        //-----------------------CLUBS---END-------------------------------



}
