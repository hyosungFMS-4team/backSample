package com.example.TravelPlanner.service.login;

import com.example.TravelPlanner.domain.User;
import com.example.TravelPlanner.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;


//나중에 파일 다 지우자
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void printAllUsersToConsole() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
