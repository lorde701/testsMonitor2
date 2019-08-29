package ru.aplana.telegram.states;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.aplana.controller.ProjectController;
import ru.aplana.request.AddProjectRequest;
import ru.aplana.telegram.UserContextJava;

import java.util.function.BiConsumer;

@Component
public class RegistrationStateJava extends UserStateJava {

    private ProjectController projectController = new ProjectController();

    protected RegistrationStateJava(UserContextJava userContext, Long chatId, BiConsumer<SendMessage, Boolean> send) {
        super(userContext, chatId, send);
    }

    @Override
    public void onMessageReceived(Message message) {

        String messageText = message.getText();

        if (messageText.equals("Назад")) {
            userContext.changeState(new StartStateJava(userContext, chatId, send));
        } else {
            projectController.add(new AddProjectRequest(messageText));
            sendMessage("проект <$messageText> добавлен в базу.", true);
        }
    }

    @Override
    public void showFirst() {

    }
}
