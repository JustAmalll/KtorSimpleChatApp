package dev.amal.ktorchat.presentation.chat

import dev.amal.ktorchat.domain.model.Message

data class ChatState(
    val message: List<Message> = emptyList(),
    val isLoading: Boolean = false
)