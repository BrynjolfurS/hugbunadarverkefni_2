package is.hi.hbv501g.SportAppBackend.Persistence.Repositories;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Message;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message save(Message message);
    Message findById(long id);
    void delete(Message message);
    List<Message> findAll();
    List<Message> findByUsername(String username);
}
