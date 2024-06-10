package org.study.todobackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
class TodoBackendApplication

fun main(args: Array<String>) {
    runApplication<TodoBackendApplication>(*args)
}
