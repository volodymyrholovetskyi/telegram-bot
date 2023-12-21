package ua.vholovetskyi.telegrambot.booking.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateBookingTicket {

    private String firstName;
    private String lastName;
    private String phone;
    private LocalDateTime dateOfVisit;

    public CreateBookingTicket(String text) {
        String[] array = text.split("\\s");
        if (array.length != 4) {
            throw new IllegalArgumentException("Not enough parameters");
        }

    }

}
