package com.app.apispringboot.services;

import com.app.apispringboot.DTO.PostDTO;
import com.app.apispringboot.DTO.PostResponse;

public interface PostService {

     PostDTO createPost(PostDTO postDTO);

     PostResponse getAllPosts(int page, int size, String sortBy, String sortDir);

     PostDTO getPostById(Long id);

     PostDTO updatePost(PostDTO postDTO, Long id);

     void deletePost(Long id);



}
