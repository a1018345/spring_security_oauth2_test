package lanyao.springsecurity.oauth.config;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UserSession implements UserDetails {

    private Integer id;
    private String email;
    private String name;
    private String password;
    private String status;
    private String uuid;

    private Collection<? extends GrantedAuthority> authorities;
    private Integer passwordErrorCount;

    private Boolean enable = Boolean.TRUE;
    private Boolean lock = Boolean.FALSE;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return lock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
