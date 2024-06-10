package org.study.todobackend.domain.todo.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.study.todobackend.domain.card.dto.AddCardRequest
import org.study.todobackend.domain.card.dto.CardResponse
import org.study.todobackend.domain.card.dto.UpdateCardRequest
import org.study.todobackend.domain.card.model.CardModel
import org.study.todobackend.domain.card.model.toResponse
import org.study.todobackend.domain.card.repository.CardRepository
import org.study.todobackend.domain.comment.dto.AddCommentRequest
import org.study.todobackend.domain.comment.dto.CommentResponse
import org.study.todobackend.domain.comment.dto.UpdateCommentRequest
import org.study.todobackend.domain.comment.model.CommentModel
import org.study.todobackend.domain.comment.model.toResponse
import org.study.todobackend.domain.comment.repository.CommentRepository
import org.study.todobackend.domain.exception.ModelNotFoundException
import org.study.todobackend.domain.todo.dto.CreateTodoRequest
import org.study.todobackend.domain.todo.dto.TodoResponse
import org.study.todobackend.domain.todo.model.TodoModel
import org.study.todobackend.domain.todo.model.toResponse
import org.study.todobackend.domain.todo.repository.TodoRepository

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val cardRepository: CardRepository,
    private val commentRepository: CommentRepository,
) {
    fun getAllTodoList(): List<TodoResponse> {
        return todoRepository.findAll().map { it.toResponse() }
    }

    fun getTodoById(todoId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        return todo.toResponse()
    }

    @Transactional
    fun createTodo(request: CreateTodoRequest): TodoResponse {
        return todoRepository.save(
            TodoModel(
                title = request.title, date = request.date, name = request.name
            )
        ).toResponse()
    }

    @Transactional
    fun deleteTodo(todoId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        todoRepository.delete(todo)
    }

    fun getAllCardList(todoId: Long): List<CardResponse> {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val comments = commentRepository.findAllByTodoId(todoId).map { it.toResponse() }
        return todo.cards.map { it.toResponse() }
    }

    fun getCardById(todoId: Long, cardId: Long): CardResponse {
        val card = cardRepository.findByTodoIdAndId(todoId, cardId) ?: throw ModelNotFoundException("Card", cardId)
        return card.toResponse()
    }

    @Transactional
    fun addCard(todoId: Long, request: AddCardRequest): CardResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val card = CardModel(
            title = request.title,
            description = request.description,
            date = request.date,
            name = request.name,
            status = request.status,
            todo = todo
        )
        cardRepository.save(card)
        todo.addCard(card)
        return card.toResponse()
    }

    @Transactional
    fun updateCard(todoId: Long, cardId: Long, request: UpdateCardRequest): CardResponse {
        val card = cardRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Card", cardId)

        val (title, description, name) = request
        card.title = title
        card.description = description
        card.name = name

        return cardRepository.save(card).toResponse()
    }

    @Transactional
    fun removeCard(todoId: Long, cardId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val card = cardRepository.findByTodoIdAndId(todoId, cardId) ?: throw ModelNotFoundException("Card", cardId)

        todo.removeCard(card)
        todoRepository.save(todo)
    }

    fun addComment(todoId: Long, cardId: Long, request: AddCommentRequest): CommentResponse {
        val card = cardRepository.findByTodoIdAndId(todoId, cardId) ?: throw ModelNotFoundException("Card", cardId)
        val comment = CommentModel(
            name = request.name,
            password = request.password,
            comments = request.comments,
            card = card
        )
        commentRepository.save(comment)
        card.addComment(comment)
        return comment.toResponse()
    }

    @Transactional
    fun updateComment(
        todoId: Long,
        cardId: Long,
        commentId: Long,
        request: UpdateCommentRequest
    ): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        val (comments) = request
        comment.comments = comments

        return commentRepository.save(comment).toResponse()
    }

    fun removeComment(todoId: Long, cardId: Long, commentId: Long) {
        val card = cardRepository.findByTodoIdAndId(todoId, cardId) ?: throw ModelNotFoundException("Card", cardId)
        val comment = commentRepository.findByCardIdAndId(cardId, commentId)
            ?: throw ModelNotFoundException("Comment", commentId)

        card.removeComment(comment)
        cardRepository.save(card)
    }

    fun updateCardStatus(todoId: Long, cardId: Long, request: UpdateCardRequest): CardResponse {
        val card = cardRepository.findByTodoIdAndId(todoId, cardId) ?: throw ModelNotFoundException("Card", cardId)

        if (card.status) {
            throw IllegalStateException("Card '$cardId' is already done")
        }

        card.complete()
        return cardRepository.save(card).toResponse()
    }
}