package org.study.todobackend.domain.todo.service

import org.study.todobackend.domain.card.dto.AddCardRequest
import org.study.todobackend.domain.card.dto.CardResponse
import org.study.todobackend.domain.card.dto.UpdateCardRequest
import org.study.todobackend.domain.todo.dto.CreateTodoRequest
import org.study.todobackend.domain.todo.dto.TodoResponse

interface TodoService {

    fun getAllTodoList(): List<TodoResponse>

    fun getTodoById(id: Long): TodoResponse

    fun createTodo(request: CreateTodoRequest): TodoResponse

    fun deleteTodo(todoId: Long)

    fun getAllCardList(todoId: Long): List<CardResponse>

    fun getCardById(todoId: Long, cardId: Long): CardResponse

    fun addCard(todoId: Long, request: AddCardRequest): CardResponse

    fun updateCard(todoId: Long, cardId: Long, request: UpdateCardRequest): CardResponse

    fun removeCard(todoId: Long, cardId: Long)
}