package main.java.com.example.controller;

import com.example.dto.ProjectDto;
import com.example.model.Projects;
import com.example.model.User;
import com.example.model.UserType;
import com.example.security.CurrentUser;
import com.example.service.ProjectService;
import com.example.service.UserService;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectController {
    private static final Logger log = LogManager.getLogger(ProjectController.class);
    private final ProjectService projectService;
    private final UserService userService;

    @PostMapping({"/project"})
    public String addProject(@AuthenticationPrincipal CurrentUser currentUser, @ModelAttribute ProjectDto projectDto, BindingResult result, ModelMap modelMap) {
        Projects projects = Projects.builder().name(projectDto.getName()).date(new Date()).deadline(java.sql.Date.valueOf(projectDto.getDeadline())).members(projectDto.getMembers()).hours(projectDto.getHours()).user(currentUser.getUser()).build();
        this.projectService.save(projects);
        log.info("project was added");
        log.info(new Date());
        return "redirect:/user/leader/projects";
    }

    @GetMapping({"/user/leader/projects"})
    public String allProjects(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        if (currentUser == null && currentUser.getUser().getUserType() == UserType.TEAM_MEMBER) {
            return "redirect:/";
        } else {
            List<Projects> projects = this.projectService.allProjectsByUser(currentUser.getUser());
            modelMap.addAttribute("projects", projects);
            return "projects";
        }
    }

    @GetMapping({"/user/leader/projects/add"})
    public String addProjects(ModelMap modelMap) {
        List<User> users = this.userService.findByUsertype(UserType.TEAM_MEMBER);
        modelMap.addAttribute("users", users);
        return "addProjects";
    }

    @GetMapping({"/user/leader/projects/delete"})
    public String delete(@RequestParam("id") int id) {
        this.projectService.delete(id);
        log.info("project with {} id was deleted", id);
        return "redirect:/user/leader/projects";
    }

    public ProjectController(final ProjectService projectService, final UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }
}
