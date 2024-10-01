package me.jimmyberg.springjpa.account.repository.entity

import jakarta.persistence.*
import me.jimmyberg.springjpa.member.repository.entity.MemberEntity

@Table(name = "ACCOUNTS_V2")
@Entity
class AccountEntityV2(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val accountNo: String,
    val amount: Long,
    val beforeAmount: Long,

    @JoinColumn(name = "MEMBER_ID")
    @ManyToOne(cascade = [CascadeType.ALL])
    val member: MemberEntity
) {
    constructor(accountNo: String, member: MemberEntity) : this(
        accountNo = accountNo,
        amount = 0,
        beforeAmount = 0,
        member = member
    )
}