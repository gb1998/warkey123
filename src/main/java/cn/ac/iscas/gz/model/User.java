package cn.ac.iscas.gz.model;

import lombok.Data;
import lombok.Generated;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * 只是单纯的存用户的信息与用户的登录信息分离开来结构更加清晰
 */
@Component
@ConfigurationProperties(prefix = "user")
@Data
@Entity
@Table(name="userinfo")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//用户id
    private String username;//用户名称
    private Integer age;//用户年龄
    private String sex;//用户性别
    private String adrress;//用户地址
    private String description;//备注-描述
    private Date birthDay;//出生天数；
    private String test;//测试

}
