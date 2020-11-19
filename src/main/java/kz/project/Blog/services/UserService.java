package kz.project.Blog.services;

import kz.project.Blog.exceptions.ServiceException;
import kz.project.Blog.models.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    List<User> findAll();
    User add(User user) throws ServiceException;
    User update(User user) throws ServiceException;
    User findOne(Long id) throws ServiceException;
    void deleteById(Long id) throws ServiceException;
    void delete(User user) throws ServiceException;
    User getUserByAuthentication(Authentication authentication) throws ServiceException;
    User findByLogin(String login);
    Set getAuthority(User user);
}
