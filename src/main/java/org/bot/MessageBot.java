package org.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageBot extends TelegramLongPollingBot {
    public MessageBot(String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        final SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText("answer");
        try {
            execute(message);
        } catch (TelegramApiException e) {
            //throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return "test_q1q1q1_bot";
    }
}
