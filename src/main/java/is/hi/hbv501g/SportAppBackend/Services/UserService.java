package is.hi.hbv501g.SportAppBackend.Services;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByUsername(String username);

    User findByID(long id);

    User save(User user);

    void delete(User user);

    User login(User user);

    void createAdmin();

}
