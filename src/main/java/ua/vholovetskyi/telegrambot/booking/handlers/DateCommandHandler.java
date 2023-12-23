package ua.vholovetskyi.telegrambot.booking.handlers;

import ua.vholovetskyi.telegrambot.booking.validator.DateValidator;
import ua.vholovetskyi.telegrambot.booking.validator.PhoneNumberValidator;
import ua.vholovetskyi.telegrambot.booking.validator.Validator;

public class DateCommandHandler extends BaseCommandHandler{

    public static final String COMMAND_NAME = "/date_of_visit";
    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public ResponseMessage handle(UserInput command) {

        String answerError = "Date is invalid";
        String answer = "Date is valid!!!";
        final var validator = new DateValidator();
        if (!validator.isValid(command.getValue())) {
            return new ResponseMessage(command.getChatId(), true, answerError);
        }
        return new ResponseMessage(command.getChatId(), false, answer);
    }
}
