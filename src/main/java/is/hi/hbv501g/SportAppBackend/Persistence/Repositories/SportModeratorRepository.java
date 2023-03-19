package is.hi.hbv501g.SportAppBackend.Persistence.Repositories;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Event;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.SportModerator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportModeratorRepository extends JpaRepository<SportModerator, Long> {
    SportModerator save(SportModerator sportModerator);
    SportModerator findByUsername(String username);
    SportModerator findByID(long id);
    void delete(SportModerator sportModerator);
    SportModerator findSportModeratorByUsernameAndSportName(String username, String sport);
}
