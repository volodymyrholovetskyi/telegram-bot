package ua.vholovetskyi.telegrambot.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.vholovetskyi.telegrambot.shared.excetpion.ResourcesNotFound;
import ua.vholovetskyi.telegrambot.user.db.UserRepository;
import ua.vholovetskyi.telegrambot.user.service.dto.RegisteredUserDto;
import ua.vholovetskyi.telegrambot.user.model.User;
import ua.vholovetskyi.telegrambot.user.model.UserRole;

import java.util.List;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2023-12-24
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long chatId) {
        return userRepository.findById(chatId)
                .orElseThrow(() -> new ResourcesNotFound(chatId));
    }

    public void registered(RegisteredUserDto userDto) {
        userRepository.save(userDto.toUser());
        log.info("Registered user with ID: " + userDto.getChatId());
    }

    public boolean existsByChatId(Long chatId) {
        return userRepository.existsByChatId(chatId);
    }

    public List<User> findByRole(UserRole userRole) {
        return userRepository.findByRole(userRole);
    }

}
