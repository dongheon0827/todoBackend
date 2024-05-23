package org.study.todobackend.domain.comment.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.study.todobackend.domain.comment.model.CommentModel

interface CommentRepository : JpaRepository<CommentModel, Long> {
    fun findByCardIdAndId(cardId: Long, id: Long): CommentModel?
}