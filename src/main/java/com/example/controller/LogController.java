package main.java.com.example.controller;

import com.example.dto.LogDto;
import com.example.model.Logs;
import com.example.model.Projects;
import com.example.security.CurrentUser;
import com.example.service.LogService;
import com.example.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LogController {

    private final LogService logService;
    private final ProjectService projectService;

    @PostMapping("/user/member/log/add")
    public String addLog(@AuthenticationPrincipal CurrentUser currentUser, @ModelAttribute LogDto logDto){
        Logs logs = Logs.builder()
                .projects(logDto.getProjects())
                .date(new Date())
                .user(currentUser.getUser())
                .hours(logDto.getHours())
                .build();
        logService.save(logs);
        log.info("log was aded");
        return "redirect:/user/member/log";
    }

    @GetMapping("/user/member/log")
    public String logPage(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap){
        List<Projects> projects = projectService.findByMembers(currentUser.getUser().getName());
        List<Logs> logs = logService.allLogByUser(currentUser.getUser());
        modelMap.addAttribute("projects", projects);
        modelMap.addAttribute("logs", logs);
        return "logPage";
    }

    @GetMapping("/user/member/log/delete")
    public String delete(@RequestParam("id") int id){
        logService.delete(id);
        log.info("log was deleted");
        return "redirect:/user/member/log";
    }
}