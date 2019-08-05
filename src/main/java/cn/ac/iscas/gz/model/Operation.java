package cn.ac.iscas.gz.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * 记录操作的日志，包括憎删查改
 */
@Component

@Data
@Entity
@Table(name="operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//操作id
    private String loginAccountName;//登陆账户名称
    private Date operationTime;//操作时间
    private String operationType;//操作类型/add/delete/update/select
    private String operationClass;//修改的路径
    private String operationIp;//修改者的IP
    private String operationContent;//修改的具体
    private String operationSql;//操作者的最后运行的sql
    private String operationState;//操作的情况是否成功
    private String operationException;//如果有异常，异常的原因
    private String operationJsonResult;//json结果


}
