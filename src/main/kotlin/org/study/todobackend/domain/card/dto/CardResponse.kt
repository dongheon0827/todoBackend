package org.study.todobackend.domain.card.dto

data class CardResponse(
    val id: Long,
    val title: String,
    val description: String?,
    val date: String,
    val name: String,
    val status : Boolean
)