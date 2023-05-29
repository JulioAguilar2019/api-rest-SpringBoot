package com.app.apispringboot.services;

import com.app.apispringboot.DTO.CommentDTO;
import com.app.apispringboot.entities.CommentEntity;
import com.app.apispringboot.entities.PostEntity;
import com.app.apispringboot.exceptions.BlogAppException;
import com.app.apispringboot.exceptions.ResourceNotFoundException;
import com.app.apispringboot.repository.CommentRepository;
import com.app.apispringboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService {

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
        CommentEntity comment = validateComment(idPost, idComment);

        return ConvertEntityToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long idPost, Long idComment,CommentDTO comment) {
        CommentEntity newComment = validateComment(idPost, idComment);

        newComment.setName(comment.getName());
        newComment.setEmail(comment.getEmail());
        newComment.setBody(comment.getBody());

        CommentEntity commentUpdated = commentRepository.save(newComment);

        return ConvertEntityToDTO(commentUpdated);
    }

    @Override
    public void deleteComment(Long idPost, Long idComment) {
        CommentEntity comment = validateComment(idPost, idComment);
        commentRepository.delete(comment);
    }

    private CommentEntity validateComment(Long idPost, Long idComment) {
        PostEntity post = postRepository.findById(idPost).orElseThrow(( ) -> new ResourceNotFoundException("Post", "idPost", idPost));

        CommentEntity comment = commentRepository.findById(idComment)
                .orElseThrow(( ) -> new ResourceNotFoundException("Comment", "idComment", idComment));

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Comment with id " + idComment + " does not belong to post with id " + idPost);
        }

        return comment;

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
