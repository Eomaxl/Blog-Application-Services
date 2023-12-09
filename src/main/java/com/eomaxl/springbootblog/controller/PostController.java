package com.eomaxl.springbootblog.controller;

import com.eomaxl.springbootblog.payload.PostDto;
import com.eomaxl.springbootblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.createPost(postDto));
    }

    @GetMapping()
    public ResponseEntity<PostDto> getAllPosts(@RequestParam(value="pageNo", defaultValue="0") int pageNo,
                                               @RequestParam(value="pageSize", defaultValue="5") int pageSize,
                                               @RequestParam(value="sortBy", defaultValue="id") String sortBy){
        return new ResponseEntity(postService.getAllPosts(pageNo,pageSize,sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
        return new ResponseEntity(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable Long id, @RequestBody PostDto postDto){
        return new ResponseEntity(postService.updatePostById(id, postDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

}
