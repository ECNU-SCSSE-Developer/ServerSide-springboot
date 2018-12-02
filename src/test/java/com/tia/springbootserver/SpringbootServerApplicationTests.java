package com.tia.springbootserver;

import com.tia.springbootserver.entity.User;
import com.tia.springbootserver.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootServerApplicationTests {

    @Autowired
    UserMapper userMapper;


    @Test
    public void contextLoads() {
    }


//    @Test
//    public void userMapperTest(){
//        User user = new User();
//        user.setStudentId("1016");
//        user.setOpenId("o4QwQ5VIJtesWAcQZlkq6JbhwJNc");
//        userMapper.updateByPrimaryKey(user);
//    }

}
