package ua.vholovetskyi.telegrambot.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.vholovetskyi.telegrambot.booking.db.BookingTicketRepository;
import ua.vholovetskyi.telegrambot.booking.service.dto.CreateBookingTicket;

@Component
@RequiredArgsConstructor
public class BookingTicketService {

    private final BookingTicketRepository ticketRepository;

    public void saveBookingTicket(CreateBookingTicket bookingTicket) {

    }
}
