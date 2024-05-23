package org.study.todobackend.domain.comment.dto

data class AddCommentRequest(
    val name: String,
    val password: String,
    val comments: String,
    )
