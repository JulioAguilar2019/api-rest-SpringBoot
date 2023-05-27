package com.app.apispringboot.services;

import com.app.apispringboot.DTO.PostDTO;

import java.util.List;

public interface PostService {

     PostDTO createPost(PostDTO postDTO);

     List<PostDTO> getAllPosts(int page, int size);

     PostDTO getPostById(Long idPost);

     PostDTO updatePost(PostDTO postDTO, Long idPost);

     void deletePost(Long idPost);

}
