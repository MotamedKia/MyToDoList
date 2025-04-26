package com.example.mytodolist.logic

fun SplitString(input: String, segmentSize: Int): String {
    if (input.isEmpty() || segmentSize <= 0) return input

    val result = StringBuilder()
    var index = 0

    while (index < input.length) {
        val end = (index + segmentSize).coerceAtMost(input.length)
        result.append(input.substring(index, end)).append("\n\n\n")
        index = end
    }

    return result.toString().trim()
}