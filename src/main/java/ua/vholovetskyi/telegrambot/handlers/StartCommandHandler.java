package ua.vholovetskyi.telegrambot.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StartCommandHandler extends BaseCommandHandler {

    public static final String COMMAND_NAME = "/start";

    @Override
    public SendMessage handle(UserInput userInput) {
        final var answer = String.format(HandlerMessage.MESSAGE_START_COMMAND, userInput.getFirstName(), HandlerMessage.BLUSH);
        return new SendMessage(String.valueOf(userInput.getChatId()), answer);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
