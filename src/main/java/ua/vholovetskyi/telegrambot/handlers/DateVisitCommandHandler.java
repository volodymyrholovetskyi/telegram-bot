package ua.vholovetskyi.telegrambot.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ua.vholovetskyi.telegrambot.booking.model.Ticket;
import ua.vholovetskyi.telegrambot.booking.service.BookingTicketService;
import ua.vholovetskyi.telegrambot.shared.excetpion.ResourcesNotFound;
import ua.vholovetskyi.telegrambot.shared.validator.DateValidator;

public class DateVisitCommandHandler extends BaseCommandHandler {

    public static final String COMMAND_NAME = "/date_of_visit";
    private final BookingTicketService ticketService;

    public DateVisitCommandHandler(BookingTicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public SendMessage handle(UserInput userInput) {
        final var validator = new DateValidator();
        if (userInput.getValue() == null || !validator.isValid(userInput.getValue())) {
            final var answer = String.format(HandlerMessage.MESSAGE_DATE_INVALID, userInput.getValue());
            return new SendMessage(String.valueOf(userInput.getChatId()), answer);
        }
        final var ticket = new Ticket(userInput.getChatId(), userInput.getValue());
        if (ticket.isAfter()) {
            return new SendMessage(String.valueOf(userInput.getChatId()), HandlerMessage.MESSAGE_PAST_DATE);
        }
        try {
            ticketService.createTicket(ticket);
            return new SendMessage(String.valueOf(userInput.getChatId()), HandlerMessage.MESSAGE_DATE_VALID);
        } catch (ResourcesNotFound e) {
            return new SendMessage(String.valueOf(userInput.getChatId()), HandlerMessage.MESSAGE_USER_IS_NOT_REGISTER);
        }
    }
}
