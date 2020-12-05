package main.java.com.example.repository;

import com.example.model.User;
import com.example.model.UserType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    List<User> findAllByUserType(UserType userType);
}
