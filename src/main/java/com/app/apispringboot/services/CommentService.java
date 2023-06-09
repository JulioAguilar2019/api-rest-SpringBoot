package com.app.apispringboot.services;

import com.app.apispringboot.DTO.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(Long idPost, CommentDTO commentDTO);
    List<CommentDTO> getCommentsByPostId(Long idPost);
    CommentDTO getCommentById(Long idPost, Long idComment);
    CommentDTO updateComment(Long idPost, Long idComment, CommentDTO comment);
    void deleteComment(Long idPost, Long idComment);
}
