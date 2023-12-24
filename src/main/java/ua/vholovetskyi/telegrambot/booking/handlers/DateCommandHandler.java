package ua.vholovetskyi.telegrambot.booking.handlers;

import ua.vholovetskyi.telegrambot.booking.handlers.utils.Answer;
import ua.vholovetskyi.telegrambot.booking.validator.DateValidator;
import ua.vholovetskyi.telegrambot.user.service.UserService;

public class DateCommandHandler extends BaseCommandHandler {

    public static final String COMMAND_NAME = "/date_of_visit";
    private final UserService userService;
    public DateCommandHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public ResponseMessage handle(UserInput command) {
        final var validator = new DateValidator();
        if (command.getValue() == null || !validator.isValid(command.getValue())) {
            final var answer = String.format(Answer.ANSWER_DATE_INVALID, command.getValue());
            return new ResponseMessage(command.getChatId(), true, answer);
        }
        return new ResponseMessage(command.getChatId(), false, Answer.ANSWER_DATE_VALID);
    }
}
