package ua.vholovetskyi.telegrambot.booking.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ua.vholovetskyi.telegrambot.booking.handlers.utils.Answer;
import ua.vholovetskyi.telegrambot.user.service.UserService;

public class StartCommandHandler extends BaseCommandHandler {

    public static final String COMMAND_NAME = "/start";
    private final UserService userService;

    public StartCommandHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public SendMessage handle(UserInput userInput) {
        final var answer = String.format(Answer.MESSAGE_START_COMMAND, userInput.getFirstName(), Answer.BLUSH);
        return new SendMessage(String.valueOf(userInput.getChatId()), answer);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
