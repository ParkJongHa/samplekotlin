package sample04_spring.demo022_jpa

import org.springframework.stereotype.Service

@Service
class Demo022UserService(
    val demo022UserRepository: Demo022UserRepository
) {

    fun findById(id: Long): User? {
        return demo022UserRepository.findById(id).orElse(null)
    }

}