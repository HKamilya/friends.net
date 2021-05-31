package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.validation.ValidNames;
import ru.itis.javalab.validation.ValidPassword;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidNames(
        message = "{errors.password.matches}",
        password = "password",
        confPass = "confPass"
)
public class UserForm {
    @Email(message = "${errors.incorrect.email}")
    private String email;
    private String username;
    @ValidPassword(message = "{errors.invalid.password}")
    private String password;
    private String confPass;

}
