package ua.vholovetskyi.telegrambot.booking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.vholovetskyi.telegrambot.shared.utils.DateTimeFormatters;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-23
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;
    @Column(name = "user_id")
    private Long userId;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Column(unique = true)
    private LocalDateTime dateOfVisit;

    public Ticket(Long userId, String dateOfVisit) {
        this.userId = userId;
        this.dateOfVisit = DateTimeFormatters.localDateTimeStringParser(dateOfVisit);
        this.status = TicketStatus.BOOKED;
    }

    public boolean isAfter() {
        final var dateTimeNow = LocalDateTime.now();
        return dateTimeNow.isAfter(dateOfVisit);
    }

    public void updateStatus(TicketStatus ticketStatus) {
        this.status = ticketStatus;
    }
}
