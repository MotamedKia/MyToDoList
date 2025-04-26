package com.example.mytodolist.logic

data class ToDoItems(
    val name: String,
    val description: String,
    val importance: Boolean,
    var done: Boolean
)