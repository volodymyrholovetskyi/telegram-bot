package ua.vholovetskyi.telegrambot.report;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.vholovetskyi.telegrambot.booking.model.Ticket;
import ua.vholovetskyi.telegrambot.booking.model.TicketStatus;
import ua.vholovetskyi.telegrambot.booking.service.BookingTicketService;

import java.time.LocalDateTime;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-26
 */
@Component
@Slf4j
@AllArgsConstructor
public class ReportBookingScheduler {
    private final BookingTicketService ticketService;
    private final TicketReportService reportService;

    /**
     * Runs every 5 minutes and returns tickets with BOOKED status from the database.
     */
    @Transactional
    @Scheduled(cron = "${telegram.bot.scheduler.cron}")
    void run() {
        log.info("Run the scheduler: " + LocalDateTime.now());
        final var tickets = ticketService.findTicketsByStatus(TicketStatus.BOOKED);
        if (!tickets.isEmpty()) {
            for (Ticket ticket : tickets) {
                ticketService.updateStatus(ticket.getTicketId(), TicketStatus.CONFIRMED);
                reportService.generateTicket(ticket);
            }
        }
    }
}
