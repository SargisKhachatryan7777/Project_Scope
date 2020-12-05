package main.java.com.example.service;

import com.example.model.User;
import com.example.model.UserType;
import com.example.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public void save(User user) {
        this.userRepository.save(user);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email) {
        Optional<User> byEmail = this.userRepository.findByEmail(email);
        return byEmail;
    }

    public List<User> findByUsertype(UserType userType) {
        return this.userRepository.findAllByUserType(userType);
    }

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
