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
import org.study.todobackend.domain.exception.ModelNotFoundException
import org.study.todobackend.domain.todo.dto.CreateTodoRequest
import org.study.todobackend.domain.todo.dto.TodoResponse
import org.study.todobackend.domain.todo.model.TodoModel
import org.study.todobackend.domain.todo.model.toResponse
import org.study.todobackend.domain.todo.repository.TodoRepository

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,
    private val cardRepository: CardRepository,
) : TodoService {
    override fun getAllTodoList(): List<TodoResponse> {
        return todoRepository.findAll().map { it.toResponse() }
    }

    override fun getTodoById(todoId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        return todo.toResponse()
    }

    @Transactional
    override fun createTodo(request: CreateTodoRequest): TodoResponse {
        return todoRepository.save(
            TodoModel(
                title = request.title, date = request.date, name = request.name
            )
        ).toResponse()
    }

    @Transactional
    override fun deleteTodo(todoId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        todoRepository.delete(todo)
    }

    override fun getAllCardList(todoId: Long): List<CardResponse> {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        return todo.cards.map { it.toResponse() }
    }

    override fun getCardById(todoId: Long, cardId: Long): CardResponse {
        val card = cardRepository.findByTodoIdAndId(todoId, cardId) ?: throw ModelNotFoundException("Card", cardId)
        return card.toResponse()
    }

    @Transactional
    override fun addCard(todoId: Long, request: AddCardRequest): CardResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val card = CardModel(
            title = request.title,
            description = request.description,
            date = request.date,
            name = request.name,
            todo = todo
        )
        cardRepository.save(card)
        todo.addCard(card)
        return card.toResponse()
    }

    @Transactional
    override fun updateCard(todoId: Long, cardId: Long, request: UpdateCardRequest): CardResponse {
        val card = cardRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Card", cardId)

        val (title, description, name) = request
        card.title = title
        card.description = description
        card.name = name

        return cardRepository.save(card).toResponse()
    }

    @Transactional
    override fun removeCard(todoId: Long, cardId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val card = cardRepository.findByTodoIdAndId(todoId, cardId) ?: throw ModelNotFoundException("Card", cardId)

        todo.removeCard(card)
        todoRepository.save(todo)
    }
}