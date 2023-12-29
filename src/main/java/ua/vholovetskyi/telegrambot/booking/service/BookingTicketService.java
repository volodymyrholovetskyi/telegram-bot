package ua.vholovetskyi.telegrambot.booking.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.vholovetskyi.telegrambot.booking.db.TicketRepository;
import ua.vholovetskyi.telegrambot.booking.model.Ticket;
import ua.vholovetskyi.telegrambot.booking.model.TicketStatus;
import ua.vholovetskyi.telegrambot.shared.excetpion.ResourcesNotFound;
import ua.vholovetskyi.telegrambot.user.service.UserService;

import java.util.List;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-26
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class BookingTicketService {
    private final UserService userService;
    private final TicketRepository ticketRepository;

    public Ticket findTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourcesNotFound(ticketId));
    }

    @Transactional
    public void createTicket(Ticket ticket) {
        final var user = userService.findById(ticket.getUserId());
        user.addTicker(ticket);
        log.info(user.getFullName() + " booked a ticket!");
    }

    @Transactional
    public void updateStatus(Long ticketId, TicketStatus ticketStatus) {
        Ticket ticket = findTicketById(ticketId);
        ticket.updateStatus(ticketStatus);
    }

    public List<Ticket> findTicketsByStatus(TicketStatus status) {
        return ticketRepository.findByStatus(status);
    }

}
