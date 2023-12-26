package ua.vholovetskyi.telegrambot.booking.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ua.vholovetskyi.telegrambot.booking.handlers.utils.Answer;
import ua.vholovetskyi.telegrambot.booking.validator.PhoneNumberValidator;
import ua.vholovetskyi.telegrambot.user.dto.UpdatePhoneUser;
import ua.vholovetskyi.telegrambot.user.service.UserService;

public class PhoneNumberCommandHandler extends BaseCommandHandler {
    public static final String COMMAND_NAME = "/myContact";
    private final UserService userService;
    public PhoneNumberCommandHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public SendMessage handle(UserInput userInput) {
        final var validator = new PhoneNumberValidator();
        if (userInput.getValue() == null || validator.isValid(userInput.getValue())) {
            final var error = String.format(Answer.MESSAGE_CONTACT_INVALID, userInput.getValue());
            return new SendMessage(String.valueOf(userInput.getChatId()), error);
        }
        userService.updatePhoneNumber(new UpdatePhoneUser(userInput.getChatId(), userInput.getValue()));
        return new SendMessage(String.valueOf(userInput.getChatId()), Answer.MESSAGE_CONTACT_VALID);
    }


    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
