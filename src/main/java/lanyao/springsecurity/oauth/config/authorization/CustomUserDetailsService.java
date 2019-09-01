package lanyao.springsecurity.oauth.config.authorization;

import lanyao.springsecurity.oauth.SystemConstant;
import lanyao.springsecurity.oauth.config.UserSession;
import lanyao.springsecurity.oauth.domain.Role;
import lanyao.springsecurity.oauth.domain.User;
import lanyao.springsecurity.oauth.service.CommonService;
import lanyao.springsecurity.oauth.service.RoleService;
import lanyao.springsecurity.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    CommonService commonService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username:::"+username);
        System.out.println("userService:::"+userService);

        if(username==null || username.trim().equals("")){
            throw new UsernameNotFoundException("找不到");
        }

        User user = userService.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("找不到人");
        }

        List<Role> byUserId = roleService.findByUserId(user.getId());

        StringBuffer roleBuffer = new StringBuffer();
        for(int i=0;i<byUserId.size();i++){
            roleBuffer.append(i == 0 ? byUserId.get(i).getName() : ","+byUserId.get(i).getName());
        }

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roleBuffer.toString());
        UserSession userSession = new UserSession();
        userSession.setAuthorities(grantedAuthorities);
        userSession.setId(user.getId());
        userSession.setEmail(user.getEmail());
        userSession.setName(user.getName());
        userSession.setPassword(user.getPwd());
        userSession.setPasswordErrorCount(user.getErrorCount());
        userSession.setStatus(user.getStatus());
        String s = commonService.hexToStr(user.getUuid());


        String uuid = "";
        try {
            uuid = commonService.encodeBase32(s.getBytes(SystemConstant.encode_UTF_8.getValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        userSession.setUuid(uuid);

        if(userSession.getStatus().equals(SystemConstant.Constants.USER_STATUS_NORMAL)){
            userSession.setEnable(Boolean.TRUE);
            userSession.setLock(Boolean.TRUE);
        }else if(userSession.getStatus().equals(SystemConstant.Constants.USER_STATUS_LOCKED)){
            userSession.setEnable(Boolean.TRUE);
            userSession.setLock(Boolean.FALSE);
        }else if(userSession.getStatus().equals(SystemConstant.Constants.USER_STATUS_ENABLE)){
            userSession.setLock(Boolean.TRUE);
            userSession.setEnable(Boolean.FALSE);
        }


        return userSession;
    }
}
