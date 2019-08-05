package cn.ac.iscas.gz;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EntityManagerFactory;

/**
 * SpringBootApplication 启动类
 *
 */

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@EnableScheduling

public class BootApplication
{

    public static void main(String[] args){

        ApplicationContext context =SpringApplication.run(BootApplication.class,args);
    }

    /**
     * 注解在方法上表示将该方法返回的对象标识交由Spring容器来管理
     * @param
     * @return
     */
   @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
        return hemf.getSessionFactory();
    }



}
