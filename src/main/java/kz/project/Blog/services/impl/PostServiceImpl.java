package kz.project.Blog.services.impl;

import kz.project.Blog.exceptions.ServiceException;
import kz.project.Blog.models.entities.Post;
import kz.project.Blog.repositories.PostRepository;
import kz.project.Blog.services.PostService;
import kz.project.Blog.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findAllByAuthorId(Long id) throws ServiceException {
        return postRepository.findAllByAuthorId(id);
    }

    @Override
    public Post findById(Long id) throws ServiceException {
        if (id == null){
            throw ServiceException.builder()
                    .message("id is null")
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .build();
        }
        return postRepository.findById(id).get();
    }

    @Override
    public Post save(Post post) throws ServiceException {
        if (post == null){
            throw ServiceException.builder()
                    .message("post is null")
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .build();
        }
        return postRepository.save(post);
    }
}
