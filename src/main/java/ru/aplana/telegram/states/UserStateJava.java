package ru.aplana.telegram.states;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.aplana.autotests.telegram.UserContext;
import ru.aplana.telegram.UserContextJava;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public abstract class UserStateJava {
    protected final UserContextJava userContext;
    protected final Long chatId;
    protected BiConsumer<SendMessage, Boolean> send;

    protected UserStateJava(UserContextJava userContext, Long chatId, BiConsumer<SendMessage, Boolean> send) {
        this.userContext = userContext;
        this.chatId = chatId;
        this.send = send;
    }


    public abstract void onMessageReceived(Message message);

    public abstract void showFirst();


    protected void sendMessage(String text, Boolean withReply) {
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText(text);
        send.accept(sm, withReply);
    }

    protected void showButtons(List<String> buttonNames, String comment, Boolean withReply) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRow = new ArrayList<>();
        for (String button : buttonNames) {
            KeyboardRow row = new KeyboardRow();
            row.add(new KeyboardButton(button));
            keyboardRow.add(row);
        }
        replyKeyboardMarkup.setKeyboard(keyboardRow);

        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setReplyMarkup(replyKeyboardMarkup);
        sm.setText(comment);

        send.accept(sm, withReply);
    }
}
