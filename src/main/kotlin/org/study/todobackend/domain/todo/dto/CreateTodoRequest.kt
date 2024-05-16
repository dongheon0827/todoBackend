package org.study.todobackend.domain.todo.dto

data class CreateTodoRequest (
    val title:String,
    val date:String,
    val name:String,
)