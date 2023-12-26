package ua.vholovetskyi.telegrambot.booking.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ua.vholovetskyi.telegrambot.booking.handlers.utils.Answer;

public class HelpCommandHandler extends BaseCommandHandler {

    public static final String COMMAND_NAME = "/help";

    @Override
    public SendMessage handle(UserInput userInput) {
        String answer = String.format(Answer.MESSAGE_HELP_COMMAND);
        return new SendMessage(String.valueOf(userInput.getChatId()), answer);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
