package com.star.demo.mapper;

import com.star.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE username = #{username}")
    @Results({@Result(property = "id", column = "id"), @Result(property = "username", column = "username"), @Result(property = "password", column = "password"), @Result(property = "email", column = "email"), @Result(property = "address", column = "address"), @Result(property = "phone", column = "phone")})
    User findByUsername(String username);

    @Insert("INSERT INTO users (username, password, email, address, phone) VALUES (#{username}, #{password}, #{email}, #{address}, #{phone})")
    void insertUser(User user);
}