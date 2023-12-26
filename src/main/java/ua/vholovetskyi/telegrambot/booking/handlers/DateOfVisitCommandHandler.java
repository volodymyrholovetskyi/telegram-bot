package ua.vholovetskyi.telegrambot.booking.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ua.vholovetskyi.telegrambot.booking.handlers.utils.Answer;
import ua.vholovetskyi.telegrambot.booking.service.BookingTicketService;
import ua.vholovetskyi.telegrambot.booking.service.dto.CreateTicketDto;
import ua.vholovetskyi.telegrambot.booking.validator.DateValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateOfVisitCommandHandler extends BaseCommandHandler {

    public static final String COMMAND_NAME = "/date_of_visit";
    private final BookingTicketService ticketService;
    public DateOfVisitCommandHandler(BookingTicketService ticketService) {
        this.ticketService=ticketService;
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public SendMessage handle(UserInput userInput) {
        final var validator = new DateValidator();
        if (userInput.getValue() == null || !validator.isValid(userInput.getValue())) {
            final var answer = String.format(Answer.MESSAGE_DATE_INVALID, userInput.getValue());
            return new SendMessage(String.valueOf(userInput.getChatId()), answer);
        }
        final LocalDateTime parseDateTime = parseLocalDateTime(userInput);
        if (isAfter(parseDateTime)){
            return new SendMessage(String.valueOf(userInput.getChatId()), Answer.MESSAGE_PAST_DATE);
        }
        ticketService.createTicket(new CreateTicketDto(userInput.getChatId(),parseDateTime));
        return new SendMessage(String.valueOf(userInput.getChatId()), Answer.MESSAGE_DATE_VALID);
    }

    private static LocalDateTime parseLocalDateTime(UserInput userInput) {
        final var dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(userInput.getValue(), dateTimeFormat);
    }

    private boolean isAfter(LocalDateTime dateTime) {
        final var dateTimeNow = LocalDateTime.now();
        return dateTimeNow.isAfter(dateTime);
    }
}
