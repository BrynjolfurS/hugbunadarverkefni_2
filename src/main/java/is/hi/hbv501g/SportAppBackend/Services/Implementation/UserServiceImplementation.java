package is.hi.hbv501g.SportAppBackend.Services.Implementation;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.User;
import is.hi.hbv501g.SportAppBackend.Persistence.Repositories.UserRepository;
import is.hi.hbv501g.SportAppBackend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    /**
     * This method is used to create a new user account with admin priviliges for setup/testing.
     */
    @Override
    public void createAdmin() {
        User admin = new User();
        admin.setUsername("master");
        admin.setUserPassword("master");
        admin.setIsAdmin(true);
        this.userRepository.save(admin);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByID(long id) {
        return userRepository.findByID(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) { userRepository.delete(user); }

    @Override
    public User login(User user) {
        User doesExist = findByUsername(user.getUsername());
        if(doesExist != null) {
            if(doesExist.getUserPassword().equals(user.getUserPassword())){
                return doesExist;
            }
        }
        return null;
    }

}
