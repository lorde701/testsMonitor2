package ru.aplana.autotests.telegram.states

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.aplana.autotests.telegram.UserContext
import ru.aplana.controller.ProjectController
import ru.aplana.telegram.states.StartState


class ProjectManagementState(userContext: UserContext,
                             chatId: Long?,
                             send: (SendMessage, Boolean) -> Unit)
    : UserState(userContext, chatId, send) {

    @Autowired
    lateinit var projectController: ProjectController

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
        val textButtons = projectController.all.asSequence().map { it -> "/$it" }.joinToString { "\n" }
        showButtons(buttons, "Управление проектами. Выберите один из вариантов\n $textButtons", false)
    }

}