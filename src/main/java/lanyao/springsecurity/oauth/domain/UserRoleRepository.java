package lanyao.springsecurity.oauth.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {


    @Query("SELECT r FROM UserRole u INNER JOIN u.role r where u.user.id = ?1 ")
    List<Role> findByEmail(Integer userId);




}
