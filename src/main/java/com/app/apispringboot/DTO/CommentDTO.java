package com.app.apispringboot.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDTO {

    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, message = "Name must have at least {min} characters")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Body cannot be empty")
    @Size(min = 10, message = "Body must have at least {min} characters")
    private String body;
}
