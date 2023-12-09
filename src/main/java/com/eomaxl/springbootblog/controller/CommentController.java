package com.eomaxl.springbootblog.controller;

import com.eomaxl.springbootblog.payload.CommentDto;
import com.eomaxl.springbootblog.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable  Long postId,@RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.createComment(postId, commentDto));
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable Long postId){
        return commentService.getCommentsByPostId(postId);
    }
}
