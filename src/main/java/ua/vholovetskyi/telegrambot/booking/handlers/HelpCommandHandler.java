package ua.vholovetskyi.telegrambot.booking.handlers;

public class HelpCommandHandler extends BaseCommandHandler {

    public static final String COMMAND_NAME = "/help";

    @Override
    public ResponseMessage handle(UserInputCommand command) {
        String answer = String.format("To book a ticket, enter the required data in the following format:" +
                "%nFirst name: Volodymyr (at least two characters);" +
                "%nLast name: Holovetskyi (at least two characters)" +
                "%nPhone number: 0987666432 (XXXYYYYYYY);" +
                "%nData of visit: 28/12/2023 10:00 (dd/mm/yyyy hh:mm).");
        return new ResponseMessage(command.getId(), answer);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
