package ua.vholovetskyi.telegrambot.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.vholovetskyi.telegrambot.booking.model.BookingTicket;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_bot")
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;
    private String firstName;
    private String lastName;
    private String phone;
    private Timestamp registeredAt;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<BookingTicket> bookingTicket = new ArrayList<>();

}
