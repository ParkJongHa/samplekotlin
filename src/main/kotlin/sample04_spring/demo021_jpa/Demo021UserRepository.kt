package sample04_spring.demo021_jpa

import org.springframework.data.jpa.repository.JpaRepository

interface Demo021UserRepository : JpaRepository<User, Long> {

}