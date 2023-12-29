package ua.vholovetskyi.telegrambot.booking.db;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vholovetskyi.telegrambot.booking.model.Ticket;
import ua.vholovetskyi.telegrambot.booking.model.TicketStatus;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByStatus(TicketStatus status);

}
