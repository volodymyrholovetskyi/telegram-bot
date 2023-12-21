package ua.vholovetskyi.telegrambot.booking.handlers;

public interface CommandHandler {

    boolean supports(String name);

    ResponseMessage handle(UserInputCommand command);
}
