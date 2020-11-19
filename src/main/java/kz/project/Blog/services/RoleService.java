package kz.project.Blog.services;

import kz.project.Blog.exceptions.ServiceException;
import kz.project.Blog.models.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findOne(Long id) throws ServiceException;
    Role save(Role role) throws ServiceException;
}
