package com.example.application.form;

import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

@Data
public class SignupForm {
    @NotBlank
    @Email
    private String userId ;

    @NotBlank
    @Length(min=4,max=100)
    @Pattern (regexp = "^[a-zA-Z0-9]+$" )
    private String password;

    @NotBlank
    private String userName;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthday;

    @Min(20)
    @Max(100)
    private Integer age;

    @NotNull
    private Integer gender;
}
