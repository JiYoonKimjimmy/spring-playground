package me.jimmyberg.springjpa.account.repository.entity

import jakarta.persistence.*
import me.jimmyberg.springjpa.card.repository.entity.CardEntity
import me.jimmyberg.springjpa.member.repository.entity.MemberEntity

@Table(name = "V2_ACCOUNTS")
@Entity
class V2AccountEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val accountNo: String,
    val amount: Long,

    @JoinColumn(name = "MEMBER_ID")
    @ManyToOne(cascade = [CascadeType.ALL])
    val member: MemberEntity,

    @JoinColumn(name = "CARD_ID")
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    private var card: CardEntity?

) {

    constructor(accountNo: String, member: MemberEntity) : this(
        accountNo = accountNo,
        amount = 0,
        member = member,
        card = null
    )

    fun getCard(): CardEntity? = this.card
    fun setCard(card: CardEntity): V2AccountEntity = this.apply { this.card = card }

}