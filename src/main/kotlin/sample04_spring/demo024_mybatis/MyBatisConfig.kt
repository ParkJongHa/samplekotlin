package sample04_spring.demo024_mybatis

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import javax.sql.DataSource


@Configuration
@MapperScan(
    basePackages = ["sample04_spring.demo024_mybatis"],
    annotationClass = Mapper::class,
    sqlSessionFactoryRef = "sqlSessionFactory" // sqlSessionFactory이름으로 등록된 Bean을 찾아 sqlSessionFactory로 사용
)
class MyBatisConfig {

    @Bean
    @Throws(Exception::class)
    fun sqlSessionFactory(@Qualifier("dataSource") dataSource: DataSource): SqlSessionFactory {
        val sqlSessionFactoryBean = SqlSessionFactoryBean()
        sqlSessionFactoryBean.setDataSource(dataSource)

        return sqlSessionFactoryBean.getObject()!!
    }

    @Bean
    fun transactionManager(dataSource: DataSource): DataSourceTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }

}