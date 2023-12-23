package ua.vholovetskyi.telegrambot.booking.handlers;

import com.vdurmont.emoji.EmojiParser;
import ua.vholovetskyi.telegrambot.user.dto.RegisteredUserDto;
import ua.vholovetskyi.telegrambot.user.service.UserService;

import java.sql.Timestamp;

public class StartCommandHandler extends BaseCommandHandler {

    private final UserService userService;

    public StartCommandHandler(UserService userService) {
        this.userService = userService;
    }
    public static final String COMMAND_NAME = "/start";

    @Override
    public ResponseMessage handle(UserInput input) {
        final var blush = EmojiParser.parseToUnicode(":blush:");
        final var answer = String.format("Hello, %s %s%nWelcome to our bot!" +
                "%nUse the /help command to view a list of commands.", input.getFirstName(), blush);
        if (!userService.existsByChatId(input.getChatId())) {
            userService.registered(new RegisteredUserDto(
                    input.getChatId(),
                    input.getFirstName(),
                    input.getLastName(),
                    new Timestamp(input.getRegisteredAt().getTime())));
        }
        return new ResponseMessage(input.getChatId(), false, answer);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
