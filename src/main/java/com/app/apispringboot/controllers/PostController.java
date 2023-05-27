package com.app.apispringboot.controllers;

import com.app.apispringboot.DTO.PostDTO;
import com.app.apispringboot.DTO.PostResponse;
import com.app.apispringboot.services.PostService;
import com.app.apispringboot.utilities.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public PostResponse getAllPosts(@RequestParam(value = "page", defaultValue = AppConstants.NUMBER_OF_PAGE_DEFAULT, required = false) int page,
                                    @RequestParam(value = "size", defaultValue = AppConstants.SIZE_OF_PAGE_DEFAULT, required = false) int size,
                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_DEFAULT, required = false) String sortBy,
                                    @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_DEFAULT, required = false) String sortDir) {
        return postService.getAllPosts(page, size, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") Long id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable("id") Long id){
        return new ResponseEntity<>(postService.updatePost(postDTO, id), HttpStatus.OK);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Map<String, String>> deletePost(@PathVariable("id") Long id){
//        postService.deletePost(id);
//
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "Post deleted successfully");
//
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

}
