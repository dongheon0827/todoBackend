package org.study.todobackend.domain.card.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.study.todobackend.domain.card.model.CardModel

interface CardRepository : JpaRepository<CardModel, Long> {
    fun findByTodoIdAndId(todoId: Long, id: Long): CardModel?
}