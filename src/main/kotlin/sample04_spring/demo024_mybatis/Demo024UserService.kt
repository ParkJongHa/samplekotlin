package sample04_spring.demo024_mybatis

import data.UserGroupByVo
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
            demo024UserMapper.insertUser(UserVo(112, "일일이", 111))
            demo024UserMapper.insertUser(UserVo(113, "일일삼", 111))
            demo024UserMapper.insertUser(UserVo(114, "일일일", 111))
            demo024UserMapper.insertUser(UserVo(115, "일일이", 112))
            demo024UserMapper.insertUser(UserVo(116, "일일삼", 113))
            demo024UserMapper.insertUser(UserVo(117, "일일사", 114))
            userList = demo024UserMapper.selectUserList()
        }

        return userList
    }

    fun insertUserList() {
        val userVoList = (1..10).map { UserVo(1000 + it.toLong(), "천$it", 1000+it)}
        demo024UserMapper.insertUserList(userVoList)
    }

    fun findGroupBy(groupByTarget: String): List<UserGroupByVo> {
        return demo024UserMapper.selectGroupByCount(groupByTarget)
    }

}