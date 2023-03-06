package is.hi.hbv501g.SportAppBackend.Persistence.Repositories;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {
    public Club save(Club club);
    public void delete(Club club);
    public Club findByID(Long id);
    public List<Club> findAll();
    public List<Club> findBySport(String sport);
}