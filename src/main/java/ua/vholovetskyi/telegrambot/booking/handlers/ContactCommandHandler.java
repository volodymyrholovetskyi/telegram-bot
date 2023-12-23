package ua.vholovetskyi.telegrambot.booking.handlers;

import ua.vholovetskyi.telegrambot.booking.validator.PhoneNumberValidator;
import ua.vholovetskyi.telegrambot.booking.validator.Validator;

public class ContactCommandHandler extends BaseCommandHandler {

    private final ContactButton contactButton;

    public ContactCommandHandler() {
        this.contactButton = new ContactButton();
    }

    public static final String COMMAND_NAME = "/myContact";
    @Override
    public ResponseMessage handle(UserInput command) {
        String answer = "Your phone has valid form";
        String error = "Your phone has no valid form!!!";
//        contactButton.sendContactButton(command);
        Validator validator = new PhoneNumberValidator();
        if (!validator.isValid(command.getValue())) {
            return new ResponseMessage(command.getChatId(), true, error);
        }
        return new ResponseMessage(command.getChatId(), false, answer);
    }


    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
