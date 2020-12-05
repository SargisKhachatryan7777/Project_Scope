package main.java.com.example.repository;

import com.example.model.Logs;
import com.example.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Logs, Integer> {
    List<Logs> findAllByUser(User user);
}
