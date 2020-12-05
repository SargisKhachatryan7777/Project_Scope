package main.java.com.example.repository;

import com.example.model.Projects;
import com.example.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Projects, Integer> {
    List<Projects> findAllByUser(User user);

    List<Projects> findAllByMembers(String members);
}
