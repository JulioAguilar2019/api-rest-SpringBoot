package com.app.apispringboot.services;

import com.app.apispringboot.DTO.PostDTO;
import com.app.apispringboot.entities.PostEntity;
import com.app.apispringboot.exceptions.ResourceNotFoundException;
import com.app.apispringboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService{

    @Autowired
    private PostRepository postRepository;
    @Override
    public PostDTO createPost(PostDTO postDTO) {
        PostEntity post = convertDTOToEntity(postDTO);

        PostEntity newPost = postRepository.save(post);

        PostDTO postResponse = convertEntityToDTO(newPost);

        return postResponse;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostEntity> posts = postRepository.findAll();
        return posts.stream().map(post -> convertEntityToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Long idPost) {
        PostEntity post = postRepository.findById(idPost).orElseThrow(( ) -> new ResourceNotFoundException("Post", "idPost", idPost));
        return convertEntityToDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long idPost) {
        PostEntity post = postRepository.findById(idPost).orElseThrow(( ) -> new ResourceNotFoundException("Post", "idPost", idPost));

        post = convertDTOToEntity(postDTO);
    }

    private PostDTO convertEntityToDTO(PostEntity postEntity) {
        PostDTO postDTO = new PostDTO();

        postDTO.setIdPost(postEntity.getIdPost());
        postDTO.setTitle(postEntity.getTitle());
        postDTO.setDescription(postEntity.getDescription());
        postDTO.setContent(postEntity.getContent());

        return postDTO;
    }

    private PostEntity convertDTOToEntity(PostDTO postDTO) {
        PostEntity postEntity = new PostEntity();

        postEntity.setIdPost(postDTO.getIdPost());
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setDescription(postDTO.getDescription());
        postEntity.setContent(postDTO.getContent());

        return postEntity;
    }

}
