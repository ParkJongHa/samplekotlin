package sample04_spring.demo024_mybatis

import data.UserVo
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface Demo024UserMapper {

    @Select("""
        SELECT * FROM member
    """)
    fun selectUserList(): List<UserVo>

    @Insert("""
        INSERT INTO member (id, name, age) 
        VALUES (#{id}, #{name}, #{age})
    """)
    fun insertUser(user: UserVo)

}