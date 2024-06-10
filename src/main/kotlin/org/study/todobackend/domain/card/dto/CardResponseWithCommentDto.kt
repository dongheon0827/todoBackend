package org.study.todobackend.domain.card.dto

import org.study.todobackend.domain.comment.dto.CommentResponse

data class CardResponseWithCommentDto(
    val id: Long,
    val title: String,
    val description: String?,
    val date: String,
    val name: String,
    val status: Boolean,
    var comments: List<CommentResponse>
)
