package kz.project.Blog.services.impl;

import kz.project.Blog.exceptions.ServiceException;
import kz.project.Blog.models.entities.Image;
import kz.project.Blog.models.entities.User;
import kz.project.Blog.repositories.ImageRepository;
import kz.project.Blog.repositories.UserRepository;
import kz.project.Blog.services.UserService;
import kz.project.Blog.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User add(User user) throws ServiceException {
        if (user == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("user is null")
                    .build();
        }
        user.setActive(1L);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) throws ServiceException {
        if(user.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("user is null")
                    .build();
        }
        return  userRepository.save(user);
    }

    @Override
    public User findOne(Long id) throws ServiceException {
        if (id == null) {
            throw ServiceException.builder()
                    .message("id is null")
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .build();
        }
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) throws ServiceException {

    }

    @Override
    public void delete(User user) throws ServiceException {

    }

    @Override
    public User findByLogin(String login) {
        if (login == null){
            throw  ServiceException.builder()
                    .message("login is null")
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .build();
        }
        return userRepository.findByLoginAndDeletedAtIsNull(login);
    }

    @Override
    public Set getAuthority(User user) {
        Set authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority(user));
    }

    @Override
    public User getUserByAuthentication(Authentication authentication) throws ServiceException {
        return findByLogin(authentication.getName());
    }
}
