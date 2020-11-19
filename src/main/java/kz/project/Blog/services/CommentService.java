package kz.project.Blog.services;

import kz.project.Blog.exceptions.ServiceException;
import kz.project.Blog.models.entities.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
    List<Comment> findByPostId(Long id) throws ServiceException;
    Comment save(Comment comment)throws ServiceException;
}
