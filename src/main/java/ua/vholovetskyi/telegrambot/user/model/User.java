package ua.vholovetskyi.telegrambot.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.vholovetskyi.telegrambot.booking.model.Ticket;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    private Long chatId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private Timestamp registeredAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Ticket> tickets = new ArrayList<>();


    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void addTicker(Ticket ticket) {
        if (ticket != null) {
            tickets.add(ticket);
        }
    }
}
