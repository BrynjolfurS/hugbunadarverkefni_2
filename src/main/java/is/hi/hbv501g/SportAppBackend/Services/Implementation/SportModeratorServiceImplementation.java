package is.hi.hbv501g.SportAppBackend.Services.Implementation;


import is.hi.hbv501g.SportAppBackend.Persistence.Entities.SportModerator;
import is.hi.hbv501g.SportAppBackend.Persistence.Repositories.SportModeratorRepository;
import is.hi.hbv501g.SportAppBackend.Persistence.Repositories.ThreadRepository;
import is.hi.hbv501g.SportAppBackend.Services.SportModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportModeratorServiceImplementation implements SportModeratorService {

    private SportModeratorRepository sportModeratorRepository;

    @Autowired
    public SportModeratorServiceImplementation(SportModeratorRepository sportModeratorRepository) {
        this.sportModeratorRepository = sportModeratorRepository;
    }

    @Override
    public SportModerator save(SportModerator sportModerator) {
        return sportModeratorRepository.save(sportModerator);
    }

    @Override
    public SportModerator findByUsername(String username) {
        return null;
    }

    @Override
    public SportModerator findByID(long id) {
        return null;
    }

    @Override
    public SportModerator findSportModeratorByUsernameAndSportName(String username, String sport) {
        return sportModeratorRepository.findSportModeratorByUsernameAndSportName(username, sport);
    }
}
