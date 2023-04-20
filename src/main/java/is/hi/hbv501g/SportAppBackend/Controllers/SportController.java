package is.hi.hbv501g.SportAppBackend.Controllers;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Club;
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


//-----------------------CLUBS---START-------------------------------

    @GetMapping(value = "/club/{id}")
    public Club getClubById(@PathVariable Long id) {
        Club club = sportService.getClubById(id);
        return club;
    }


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
        sportService.saveClub(club);
        return "redirect:/home/{sport}/clubs";
    }


    @RequestMapping(value = "/home/{sport}/clubs/delete/{id}", method = RequestMethod.GET)
    public String deleteClub(@PathVariable("id") Long id, Model model) {
        //takes in object and saves changes
        sportService.deleteClubById(id);
        return "redirect:/home/{sport}/clubs/edit";
    }
        //-----------------------CLUBS---END-------------------------------



}
