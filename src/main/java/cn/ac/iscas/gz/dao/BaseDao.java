package cn.ac.iscas.gz.dao;

import cn.ac.iscas.gz.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
public abstract class BaseDao extends HibernateDaoSupport {

    public HibernateTemplate hibernateTemplate;

    @Autowired
    public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
        super.setSessionFactory(sessionFactory);
    }

    public HibernateTemplate getMyHibernateTemplate(){
        this.hibernateTemplate=getHibernateTemplate();
        return  this.hibernateTemplate;
    }


     public SessionFactory initSessionFactory() {
           Configuration config = new Configuration().configure();
           //读取hibernate核心配置文件内容，创建session工厂
           return config.buildSessionFactory();
}



}

