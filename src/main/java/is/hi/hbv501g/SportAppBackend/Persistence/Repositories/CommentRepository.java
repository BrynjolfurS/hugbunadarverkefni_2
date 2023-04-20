package is.hi.hbv501g.SportAppBackend.Persistence.Repositories;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Club;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public Comment save(Comment comment);
    public void delete(Comment comment);
    public Comment findCommentByID(Long id);
    List<Comment> findCommentsByThread(long id);
}