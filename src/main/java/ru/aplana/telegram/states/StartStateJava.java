package ru.aplana.telegram.states;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.aplana.autotests.telegram.UserContext;
import ru.aplana.telegram.UserContextJava;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public class StartStateJava extends UserStateJava {

    private List<String> buttons = Arrays.asList("Зарегистрировать проект", /*"Подписаться на проект", */"Управление проектами");

    public StartStateJava(UserContextJava userContext, Long chatId, BiConsumer<SendMessage, Boolean> send) {
        super(userContext, chatId, send);
    }

    @Override
    public void onMessageReceived(Message message) {
        String messageText = message.getText();

        switch (messageText) {
            case "Зарегистрировать проект": userContext.changeState(new RegistrationStateJava(userContext, chatId, send));
            case "Управление проектами": userContext.changeState(new ProjectManagementStateJava(userContext, chatId, send));
            default:{

                showButtons(buttons, "Выберите вариант из предложенного списка", true);
            }
        }
    }

    @Override
    public void showFirst() {

    }
}
