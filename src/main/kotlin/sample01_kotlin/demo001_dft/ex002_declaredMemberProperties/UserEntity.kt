package sample01_kotlin.demo001_dft.ex002_declaredMemberProperties

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
open class UserEntity {

    @Id
    open var id: String? = null

    @Column
    open var name: String? = null

    open var isChecked: Boolean? = null

}