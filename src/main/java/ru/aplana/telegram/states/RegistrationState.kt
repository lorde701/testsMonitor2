package ru.aplana.telegram.states

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.aplana.autotests.telegram.UserContext
import ru.aplana.autotests.telegram.states.UserState
import ru.aplana.controller.ProjectController
import ru.aplana.request.AddProjectRequest

@EnableTransactionManagement
class RegistrationState(userContext: UserContext, chatId: Long?, send: (SendMessage, Boolean) -> Unit)
    : UserState(userContext, chatId, send) {

    @Autowired
    lateinit var projectController: ProjectController
//    private var projectController = ProjectController()


    override fun onMessageReceived(message: Message) {
        val messageText = message.text

        if (messageText == "Назад") {
            userContext.changeState(StartState(userContext, chatId, send))
        } else {
            projectController.add(AddProjectRequest(messageText))
            sendMessage("проект <$messageText> добавлен в базу.", true)
        }

    }

    override fun showFirst() {
        sendMessage("""Созданные проекты:
            |${projectController.all.joinToString { "\n" }}
            |
        """.trimMargin(), false)
        val buttons = mutableListOf("Назад")
        showButtons(buttons, "Введите название нового проекта или вернитесь в предыдущее меню", false)
    }

}