package ua.vholovetskyi.telegrambot.booking.handlers;

import ua.vholovetskyi.telegrambot.booking.handlers.utils.Answer;

public class HelpCommandHandler extends BaseCommandHandler {

    public static final String COMMAND_NAME = "/help";

    @Override
    public ResponseMessage handle(UserInput command) {
        String answer = String.format(Answer.ANSWER_HELP_COMMAND);
        return new ResponseMessage(command.getChatId(), false, answer);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
