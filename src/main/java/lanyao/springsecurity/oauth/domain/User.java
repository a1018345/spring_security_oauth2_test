/*
 * This file is generated by jOOQ.
*/
package lanyao.springsecurity.oauth.domain;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lanyao.springsecurity.oauth.auditable.BaseAuditable;
import lanyao.springsecurity.oauth.form.LabelForm;
import lanyao.springsecurity.oauth.jackson.HexBase32Ser;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;


/**
 * 使用者
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "user", schema = "master_program")
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class User extends BaseAuditable implements Serializable {

    private static final long serialVersionUID = -1040962656;

    private Integer       id;

    private String        name;

    private String        phone;

    private String        email;

    private String        pwd;

    private String        status;

    private Integer       errorCount;
    private LocalDateTime loginTime;
    private LocalDateTime registeredDate;
    private byte[]        uuid;



    private Set<UserRole> userRoles;



    private LabelForm statusForm;


    private Integer roleId;




    public User() {}

    public User(User value) {
        this.id = value.id;
        this.name = value.name;
        this.phone = value.phone;
        this.email = value.email;
        this.pwd = value.pwd;
        this.status = value.status;
        this.errorCount = value.errorCount;
        this.loginTime = value.loginTime;
        this.registeredDate = value.registeredDate;
        this.uuid = value.uuid;
    }

    public User(
        Integer       id,
        String        name,
        String        phone,
        String        email,
        String        pwd,
        String        status,
        Integer       errorCount,
        LocalDateTime loginTime,
        LocalDateTime registeredDate,
        byte[]        uuid
    ) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.pwd = pwd;
        this.status = status;
        this.errorCount = errorCount;
        this.loginTime = loginTime;
        this.registeredDate = registeredDate;
        this.uuid = uuid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, length = 30)
    @NotNull
    @Size(max = 30)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "phone", length = 10)
    @Size(max = 10)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "email", unique = true, nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "pwd", nullable = false, length = 80)
    @NotNull
    @Size(max = 80)
    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Column(name = "status", nullable = false, length = 2)
    @NotNull
    @Size(max = 2)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "error_count", nullable = false, precision = 10)
    @NotNull
    public Integer getErrorCount() {
        return this.errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    @Column(name = "login_time")
//    @Convert(converter = LocalDateTimeConverter.class)
    public LocalDateTime getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    @Column(name = "registered_date", nullable = false)
    @NotNull
    public LocalDateTime getRegisteredDate() {
        return this.registeredDate;
    }

    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
    }

    @Column(name = "uuid", nullable = false, length = 16)
    @NotNull
    @JsonSerialize(using = HexBase32Ser.class)
    public byte[] getUuid() {
        return this.uuid;
    }

    public void setUuid(byte... uuid) {
        this.uuid = uuid;
    }


//    ====================

//    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }




//    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    public LabelForm getStatusForm() {
        return statusForm;
    }

    public void setStatusForm(LabelForm statusForm) {
        this.statusForm = statusForm;
    }

    @Transient
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


//    @ManyToMany
//    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id")
//        ,inverseJoinColumns = @JoinColumn(name = "role_id"))
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

}

