package ua.vholovetskyi.telegrambot.booking.handlers;

import ua.vholovetskyi.telegrambot.booking.dto.AddDateOfVisit;
import ua.vholovetskyi.telegrambot.booking.handlers.utils.Answer;
import ua.vholovetskyi.telegrambot.booking.service.BookingTicketService;
import ua.vholovetskyi.telegrambot.booking.service.dto.CreateBookingTicket;
import ua.vholovetskyi.telegrambot.booking.validator.PhoneNumberValidator;
import ua.vholovetskyi.telegrambot.user.service.UserService;

import java.time.LocalDateTime;

public class ContactCommandHandler extends BaseCommandHandler {
    public static final String COMMAND_NAME = "/myContact";
    private final BookingTicketService ticketService;
    public ContactCommandHandler(BookingTicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public ResponseMessage handle(UserInput userInput) {
        final var validator = new PhoneNumberValidator();
        if (userInput.getValue() == null || validator.isValid(userInput.getValue())) {
            final var error = String.format(Answer.ANSWER_CONTACT_INVALID, userInput.getValue());
            return new ResponseMessage(userInput.getChatId(), true, error);
        }
        ticketService.saveBookingTicket(new CreateBookingTicket(userInput.getChatId(), LocalDateTime.parse(userInput.getValue())));
        return new ResponseMessage(userInput.getChatId(), false, Answer.ANSWER_CONTACT_VALID);
    }


    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
