package sample04_spring.demo024_mybatis

import data.UserVo
import data.UserGroupByVo
import org.apache.ibatis.annotations.*

@Mapper
interface Demo024UserMapper {

    @Select("""
        select * from member
    """)
    fun selectUserList(): List<UserVo>

    @Select("""
        select max(id) from member
    """)
    fun selectMaxId(): Long

    @Insert("""
        insert into member (id, name, age) 
        values (#{id}, #{name}, #{age})
    """)
    fun insertUser(user: UserVo)

    @Insert("""<script>
        insert into member (id, name, age) 
        values 
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
        var sql = "select $groupByTarget, count(*) as cnt from member"

        when (groupByTarget) {
            "name" -> sql += " group by name "
            "age" -> sql += " group by age "
        }

        return sql
    }

}
