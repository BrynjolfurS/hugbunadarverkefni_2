package is.hi.hbv501g.hugbunadarverkefni1.Controllers;

import is.hi.hbv501g.hugbunadarverkefni1.Persistence.Entities.Club;
import is.hi.hbv501g.hugbunadarverkefni1.Persistence.Entities.Event;
import is.hi.hbv501g.hugbunadarverkefni1.Persistence.Entities.Player;
import is.hi.hbv501g.hugbunadarverkefni1.Services.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SportController {

    private final SportService sportService;


    @Autowired
    public SportController(SportService sportService){
        this.sportService = sportService;

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



        //-----------------------EVENT---START-------------------------------

    @RequestMapping(value = "/home/{sport}/events/edit/{id}", method = RequestMethod.POST)
    public String editEvent(@PathVariable("id") Long id,Event event,Model model) {
        //takes in object and saves changes
        event.setID(id);
        sportService.saveEvent(event);
        return "redirect:/home/{sport}/events/edit";
    }

    @RequestMapping(value = "/home/{sport}/events/save", method = RequestMethod.POST)
    public String saveEvent(Event event, Model model) {
        //takes in object and saves changes
        System.err.println("Today's date: " + event.getEventDate());
        sportService.saveEvent(event);
        return "redirect:/home/{sport}/events";
    }

    @RequestMapping(value = "/home/{sport}/events/delete/{id}", method = RequestMethod.GET)
    public String deleteEvent(@PathVariable("id") Long id, Model model) {
        //takes in object and saves changes
        sportService.deletEventById(id);
        return "redirect:/home/{sport}/events/edit";
    }

    //-----------------------EVENT---END-------------------------------


    //-----------------------PLAYER---START-------------------------------
    @RequestMapping(value = "/home/{sport}/players/edit/{id}", method = RequestMethod.POST)
    public String editPlayer(@PathVariable("id") Long id, Player player, Model model) {
        //takes in object and saves changes
        player.setID(id);
        sportService.savePlayer(player);
        return "redirect:/home/{sport}/players/edit";
    }

    @RequestMapping(value = "/home/{sport}/players/save", method = RequestMethod.POST)
    public String savePlayer(Player player, Model model) {
        //takes in object and saves changes
        sportService.savePlayer(player);
        return "redirect:/home/{sport}/players";
    }

    @RequestMapping(value = "/home/{sport}/players/delete/{id}", method = RequestMethod.GET)
    public String deletePlayer(@PathVariable("id") Long id, Model model) {
        //takes in object and saves changes
        sportService.deletPlayerById(id);
        return "redirect:/home/{sport}/players/edit";
    }
    //-----------------------PLAYER---END-------------------------------




}
