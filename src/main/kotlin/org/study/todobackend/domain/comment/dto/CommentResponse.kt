package org.study.todobackend.domain.comment.dto

data class CommentResponse(
    val id: Long,
    val name: String,
    val password: String,
    val comments : String
)
