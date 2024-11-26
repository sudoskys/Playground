package myapp.mapper;

import myapp.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT id, username, password, email, role FROM web_user WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT id, username, password, email, role FROM web_user WHERE id = #{id}")
    User findById(Long id);

    // insert user
    void insert(User user);


}