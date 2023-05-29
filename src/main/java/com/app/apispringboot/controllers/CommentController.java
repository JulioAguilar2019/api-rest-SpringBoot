package com.app.apispringboot.controllers;

import com.app.apispringboot.DTO.CommentDTO;
import com.app.apispringboot.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("/posts/{idPost}/comments")
    public List<CommentDTO> findCommentsByidPost(@PathVariable(value = "idPost") Long idPost) {
        return commentService.getCommentsByPostId(idPost);
    }

    @GetMapping("/posts/{idPost}/comments/{idComment}")
    public ResponseEntity<CommentDTO> findCommentById(@PathVariable(value = "idPost") long idPost, @PathVariable(value = "idComment") Long idComment) {
        return new ResponseEntity<>(commentService.getCommentById(idPost, idComment), HttpStatus.OK);
    }

    @PostMapping("/posts/{idPost}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "idPost") long idPost, @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.createComment(idPost, commentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/posts/{idPost}/comments/{idComment}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable(value = "idPost") long idPost, @PathVariable(value = "idComment") Long idComment, @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.updateComment(idPost, idComment, commentDTO), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{idPost}/comments/{idComment}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "idPost") long idPost, @PathVariable(value = "idComment") Long idComment){
        commentService.deleteComment(idPost,idComment);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }


}
