package com.example.demo.sample008_jpa

import org.springframework.stereotype.Service

@Service
class Sample008UserService(
    val sample008UserRepository: Sample008UserRepository
) {

    fun findById(id: Long): User? {
        return sample008UserRepository.findById(id).orElse(null)
    }

}