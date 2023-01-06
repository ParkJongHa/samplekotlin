package sample02spring.demo008_jpa

import org.springframework.data.jpa.repository.JpaRepository

interface Demo008UserRepository : JpaRepository<User, Long> {

}