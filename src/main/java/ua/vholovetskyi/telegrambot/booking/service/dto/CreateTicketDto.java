package ua.vholovetskyi.telegrambot.booking.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.vholovetskyi.telegrambot.booking.model.Ticket;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreateTicketDto {

    private Long chatId;
    private LocalDateTime dateOfVisit;

    public Ticket toTicker() {
        return Ticket.builder()
                .user_id(chatId)
                .dateOfVisit(dateOfVisit)
                .build();
    }
}
