package org.study.todobackend.domain.comment.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.study.todobackend.domain.comment.dto.*
import org.study.todobackend.domain.todo.service.TodoService

@RequestMapping("/todos/{todoId}/cards/{cardId}/comments")
@RestController
class CommentController(private val todoService: TodoService) {

    @PostMapping
    fun addComment(
        @PathVariable todoId: Long,
        @PathVariable cardId: Long,
        @RequestBody addCommentRequest: AddCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.addComment(todoId, cardId, addCommentRequest))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable todoId: Long,
        @PathVariable cardId: Long,
        @PathVariable commentId: Long,
        @RequestBody updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(todoService.updateComment(todoId, cardId, commentId, updateCommentRequest))
    }

    @DeleteMapping("/{commentId}")
    fun removeComment(
        @PathVariable todoId: Long,
        @PathVariable cardId: Long,
        @PathVariable commentId: Long
    ): ResponseEntity<Unit> {
        todoService.removeComment(todoId, cardId, commentId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}