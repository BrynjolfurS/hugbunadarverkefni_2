package is.hi.hbv501g.SportAppBackend.Services;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Club;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Comment;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Event;

import java.util.List;

public interface CommentService {
    public List<Comment> findAllComments();
    public void delete(Comment comment);
    public Comment findCommentById(Long id);
}
