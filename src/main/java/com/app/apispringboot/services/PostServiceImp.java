package com.app.apispringboot.services;

import com.app.apispringboot.DTO.PostDTO;
import com.app.apispringboot.DTO.PostResponse;
import com.app.apispringboot.entities.PostEntity;
import com.app.apispringboot.exceptions.ResourceNotFoundException;
import com.app.apispringboot.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        PostEntity post = convertDTOToEntity(postDTO);

        PostEntity newPost = postRepository.save(post);

        return convertEntityToDTO(newPost);
    }

    @Override
    public PostResponse getAllPosts(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ?
                Sort.by(sortBy).ascending()
                :
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<PostEntity> posts = postRepository.findAll(pageable);

        List<PostEntity> listPosts = posts.getContent();
        List<PostDTO> content =  listPosts.stream().map(post -> convertEntityToDTO(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPage(posts.getNumber());
        postResponse.setSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLastPage(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDTO getPostById(Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(( ) -> new ResourceNotFoundException("Post", "id", id));
        return convertEntityToDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(( ) -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        PostEntity updatePost = postRepository.save(post);
        return convertEntityToDTO(updatePost);
    }

    @Override
    public void deletePost(Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(( ) -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

//Convert with ModelMapper

private PostDTO convertEntityToDTO(PostEntity postEntity){
        PostDTO postDTO = modelMapper.map(postEntity, PostDTO.class);
        return postDTO;
}

private PostEntity convertDTOToEntity(PostDTO postDTO) {
        PostEntity postEntity = modelMapper.map(postDTO, PostEntity.class);
        return postEntity;
}

//Convert manually

//    private PostDTO convertEntityToDTO(PostEntity postEntity) {
//        PostDTO postDTO = new PostDTO();
//
//        postDTO.setId(postEntity.getId());
//        postDTO.setTitle(postEntity.getTitle());
//        postDTO.setDescription(postEntity.getDescription());
//        postDTO.setContent(postEntity.getContent());
//
//        return postDTO;
//    }
//
//    private PostEntity convertDTOToEntity(PostDTO postDTO) {
//        PostEntity postEntity = new PostEntity();
//
//        postEntity.setId(postDTO.getId());
//        postEntity.setTitle(postDTO.getTitle());
//        postEntity.setDescription(postDTO.getDescription());
//        postEntity.setContent(postDTO.getContent());
//
//        return postEntity;
//    }

}
