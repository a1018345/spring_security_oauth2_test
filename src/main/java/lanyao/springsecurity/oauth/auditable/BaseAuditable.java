package lanyao.springsecurity.oauth.auditable;


import com.fasterxml.jackson.annotation.JsonFormat;
import lanyao.springsecurity.oauth.SystemConstant;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditable {






    @CreatedBy
    @Column(name = "insert_user_id", nullable = false, precision = 10,updatable = false)
    private Integer       insertUserId;

    @LastModifiedBy
    @Column(name = "last_modify_user_id", nullable = false, precision = 10)
    private Integer       lastModifyUserId;

    @JsonFormat(pattern = SystemConstant.Constants.DATE_ALL_FORMAT,timezone = SystemConstant.Constants.GMT_8)
    private LocalDateTime lastModifyDate;










}
