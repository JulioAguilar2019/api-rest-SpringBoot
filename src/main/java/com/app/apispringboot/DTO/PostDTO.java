package com.app.apispringboot.DTO;

import com.app.apispringboot.entities.CommentEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.util.List;

@Data
public class PostDTO {


    private Long id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 3, message = "Title must have at least {min} characters")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 10, message = "Description must have at least {min} characters")
    private String description;

    @NotEmpty(message = "Content cannot be empty")
    private String content;

    private List<CommentEntity> comments;
}
