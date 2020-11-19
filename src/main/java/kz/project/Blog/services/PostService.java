package kz.project.Blog.services;

import kz.project.Blog.exceptions.ServiceException;
import kz.project.Blog.models.entities.Post;

import java.util.List;

public interface PostService {

    List<Post> findAll();
    List<Post> findAllByAuthorId(Long id) throws ServiceException;
    Post findById(Long id) throws ServiceException;
    Post save (Post post) throws ServiceException;
}
