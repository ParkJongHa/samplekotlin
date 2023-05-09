package sample04_spring.demo023_jpa

import jakarta.persistence.*

@Entity
@Table(name = "tb_user") // basically `user` used
class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var name: String? = null,

    @Column
    var age: Int? = 0

)