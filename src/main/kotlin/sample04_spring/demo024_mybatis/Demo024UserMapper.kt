package sample04_spring.demo024_mybatis

import data.UserVo
import data.UserGroupByVo
import org.apache.ibatis.annotations.*
import org.hibernate.sql.ast.tree.insert.InsertStatement

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

    @Insert("""<script>
        INSERT INTO member (id, name, age) 
        VALUES 
        <foreach collection='list' item='item' separator=','>
        (#{item.id}, #{item.name}, #{item.age})
        </foreach>
    </script>""")
    fun insertUserList(userList: List<UserVo>): Int

    @SelectProvider(type = UserSqlProvider::class, method = "userGroupBy")
    fun selectGroupByCount(groupByTarget: String): List<UserGroupByVo>

}


class UserSqlProvider {

    fun userGroupBy(groupByTarget: String): String {
        var sql = "SELECT $groupByTarget, count(*) as cnt FROM member"

        when (groupByTarget) {
            "name" -> sql += " group by name "
            "age" -> sql += " group by age "
        }

        return sql
    }

}
