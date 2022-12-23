package com.example.demo.sample008_jpa

import jakarta.persistence.*


@Entity
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false)
    val name: String? = null

    @Column(nullable = false)
    val age = 0

}