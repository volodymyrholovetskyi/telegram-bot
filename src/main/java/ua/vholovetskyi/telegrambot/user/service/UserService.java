package ua.vholovetskyi.telegrambot.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.vholovetskyi.telegrambot.booking.dto.AddDateOfVisit;
import ua.vholovetskyi.telegrambot.user.db.UserRepository;
import ua.vholovetskyi.telegrambot.user.dto.RegisteredUserDto;
import ua.vholovetskyi.telegrambot.user.model.User;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public void registered(RegisteredUserDto userDto) {
        userRepository.save(userDto.toUser());
    }

    public boolean existsByChatId(Long chatId){
        return userRepository.existsByChatId(chatId);
    }

    public void addDateOfVisit(AddDateOfVisit addDateOfVisit) {
        User user = findById(addDateOfVisit.getChatId());
        user.set;
    }

    private User findById(Long chatId) {
        return userRepository.findById(chatId).orElseThrow();
    }
}
