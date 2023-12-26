package ua.vholovetskyi.telegrambot.booking.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.vholovetskyi.telegrambot.booking.service.dto.CreateTicketDto;
import ua.vholovetskyi.telegrambot.user.model.User;
import ua.vholovetskyi.telegrambot.user.service.UserService;

@Component
@RequiredArgsConstructor
public class BookingTicketService {
    private final UserService userService;

    @Transactional
    public void createTicket(CreateTicketDto ticketDto) {
        User user = userService.findById(ticketDto.getChatId());
        user.addTicker(ticketDto.toTicker());
    }
}
