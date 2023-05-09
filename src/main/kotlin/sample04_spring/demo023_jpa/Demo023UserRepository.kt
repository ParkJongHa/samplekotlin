package sample04_spring.demo023_jpa

import org.springframework.data.jpa.repository.JpaRepository

interface Demo023UserRepository : JpaRepository<User, Long> {

}