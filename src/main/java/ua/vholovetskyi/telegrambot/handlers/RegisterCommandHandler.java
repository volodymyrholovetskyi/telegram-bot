package ua.vholovetskyi.telegrambot.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ua.vholovetskyi.telegrambot.user.service.UserService;

public class RegisterCommandHandler extends BaseCommandHandler {

    public static final String COMMAND_NAME = "/register";
    private final UserService userService;

    public RegisterCommandHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public SendMessage handle(UserInput userInput) {
        boolean existsUser = userService.existsByChatId(userInput.getChatId());
        if (existsUser) {
            return new SendMessage(String.valueOf(userInput.getChatId()), HandlerMessage.MESSAGE_REGISTERED);
        }
        return ButtonSendContact.buttonSendContact(userInput);
    }
}
