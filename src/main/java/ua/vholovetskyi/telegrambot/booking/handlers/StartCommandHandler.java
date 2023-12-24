package ua.vholovetskyi.telegrambot.booking.handlers;

import com.vdurmont.emoji.EmojiParser;
import ua.vholovetskyi.telegrambot.booking.handlers.utils.Answer;
import ua.vholovetskyi.telegrambot.user.dto.RegisteredUserDto;
import ua.vholovetskyi.telegrambot.user.service.UserService;

import java.sql.Timestamp;

public class StartCommandHandler extends BaseCommandHandler {

    public static final String COMMAND_NAME = "/start";
    private final UserService userService;
    public StartCommandHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseMessage handle(UserInput input) {
        final var answer = String.format(Answer.ANSWER_START_COMMAND, input.getFirstName(), Answer.BLUSH);
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
