package cn.ac.iscas.gz.dao;

import cn.ac.iscas.gz.model.User;
import org.hibernate.*;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDao implements UserlDao {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private SessionFactory sessionFactory;


    public Session getSession() {
        return entityManager.unwrap(SessionFactory.class).openSession();
    }
    @Override
    public User getUserById(int id) {
         return (User) getHibernateTemplate().get(User.class, (long)id);
}
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
    public void insertUser() {
        User user=new User();
        user.setUsername("中国共产党");
        user.setAge(100);
        user.setId((long) 123.0);
       // getHibernateTemplate().save(user);
       // getHibernateTemplate().saveOrUpdate(user);

        List<User> userList= (List<User>) getHibernateTemplate().find("from User u");
        for(User use : userList){
            System.out.println(use);
        }

    }
    @SuppressWarnings("unchecked")

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
    public void addNewUser(User u) {
        if(u!=null){
            u.setUsername("孙悟空");
            u.setDescription("attend to insert a data");
            HibernateEntityManager hEntityManager = (HibernateEntityManager)entityManager;
            Session session = hEntityManager.getSession();
            //session.setReadOnly(u,false);
            session.setFlushMode(FlushMode.AUTO);
           // getHibernateTemplate().save(u);
            getHibernateTemplate().execute(new HibernateCallback<User>() {

                @Override
                public User doInHibernate(Session session) throws HibernateException {
                   // session.setFlushMode(FlushMode.AUTO);
                    //boolean defaultReadOnly1 = session.isDefaultReadOnly();
                    session.save(u);
                    return null;
                }
            });


        }

    }


}

