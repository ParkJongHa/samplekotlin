package sample04_spring.demo024_mybatis

import data.UserGroupByVo
import data.UserVo
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.stereotype.Service
import org.springframework.transaction.TransactionDefinition

@Service
class Demo024UserService(
    val mapper: Demo024UserMapper,
    val transactionManager: DataSourceTransactionManager
) {

    fun findAll(): List<UserVo> {
        var userList = mapper.selectUserList()

        if (userList.isEmpty()) {
            mapper.insertUser(UserVo(111, "일일일", 111))
            mapper.insertUser(UserVo(112, "일일이", 111))
            mapper.insertUser(UserVo(113, "일일삼", 111))
            mapper.insertUser(UserVo(114, "일일일", 111))
            mapper.insertUser(UserVo(115, "일일이", 112))
            mapper.insertUser(UserVo(116, "일일삼", 113))
            mapper.insertUser(UserVo(117, "일일사", 114))
            userList = mapper.selectUserList()
        }

        return userList
    }

    fun insertUserList() {
        val userVoList = (1..10).map { UserVo(1000 + it.toLong(), "천$it", 1000+it)}
        mapper.insertUserList(userVoList)
    }

    fun findGroupBy(groupByTarget: String): List<UserGroupByVo> {
        return mapper.selectGroupByCount(groupByTarget)
    }

    fun insertUser(isTransaction: Boolean, isError: Boolean) {
        val transactionStatus =
            if (isTransaction) {
                transactionManager.getTransaction(TransactionDefinition.withDefaults())
            } else {
                null
            }

        val maxId = mapper.selectMaxId() + 1000

        try {
            mapper.insertUser(UserVo(maxId + 1, "이름${maxId + 1}", maxId.toInt()))
            mapper.insertUser(UserVo(maxId + 2, "이름${maxId + 2}", maxId.toInt()))
            if (isError) throw Exception("insertUser error")
            mapper.insertUser(UserVo(maxId + 3, "이름${maxId + 3}", maxId.toInt()))

            transactionStatus?.run {transactionManager.commit(this)}
        } catch (e: Exception) {
            e.printStackTrace()
            transactionStatus?.run {transactionManager.rollback(this)} // 저장 모두 안됨
//            transactionManager.commit(transactionStatus)  // throw Exception 전 까지 insert 한건 들어감
        }
    }

}