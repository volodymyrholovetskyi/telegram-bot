package ua.vholovetskyi.telegrambot.booking.handlers;

import java.util.Locale;

public class StartCommandHandler extends BaseCommandHandler{

    public static final String COMMAND_NAME = "/start";

    @Override
    public ResponseMessage handle(UserInputCommand command) {
        final String formatMessage = String.format(Locale.US, "Hello %s!%nWelcome to the Ticket Booking Service." +
                "%nTo use the chat correctly, use the /help command.", command.getUserName());
        return new ResponseMessage(command.getId(), formatMessage);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
