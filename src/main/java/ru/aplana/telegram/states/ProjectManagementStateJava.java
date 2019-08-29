package ru.aplana.telegram.states;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.aplana.controller.ProjectController;
import ru.aplana.telegram.UserContextJava;

import java.util.Collections;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Component
public class ProjectManagementStateJava extends UserStateJava {

    private ProjectController projectController = new ProjectController();

    protected ProjectManagementStateJava(UserContextJava userContext, Long chatId, BiConsumer<SendMessage, Boolean> send) {
        super(userContext, chatId, send);
    }

    @Override
    public void onMessageReceived(Message message) {
        String messageText = message.getText();
        switch (messageText) {
            case "Назад":
                userContext.changeState(new StartStateJava(userContext, chatId, send));
                break;
            default:
                sendMessage("Выберите вариант из предложенного списка", true);

        }
    }

    @Override
    public void showFirst() {
        String textButtons = projectController.getAll().stream().map(it -> "/" + it + "/n").collect(Collectors.joining());
        showButtons(Collections.singletonList("Назад"), "Управление проектами. Выберите один из вариантов\n" + textButtons, false);

    }
}
