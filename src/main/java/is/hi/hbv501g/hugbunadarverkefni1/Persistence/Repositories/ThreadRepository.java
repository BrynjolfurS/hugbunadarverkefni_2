package is.hi.hbv501g.hugbunadarverkefni1.Persistence.Repositories;

import is.hi.hbv501g.hugbunadarverkefni1.Persistence.Entities.Comment;
import is.hi.hbv501g.hugbunadarverkefni1.Persistence.Entities.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
    public Thread save(Thread thread);
    public Comment save(Comment comment);
    public void delete(Thread thread);
    public void deleteAll();
    public List<Thread> findAll();
    public List<Thread> findBySport(String sport);
    public Thread findByID(Long ID);
}
