package ua.vholovetskyi.telegrambot.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AddDateOfVisit {

    private LocalDateTime dareOfVisit;
}
