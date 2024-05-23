package org.study.todobackend.domain.todo.service

import org.study.todobackend.domain.card.dto.AddCardRequest
import org.study.todobackend.domain.card.dto.CardResponse
import org.study.todobackend.domain.card.dto.UpdateCardRequest
import org.study.todobackend.domain.comment.dto.AddCommentRequest
import org.study.todobackend.domain.comment.dto.CommentResponse
import org.study.todobackend.domain.comment.dto.UpdateCommentRequest
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

    fun addComment(todoId: Long, cardId: Long, request: AddCommentRequest): CommentResponse

    fun updateComment(todoId: Long, cardId:Long, commentId: Long, request: UpdateCommentRequest): CommentResponse

    fun removeComment(todoId: Long, cardId: Long, commentId: Long)

    fun updateCardStatus(
        todoId: Long,
        cardId: Long,
        request: UpdateCardRequest
    ): CardResponse
}