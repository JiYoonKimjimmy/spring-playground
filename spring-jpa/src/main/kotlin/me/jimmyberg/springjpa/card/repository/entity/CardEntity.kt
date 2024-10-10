package me.jimmyberg.springjpa.card.repository.entity

import jakarta.persistence.*

@Table(name = "CARDS")
@Entity
class CardEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val cardNo: String,
    val cardName: String

)