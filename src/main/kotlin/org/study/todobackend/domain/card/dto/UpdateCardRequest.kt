package org.study.todobackend.domain.card.dto

data class UpdateCardRequest(
    val title:String,
    val description:String?,
    val name:String,
)
