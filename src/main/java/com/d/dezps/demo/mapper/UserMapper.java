package com.d.dezps.demo.mapper;

import com.d.dezps.demo.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper
{
    @Select("SELECT * FROM USER WHERE username=#{username} AND password=#{password}")
    User findByUser(@Param("username") String username,@Param("password")String password);

    @Select("SELECT * FROM USER WHERE username=#{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM USER WHERE id=#{id}")
    User findById(@Param("id") Long id);

    @Update("update user set name=#{name},username=#{username},password=#{password},isadmin=#{isadmin} where id=#{id}")
    boolean updateUser(@Param("name") String name,@Param("username")String username,@Param("password")String password,@Param("isadmin")Boolean isadmin,@Param("id")Long id);

    @Select("select * from user")
    List<User> findAllUser();

    @Insert("Insert into user(name, username, password, isadmin) values(#{name},#{username},#{password},#{isadmin})")
    boolean addUser(@Param("name") String name,@Param("username")String username,@Param("password")String password,@Param("isadmin")Boolean isadmin);

    @Delete("delete from user where id=#{id}")
    boolean deleteUser(@Param("id")Long id);
}


