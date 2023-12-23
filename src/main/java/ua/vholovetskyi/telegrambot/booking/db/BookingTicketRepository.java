package ua.vholovetskyi.telegrambot.booking.db;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vholovetskyi.telegrambot.booking.model.BookingTicket;

public interface BookingTicketRepository extends JpaRepository<BookingTicket, Long> {
}
