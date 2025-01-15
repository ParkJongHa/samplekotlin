package sample04_spring.demo024_mybatis

import data.UserVo
import org.springframework.stereotype.Service

@Service
class Demo024UserService(
    val demo024UserMapper: Demo024UserMapper
) {

    fun findAll(): List<UserVo> {
        var userList = demo024UserMapper.selectUserList()

        if (userList.isEmpty()) {
            demo024UserMapper.insertUser(UserVo(111, "일일일", 111))
            userList = demo024UserMapper.selectUserList()
        }

        return userList
    }

}