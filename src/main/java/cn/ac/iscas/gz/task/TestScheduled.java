package cn.ac.iscas.gz.task;

import cn.ac.iscas.gz.model.User;
import cn.ac.iscas.gz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试定时任务类
 */
@Component
public class TestScheduled {
    private static final Logger loger= LoggerFactory.getLogger(TestScheduled.class);

    @Autowired
     private UserService userService;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 5000)
     public void printToConsole(){
     loger.info("The time is now at"+simpleDateFormat.format(new Date()));
     System.out.println("The time is now at"+simpleDateFormat.format(new Date()));
    // User user=new User();
     //user.setUsername("定时任务加入数据");
     //user.setBirthDay(new Date());
    // userService.addUser(user);


    }

}
