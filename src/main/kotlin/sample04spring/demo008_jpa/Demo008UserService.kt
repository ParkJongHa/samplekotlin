package sample04spring.demo008_jpa

import org.springframework.stereotype.Service

@Service
class Demo008UserService(
    val demo008UserRepository: Demo008UserRepository
) {

    fun findById(id: Long): User? {
        return demo008UserRepository.findById(id).orElse(null)
    }

}