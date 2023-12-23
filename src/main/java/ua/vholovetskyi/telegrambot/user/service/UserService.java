package ua.vholovetskyi.telegrambot.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.vholovetskyi.telegrambot.user.db.UserRepository;
import ua.vholovetskyi.telegrambot.user.dto.RegisteredUserDto;

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
}
