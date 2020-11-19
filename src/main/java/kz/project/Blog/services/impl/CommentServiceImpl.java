package kz.project.Blog.services.impl;

import kz.project.Blog.exceptions.ServiceException;
import kz.project.Blog.models.entities.Comment;
import kz.project.Blog.repositories.CommentRepository;
import kz.project.Blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }
    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> findByPostId(Long id) throws ServiceException {
        return commentRepository.findAllByPostId(id);
    }

    @Override
    public Comment save(Comment comment) throws ServiceException {
        return commentRepository.save(comment);
    }
}
