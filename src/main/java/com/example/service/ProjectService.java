package main.java.com.example.service;

import com.example.model.Projects;
import com.example.model.User;
import com.example.repository.ProjectRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public void save(Projects projects) {
        this.projectRepository.save(projects);
    }

    public List<Projects> allProjectsByUser(User user) {
        return this.projectRepository.findAllByUser(user);
    }

    public void delete(int id) {
        this.projectRepository.deleteById(id);
    }

    public List<Projects> findByMembers(String members) {
        return this.projectRepository.findAllByMembers(members);
    }

    public ProjectService(final ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
}
