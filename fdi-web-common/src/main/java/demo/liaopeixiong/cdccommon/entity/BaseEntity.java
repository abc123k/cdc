package demo.liaopeixiong.cdccommon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Setter
@Getter
@MappedSuperclass  //标记为基类，不映射到数据库表
@EntityListeners(AuditingEntityListener.class) // 启用审计监听
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
    protected Long id;

    @Comment("创建时间")
    @CreatedDate
    @Column(columnDefinition = "timestamp",name = "create_time")
    protected Timestamp createTime;

    @Comment("更新时间")
    @LastModifiedDate
    @Column(columnDefinition = "timestamp",name = "update_time")
    protected Timestamp updateTime;

}
