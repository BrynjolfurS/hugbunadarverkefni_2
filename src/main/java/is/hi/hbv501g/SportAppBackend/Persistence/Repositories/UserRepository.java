package is.hi.hbv501g.SportAppBackend.Persistence.Repositories;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.SportModerator;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    void delete(User user);
    User findByUsername(String username);
    User findByID(long id);
}
