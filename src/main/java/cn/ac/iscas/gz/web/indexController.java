package cn.ac.iscas.gz.web;

import cn.ac.iscas.gz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 */
//@RestController("/index")
@RestController
public class indexController {
    @Value("${name}")
    private  String name;
    @Value("${age}")
    private Integer age;
    //@Value("${address}")
   // private  String address;
   /* @Value("${sex}")
    private  String sex;*/
    @Autowired
    private User user;
    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String  say(){
        return user.toString()+"姓名 : "+name;

    }


}
