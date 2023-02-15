package sample04_spring.demo022_jpa

import org.springframework.data.jpa.repository.JpaRepository

interface Demo022UserRepository : JpaRepository<User, Long> {

}