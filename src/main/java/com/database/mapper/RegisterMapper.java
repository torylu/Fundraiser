package com.database.mapper;

import com.database.view.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Tory on 4/25/17.
 */
public interface RegisterMapper {

    String chechValidility = "select * from user where userName = #{userName}";
    String register = "insert into user(userName,email,firstName,lastName,hometown,phone,pwd) values" +
            "(#{userName},#{email},#{firstName},#{lastName},#{hometown},#{phone},#{pwd})";
    String login = "select * from user where email = #{email} and pwd = #{pwd}";

    @Select(chechValidility)
    public User chechValidility(@Param("userName") String userName);
    @Select(login)
    public User login(@Param("email") String email,
                      @Param("pwd") String pwd);

    @Insert(register)
    public void register(@Param("userName") String userName,
                         @Param("email") String email,
                         @Param("fistName") String firstName,
                         @Param("lastName") String lastName,
                         @Param("hometown") String hometown,
                         @Param("phone") String phone,
                         @Param("pwd") String pwd);
}
