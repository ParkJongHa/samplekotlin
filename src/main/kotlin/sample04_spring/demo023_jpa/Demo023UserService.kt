package sample04_spring.demo023_jpa

import org.springframework.stereotype.Service

@Service
class Demo023UserService(
    val demo023UserRepository: Demo023UserRepository
) {

    fun findById(id: Long): User? {
        return demo023UserRepository.findById(id).orElse(null)
    }

}