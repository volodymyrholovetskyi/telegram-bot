package ua.vholovetskyi.telegrambot.booking.handlers;

public interface CommandHandler {

    boolean supports(String command);

    ResponseMessage handle(UserInput command);
}
