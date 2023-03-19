package is.hi.hbv501g.SportAppBackend.Services.Implementation;


import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Message;
import is.hi.hbv501g.SportAppBackend.Persistence.Repositories.MessageRepository;
import is.hi.hbv501g.SportAppBackend.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImplementation(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message findById(long id) {
        return messageRepository.findById(id);
    }

    @Override
    public void delete(Message message) {
        messageRepository.delete(message);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> findByUsername(String username) {
        return messageRepository.findByUsername(username);
    }
}
