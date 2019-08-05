package cn.ac.iscas.gz.dao;


import cn.ac.iscas.gz.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


import javax.persistence.Table;
@Repository
@Table(name="userinfo")
@Qualifier("userlDao")

public interface UserlDao{

        public User getUserById(int id);
        public void insertUser();
        //添加新的用户

        public void addNewUser(User u);

}

