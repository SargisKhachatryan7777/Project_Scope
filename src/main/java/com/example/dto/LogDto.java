package main.java.com.example.dto;

import com.example.model.Projects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogDto {

    private Projects projects;
    private Double hours;
}