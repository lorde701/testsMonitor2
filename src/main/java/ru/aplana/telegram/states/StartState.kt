package ru.aplana.telegram.states

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.aplana.autotests.telegram.UserContext
import ru.aplana.autotests.telegram.states.ProjectManagementState
import ru.aplana.autotests.telegram.states.UserState

class StartState(userContext: UserContext, chatId: Long?, send: (SendMessage, Boolean) -> Unit) : UserState(userContext, chatId, send) {

    val buttons = mutableListOf("Зарегистрировать проект", /*"Подписаться на проект", */"Управление проектами")

    override fun onMessageReceived(message: Message) {
        val messageText = message.text

        when (messageText) {
            "Зарегистрировать проект" -> userContext.changeState(RegistrationState(userContext, chatId, send))
//            "Подписаться на проект" -> userContext.changeState(SubscribeState(userContext, chatId, send))
            "Управление проектами" -> userContext.changeState(ProjectManagementState(userContext, chatId, send))
            else -> {
//                sendMessage("Выберите вариант из предложенного списка", true)
                showButtons(buttons, "Выберите вариант из предложенного списка", true)
            }
        }

    }

    override fun showFirst() {
        showButtons(buttons, "Выберите вариант", false)
    }

}
