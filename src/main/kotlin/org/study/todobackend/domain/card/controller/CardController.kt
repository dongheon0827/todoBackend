package org.study.todobackend.domain.card.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.study.todobackend.domain.card.dto.AddCardRequest
import org.study.todobackend.domain.card.dto.CardResponse
import org.study.todobackend.domain.card.dto.UpdateCardRequest
import org.study.todobackend.domain.todo.service.TodoService

@RequestMapping("/todos/{todoId}/cards")
@RestController
class CardController(private val todoService: TodoService) {

    @GetMapping
    fun getAllCards(@PathVariable todoId: Long): ResponseEntity<List<CardResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getAllCardList(todoId))
    }

    @GetMapping("/{cardId}")
    fun getCard(@PathVariable todoId: Long, @PathVariable cardId: Long): ResponseEntity<CardResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getCardById(todoId, cardId))
    }

    @PostMapping
    fun addCard(
        @PathVariable todoId: Long,
        @RequestBody addCardRequest: AddCardRequest
    ): ResponseEntity<CardResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.addCard(todoId, addCardRequest))
    }

    @PutMapping("/{cardId}")
    fun updateCard(
        @PathVariable todoId: Long,
        @PathVariable cardId: Long,
        @RequestBody updateCardRequest: UpdateCardRequest
    ): ResponseEntity<CardResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.updateCard(todoId, cardId, updateCardRequest))
    }

    @DeleteMapping("/{cardId}")
    fun removeCard(@PathVariable todoId: Long, @PathVariable cardId: Long): ResponseEntity<Unit> {
        todoService.removeCard(todoId, cardId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}