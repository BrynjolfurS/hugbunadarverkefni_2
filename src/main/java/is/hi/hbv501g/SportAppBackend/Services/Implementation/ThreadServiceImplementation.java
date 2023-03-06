package is.hi.hbv501g.SportAppBackend.Services.Implementation;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Comment;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Thread;
import is.hi.hbv501g.SportAppBackend.Persistence.Repositories.ThreadRepository;
import is.hi.hbv501g.SportAppBackend.Services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadServiceImplementation implements ThreadService {

    private ThreadRepository threadRepository;

    @Autowired
    public ThreadServiceImplementation(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    @Override
    public void addComment(Comment comment, Thread thread) {
        threadRepository.save(comment);
        threadRepository.save(thread);
    }

    @Override
    public Thread save(Thread thread) {
        return threadRepository.save(thread);
    }

    @Override
    public List<Thread> findAllThreadsBySport(String sport) {
        return threadRepository.findBySport(sport);
    }

    @Override
    public List<Thread> sortThreads() {
        return null;
    }

    @Override
    public void delete(Thread thread) {

    }

    @Override
    public void deleteAll() {
        threadRepository.deleteAll();
    }

    @Override
    public void deleteComment(Comment comment) {

    }

    @Override
    public Thread findThreadById(Long id) {
        return  threadRepository.findByID(id);
    }

    @Override
    public List<Thread> findAllThreads() {
        return threadRepository.findAll();
    }
}
