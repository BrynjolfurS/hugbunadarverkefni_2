package is.hi.hbv501g.SportAppBackend.Services;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.SportModerator;

public interface SportModeratorService {
    SportModerator save(SportModerator sportModerator);
    SportModerator findByUsername(String username);
    SportModerator findByID(long id);
    SportModerator findSportModeratorByUsernameAndSportName(String username, String sport);
}
