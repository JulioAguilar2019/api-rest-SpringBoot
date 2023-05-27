package com.app.apispringboot.services;

import com.app.apispringboot.DTO.CommentDTO;
import com.app.apispringboot.entities.CommentEntity;
import com.app.apispringboot.entities.PostEntity;
import com.app.apispringboot.exceptions.ResourceNotFoundException;
import com.app.apispringboot.repository.CommentRepository;
import com.app.apispringboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDTO createComment(Long idPost, CommentDTO commentDTO) {

        CommentEntity commentEntity = ConvertDTOToEntity(commentDTO);
        PostEntity post = postRepository.findById(idPost).orElseThrow(( ) -> new ResourceNotFoundException("Post", "idPost", idPost));

        commentEntity.setPost(post);

        CommentEntity newComment = commentRepository.save(commentEntity);
        return ConvertEntityToDTO(newComment);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(Long idPost) {
        List<CommentEntity> comments = commentRepository.findByPostId(idPost);
        return comments.stream().map(comment ->ConvertEntityToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(Long idPost, Long idComment) {
        return null;
    }

    private CommentDTO ConvertEntityToDTO(CommentEntity comment) {
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setBody(comment.getBody());

        return commentDTO;
    }

    private CommentEntity ConvertDTOToEntity(CommentDTO commentDTO) {
        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setId(commentDTO.getId());
        commentEntity.setName(commentDTO.getName());
        commentEntity.setEmail(commentDTO.getEmail());
        commentEntity.setBody(commentDTO.getBody());

        return commentEntity;
    }
}
