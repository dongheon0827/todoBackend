package org.study.todobackend.domain.todo.model

import jakarta.persistence.*
import org.study.todobackend.domain.card.model.CardModel
import org.study.todobackend.domain.todo.dto.TodoResponse

@Entity
@Table(name = "todo")
class TodoModel(
    @Column(name = "title") var title: String,
    @Column(name = "date") var date: String,
    @Column(name = "name") var name: String,

    @OneToMany(
        mappedBy = "todo", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true
    ) var cards: MutableList<CardModel> = mutableListOf(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun addCard(card: CardModel) {
        cards.add(card)
    }

    fun removeCard(card: CardModel) {
        cards.remove(card)
    }
}

fun TodoModel.toResponse(): TodoResponse {
    return TodoResponse(
        id = id!!,
        title = title,
        date = date,
        name = name
    )
}