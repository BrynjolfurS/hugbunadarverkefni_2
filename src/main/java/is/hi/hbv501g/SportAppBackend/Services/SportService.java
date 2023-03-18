package is.hi.hbv501g.SportAppBackend.Services;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Club;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Event;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.SportModerator;

import java.util.ArrayList;
import java.util.List;

public interface SportService {
    public List<String> findAllSports();
    List<Event> findAllEventsBySport(String sport);
    Club saveClub(Club club);
    public  Boolean isSport(String sport);
    void  deletClubById(long id);
    Event saveEvent(Event event);
    void  deletEventById(long id);
    List<Club> findAllClubsBySport(String sport);
}
