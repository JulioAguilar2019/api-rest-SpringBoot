package com.app.apispringboot.services;

import com.app.apispringboot.DTO.PostDTO;

import java.util.List;

public interface PostService {

    public PostDTO createPost(PostDTO postDTO);

    public List<PostDTO> getAllPosts();

    public PostDTO getPostById(Long idPost);

    public PostDTO updatePost(PostDTO postDTO, Long idPost);

}
