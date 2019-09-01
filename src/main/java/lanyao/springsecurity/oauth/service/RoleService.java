package lanyao.springsecurity.oauth.service;

import lanyao.springsecurity.oauth.domain.Role;
import lanyao.springsecurity.oauth.domain.RoleRepository;
import lanyao.springsecurity.oauth.domain.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService {



    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    RoleRepository roleRepository;

    public List<Role> findByUserId(Integer userId){
        return userRoleRepository.findByEmail(userId);
    }

    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    public Role findById(Integer id){
        return roleRepository.getOne(id);
    }



}
