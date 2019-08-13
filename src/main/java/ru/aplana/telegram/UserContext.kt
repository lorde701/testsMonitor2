package ru.aplana.autotests.telegram

import lombok.Getter
import lombok.Setter
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.aplana.autotests.telegram.states.StartState
import ru.aplana.autotests.telegram.states.UserState

import java.util.function.BiConsumer
import java.util.function.Consumer

class UserContext(private val chatId: Long?, sendMessage: (SendMessage) -> Unit) {
    private var lastMessageId: Int? = null

    var projects: MutableList<String>? = null

    private var userState: UserState? = null

    private var sendMessage: (SendMessage, Boolean) -> Unit

    init {
        this.sendMessage = { sm, reply ->
            if (reply) {
                sm.setReplyToMessageId(lastMessageId)
            }
            sendMessage(sm)
        }
        changeState(StartState(this, chatId, this.sendMessage))
    }

    fun changeState(newUserState: UserState) {
        this.userState = newUserState
        userState!!.showFirst()
        println("Класс: " + newUserState.javaClass)
    }

    fun onMessageReceived(message: Message) {
        //  Тоже не очень решение, но лучше чем, если случится
        //  коллизия и будет делаться реплай на сообщение другого пользователя
        lastMessageId = message.messageId
        userState!!.onMessageReceived(message)
    }
}
