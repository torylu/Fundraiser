package com.database.controller;

import com.database.mapper.RegisterMapper;
import com.database.view.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tory on 4/25/17.
 */

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public boolean login(@RequestParam("email") String email,
                         @RequestParam("email") String pwd){
        SqlSession session = DataBaseConfiguration.getInstance().openSession();
        RegisterMapper mapper = session.getMapper(RegisterMapper.class);
        User user = mapper.login(email,pwd);
        boolean flag = user == null ? false : true;
        session.commit();
        session.close();
        return flag;
    }
}
