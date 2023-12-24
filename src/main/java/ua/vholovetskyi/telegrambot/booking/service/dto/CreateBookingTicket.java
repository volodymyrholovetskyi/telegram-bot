package ua.vholovetskyi.telegrambot.booking.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreateBookingTicket {

    private Long chatId;
    private LocalDateTime dateOfVisit;

}
