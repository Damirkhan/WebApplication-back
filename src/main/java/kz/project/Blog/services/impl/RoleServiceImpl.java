package kz.project.Blog.services.impl;

import kz.project.Blog.exceptions.ServiceException;
import kz.project.Blog.models.entities.Role;
import kz.project.Blog.repositories.RoleRepository;
import kz.project.Blog.services.RoleService;
import kz.project.Blog.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findOne(Long id) throws ServiceException {
        if (id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("roleId is null")
                    .build();
        }
        return roleRepository.findById(id).get();
    }

    @Override
    public Role save(Role role) throws ServiceException {
        if (role == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("role is null")
                    .build();
        }
        return roleRepository.save(role);
    }
}
