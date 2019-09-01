package lanyao.springsecurity.oauth.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {


    User findByEmail(String email);

    @Modifying
    @Query("update User u set u.loginTime =?2 where u.id=?1")
    Integer updateLoginTime(Integer id, LocalDateTime nowLocalDateTime);


    @Query("select u from User u where u.uuid = function('unhex',?1) ")
    User findByUuid(String uuid);



}
