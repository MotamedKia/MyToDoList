package com.example.mytodolist.logic

data class ToDoItems(
    var name: String,
    var description: String,
    var importance: Boolean,
    var done: Boolean
)