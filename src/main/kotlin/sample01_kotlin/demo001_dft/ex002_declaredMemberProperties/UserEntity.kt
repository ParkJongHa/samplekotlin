package sample01_kotlin.demo001_dft.ex002_declaredMemberProperties

import jakarta.persistence.Column
import jakarta.persistence.Id

open class UserEntity {

    @Id
    open var id: String? = null

    @Column
    open var name: String? = null

    open var isChecked: Boolean? = null

}