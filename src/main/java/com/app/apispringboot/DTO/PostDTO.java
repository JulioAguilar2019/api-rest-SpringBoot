package com.app.apispringboot.DTO;

import lombok.Data;

@Data
public class PostDTO {
    private Long idPost;
    private String title;
    private String description;
    private String content;

}
