package com.example.demo.sample008_jpa

import org.springframework.data.jpa.repository.JpaRepository

interface Sample008UserRepository : JpaRepository<User, Long> {



}