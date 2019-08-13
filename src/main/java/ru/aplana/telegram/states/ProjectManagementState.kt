package ru.aplana.autotests.telegram.states

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.aplana.autotests.telegram.UserContext

class ProjectManagementState(userContext: UserContext,
                             chatId: Long?,
                             send: (SendMessage, Boolean) -> Unit)
    : UserState(userContext, chatId, send) {

    override fun onMessageReceived(message: Message) {
        val messageText = message.text


        when (messageText) {
            "Назад" -> userContext.changeState(StartState(userContext, chatId, send))
            else -> {
                sendMessage("Выберите вариант из предложенного списка", true)
            }
        }
    }

    override fun showFirst() {
        val buttons = mutableListOf("Назад")
        val textButtons = userContext.projects?.joinToString("\n")
        showButtons(buttons, "Управление проектами. Выберите вариант\n $textButtons", false)
    }

}