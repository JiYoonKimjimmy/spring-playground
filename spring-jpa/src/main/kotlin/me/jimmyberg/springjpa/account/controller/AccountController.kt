package me.jimmyberg.springjpa.account.controller

import jakarta.websocket.server.PathParam
import me.jimmyberg.springjpa.account.repository.entity.AccountEntity
import me.jimmyberg.springjpa.account.service.AccountService
import org.springframework.web.bind.annotation.*

@RestController
class AccountController(
    private val accountService: AccountService
) {

    @PostMapping("/account")
    fun saveEntity(@RequestBody entity: AccountEntity): AccountEntity {
        return accountService.saveEntity(entity)
    }

    @GetMapping("/account/{accountNo}")
    fun findEntity(@PathVariable accountNo: String): AccountEntity {
        return accountService.findEntity(accountNo)
    }

    @PutMapping("/account/{accountNo}")
    fun incrementAmount(
        @PathVariable accountNo: String,
        @PathParam("amount") amount: Long
    ): AccountEntity {
        return accountService.incrementAmount(accountNo, amount)
    }

}