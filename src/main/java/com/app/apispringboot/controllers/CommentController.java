package com.app.apispringboot.controllers;

import com.app.apispringboot.DTO.CommentDTO;
import com.app.apispringboot.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "postId") long postId, @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
    }
}
