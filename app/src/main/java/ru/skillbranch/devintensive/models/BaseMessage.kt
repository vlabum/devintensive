package ru.skillbranch.devintensive.models

import android.os.Message
import org.w3c.dom.Text
import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage(): String

    companion object {
        var lastId = -1
        fun makeMessage(
            from: User?,
            chat: Chat,
            date: Date = Date(),
            type: String,
            payload: Any?,
            isIncoming: Boolean = false
        ): BaseMessage {
            lastId++
            val messageType = if (type == "image") MessageType.IMAGE else MessageType.TEXT
            return when(messageType) {
                MessageType.IMAGE -> ImageMessage("$lastId", from, chat, isIncoming, date, payload as String)
                MessageType.TEXT -> TextMessage("$lastId", from, chat, isIncoming, date, payload as String)
            }
        }
    }
}

enum class MessageType {
    TEXT,
    IMAGE
}