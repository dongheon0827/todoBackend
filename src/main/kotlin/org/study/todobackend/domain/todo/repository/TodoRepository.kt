package org.study.todobackend.domain.todo.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.study.todobackend.domain.todo.model.TodoModel

interface TodoRepository : JpaRepository<TodoModel, Long> {}