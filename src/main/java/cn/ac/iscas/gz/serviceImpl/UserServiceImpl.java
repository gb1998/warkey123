package cn.ac.iscas.gz.serviceImpl;

import cn.ac.iscas.gz.dao.UserlDao;
import cn.ac.iscas.gz.model.User;
import cn.ac.iscas.gz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    public UserlDao userlDao;

    @Override
    @Transactional
    public void addUser(User u) {
        userlDao.addNewUser(u);

    }
}
