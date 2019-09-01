package lanyao.springsecurity.oauth.service;

import lanyao.springsecurity.oauth.domain.User;
import lanyao.springsecurity.oauth.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordService passwordService;
    @Autowired
    CommonService commonService;

    @Autowired
    RoleService roleService;


    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void updateLoginTime(Integer id){
        userRepository.updateLoginTime(id, LocalDateTime.now());
    }


    public User findByUuid(String hexUuid){
        return userRepository.findByUuid(hexUuid);
    }

    public void updatePersonal(User user){
        Integer id = user.getId();

        User dbUser = userRepository.getOne(id);

        dbUser.setName(user.getName());
        dbUser.setPhone(user.getPhone());
        dbUser.setEmail(user.getEmail());

        userRepository.save(dbUser);
    }

    public List<User> getUserAll(String adminName){


        List<User> name = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();
            if (adminName != null && !adminName.equals("")) {
                predicatesList.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + adminName + "%")));
            }

            criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

//            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
            return null;
        });


        return name;
    }




}
