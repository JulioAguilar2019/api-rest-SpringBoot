package com.app.apispringboot.controllers;

import com.app.apispringboot.DTO.PostDTO;
import com.app.apispringboot.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostDTO> getAllPosts(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                     @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        return postService.getAllPosts(page, size);
    }

    @GetMapping("/{idPost}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("idPost") Long idPost){
        return new ResponseEntity<>(postService.getPostById(idPost), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{idPost}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable("idPost") Long idPost){
        return new ResponseEntity<>(postService.updatePost(postDTO, idPost), HttpStatus.OK);
    }

//    @DeleteMapping("/{idPost}")
//    public ResponseEntity<Map<String, String>> deletePost(@PathVariable("idPost") Long idPost){
//        postService.deletePost(idPost);
//
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "Post deleted successfully");
//
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }

    @DeleteMapping("/{idPost}")
    public ResponseEntity<String> deletePost(@PathVariable("idPost") Long idPost){
        postService.deletePost(idPost);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

}
