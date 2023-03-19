package is.hi.hbv501g.SportAppBackend.Services;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Message;

import java.util.List;

public interface MessageService {
    Message save(Message message);
    Message findById(long id);
    void delete(Message message);
    List<Message> findAll();
    List<Message> findByUsername(String username);
}
