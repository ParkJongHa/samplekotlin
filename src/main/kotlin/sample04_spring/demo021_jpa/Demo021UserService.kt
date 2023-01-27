package sample04_spring.demo021_jpa

import org.springframework.stereotype.Service

@Service
class Demo021UserService(
    val demo021UserRepository: Demo021UserRepository
) {

    fun findById(id: Long): User? {
        return demo021UserRepository.findById(id).orElse(null)
    }

}