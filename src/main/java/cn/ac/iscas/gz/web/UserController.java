package cn.ac.iscas.gz.web;


import cn.ac.iscas.gz.dao.UserDaoImpl;

import cn.ac.iscas.gz.enums.ResultTypeEnum;
import cn.ac.iscas.gz.result.DataResult;
import cn.ac.iscas.gz.model.Operation;
import cn.ac.iscas.gz.model.User;
import cn.ac.iscas.gz.service.UserService;
import cn.ac.iscas.gz.util.ResultUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Spring 支持的JdbcTemplate 使用测试
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;
   // @Autowired
    //private HibernateTemplate hibernateTemplate;
  /*  @Autowired
    private  HibernateTemplate hibernateTemplate;*/
    //注入hiberTemplate
    @Autowired
    private  User usera;
    @Autowired
    private UserDaoImpl userDao;
    @RequestMapping("/listUser")
    public  DataResult<List<User>>  getAllUser(HttpServletRequest request){

       User user=new User();
       user.setUsername("天啊地啊啊");
       userService.addUser(user);
        //SessionFactory sessionFactory= hibernateTemplate.getSessionFactory();
        //System.out.println(sessionFactory);
        userDao.insertUser();

        DataResult<List<User>> dataResult=new DataResult<>();
        dataResult.setCode(ResultTypeEnum.succeess.getCode());
        dataResult.setMessage(ResultTypeEnum.succeess.name());
        StringBuilder sql=new StringBuilder();
        sql.append("select id,username,age,sex,adrress,birth_day from userinfo ");
        RowMapper<User> userRowMapper=new BeanPropertyRowMapper<>(User.class);
        List<User> users= (List<User>) jdbcTemplate.query(sql.toString(),userRowMapper);//query 返回多值，queryForObject 返回单值
        //如果PO和数据库模型的字段完全对应（字段名字一样或者驼峰式与下划线式对应），如果使用JdbcTemplate则可以使用这个RowMapper作为PO和数据库的映射。
        //List<User> users=new ArrayList<>();
        //users.add(user);
        dataResult.setData(users);
        Operation operation=new Operation();
        operation.setLoginAccountName("gaoba");
        //operation.setOperationClass(String.valueOf(UserController.class.getResource("/")));
        operation.setOperationState("Select");
        operation.setOperationSql(sql.toString());
        operation.setOperationTime(new Date());
        operation.setOperationIp(request.getRemoteAddr());
        UrlPathHelper helper = new UrlPathHelper();
        StringBuffer buff = request.getRequestURL();
        String uri = request.getRequestURI();
        String origUri = helper.getOriginatingRequestUri(request);
        buff.replace(buff.length() - uri.length(), buff.length(), origUri);
        String queryString = helper.getOriginatingQueryString(request);
        if (queryString != null)
        {
            buff.append("?").append(queryString);
        }
        operation.setOperationClass(buff.toString());
        System.out.println(operation);
     //   hibernateTemplate.save(operation);


        return dataResult;
    }

    @RequestMapping("/listMap")
    public  DataResult<List<Map<String,Object>> > getAllMap(){
        StringBuilder sql=new StringBuilder();
        sql.append("select id,username,age,sex,adrress from userinfo ");
        //使用QueryForList 来查询
        List<Map<String,Object>> maps=jdbcTemplate.queryForList(sql.toString());
       // ResultUtil.success(maps);
        return ResultUtil.success(maps);
    }

}
