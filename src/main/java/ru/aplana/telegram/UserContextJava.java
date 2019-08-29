package ru.aplana.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.aplana.telegram.states.StartStateJava;
import ru.aplana.telegram.states.UserStateJava;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class UserContextJava {

    private final Long chatId;
    private Integer lastMessageId;

    private final BiConsumer<SendMessage, Boolean> sendMessage;

    private UserStateJava userState;

    public UserContextJava(Long chatId, Consumer<SendMessage> sendMessage) {
        this.sendMessage = (sm, reply) -> {
            if (reply) {
                sm.setReplyToMessageId(lastMessageId);
            }
            sendMessage.accept(sm);
        };
        this.chatId = chatId;
        changeState(new StartStateJava(this, chatId, this.sendMessage));
    }


    public void changeState(UserStateJava newUserState) {
        this.userState = newUserState;
        userState.showFirst();
        System.out.println("Класс: " + newUserState.getClass());
    }

    public void onMessageReceived(Message message) {
        //  Тоже не очень решение, но лучше чем, если случится
        //  коллизия и будет делаться реплай на сообщение другого пользователя
        lastMessageId = message.getMessageId();
        userState.onMessageReceived(message);
    }
}
