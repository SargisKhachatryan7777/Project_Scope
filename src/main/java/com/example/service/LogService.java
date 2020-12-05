package main.java.com.example.service;

import com.example.model.Logs;
import com.example.model.User;
import com.example.repository.LogRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private final LogRepository logRepository;

    public void save(Logs logs) {
        this.logRepository.save(logs);
    }

    public List<Logs> allLogByUser(User user) {
        return this.logRepository.findAllByUser(user);
    }

    public void delete(int id) {
        this.logRepository.deleteById(id);
    }

    public LogService(final LogRepository logRepository) {
        this.logRepository = logRepository;
    }
}
