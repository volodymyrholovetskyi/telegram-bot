package ua.vholovetskyi.telegrambot.booking.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface CommandHandler {

    boolean supports(String command);

    SendMessage handle(UserInput command);
}
