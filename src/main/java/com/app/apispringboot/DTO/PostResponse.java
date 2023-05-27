package com.app.apispringboot.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private List<PostDTO> content;
    private int page;
    private int size;
    private Long totalElements;
    private int totalPages;
    private boolean lastPage;
}
