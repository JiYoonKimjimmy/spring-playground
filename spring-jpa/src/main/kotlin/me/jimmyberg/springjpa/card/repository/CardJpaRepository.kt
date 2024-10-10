package me.jimmyberg.springjpa.card.repository

import me.jimmyberg.springjpa.card.repository.entity.CardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CardJpaRepository : JpaRepository<CardEntity, Long>