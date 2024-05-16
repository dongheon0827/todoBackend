package org.study.todobackend.domain.todo.dto

data class TodoResponse(
    val id:Long,
    val title: String,
    val date: String,
    val name: String,
)
