package is.hi.hbv501g.SportAppBackend.Services.Implementation;

import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Comment;
import is.hi.hbv501g.SportAppBackend.Persistence.Entities.Thread;
import is.hi.hbv501g.SportAppBackend.Persistence.Repositories.ClubRepository;
import is.hi.hbv501g.SportAppBackend.Persistence.Repositories.CommentRepository;
import is.hi.hbv501g.SportAppBackend.Persistence.Repositories.EventRepository;
import is.hi.hbv501g.SportAppBackend.Persistence.Repositories.ThreadRepository;
import is.hi.hbv501g.SportAppBackend.Services.CommentService;
import is.hi.hbv501g.SportAppBackend.Services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImplementation implements CommentService {

    CommentRepository commentRepository;

    @Autowired
    public CommentServiceImplementation(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @Override
    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment findCommentById(Long id) {
        return commentRepository.findCommentByID(id);
    }
}
