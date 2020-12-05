package main.java.com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private int id;
    @NotBlank(message = "Name is requaired")
    private String name;
    @NotBlank(message = "Surname is requaired")
    private String surname;
    @NotBlank(message = "Email is requaired")
    @Email(message = "Email isn't valid")
    private String email;
    @Size(min = 6,message = "Passwore lenght sould be at least 6 symbol")
    private String password;
}