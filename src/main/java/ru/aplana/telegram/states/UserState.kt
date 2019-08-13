package ru.aplana.autotests.telegram.states

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import ru.aplana.autotests.telegram.UserContext

import java.util.ArrayList
import java.util.function.BiConsumer

abstract class UserState protected constructor(protected val userContext: UserContext,
                                               protected val chatId: Long?,
                                               protected var send: (SendMessage, Boolean) -> Unit) {
    abstract fun onMessageReceived(message: Message)

    abstract fun showFirst()


    protected fun sendMessage(text: String, withReply: Boolean) {
        val sm = SendMessage()
        sm.setChatId(chatId!!)
        sm.text = text
        send(sm, withReply)
    }

    protected fun showButtons(buttonNames: List<String>, comment: String, withReply: Boolean) {
        val replyKeyboardMarkup = ReplyKeyboardMarkup()

        replyKeyboardMarkup.selective = true
        replyKeyboardMarkup.resizeKeyboard = true
        replyKeyboardMarkup.oneTimeKeyboard = false

        val keyboardRow = ArrayList<KeyboardRow>()
        for (button in buttonNames) {
            val row = KeyboardRow()
            row.add(KeyboardButton(button))
            keyboardRow.add(row)
        }
        replyKeyboardMarkup.keyboard = keyboardRow

        val sm = SendMessage()
        sm.setChatId(chatId!!)
        sm.replyMarkup = replyKeyboardMarkup
        sm.text = comment

        send(sm, withReply)
    }
}
