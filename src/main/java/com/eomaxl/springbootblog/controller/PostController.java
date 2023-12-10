package com.eomaxl.springbootblog.controller;

import com.eomaxl.springbootblog.payload.PostDto;
import com.eomaxl.springbootblog.service.PostService;
import com.eomaxl.springbootblog.utils.AppConstants;
import jakarta.validation.Valid;
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
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.createPost(postDto));
    }

    @GetMapping()
    public ResponseEntity<PostDto> getAllPosts(@RequestParam(value="pageNo", defaultValue= AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
                                               @RequestParam(value="pageSize", defaultValue=AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
                                               @RequestParam(value="sortBy", defaultValue=AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
                                                @RequestParam(value="sortDirection", defaultValue=AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDirection){
        return new ResponseEntity(postService.getAllPosts(pageNo,pageSize,sortBy,sortDirection), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
        return new ResponseEntity(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@Valid @PathVariable Long id, @RequestBody PostDto postDto){
        return new ResponseEntity(postService.updatePostById(id, postDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

}
