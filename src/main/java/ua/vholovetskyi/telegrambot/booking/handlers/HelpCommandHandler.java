package ua.vholovetskyi.telegrambot.booking.handlers;

public class HelpCommandHandler extends BaseCommandHandler {

    public static final String COMMAND_NAME = "/help";

    @Override
    public ResponseMessage handle(UserInput command) {
        String answer = String.format("To book a ticket, execute the following two commands:" +
                "%n/contact - click the button to submit your phone number" +
                "%n/date_of_visit - dd/mm/yyyy hh:mm");
        return new ResponseMessage(command.getChatId(), false, answer);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
