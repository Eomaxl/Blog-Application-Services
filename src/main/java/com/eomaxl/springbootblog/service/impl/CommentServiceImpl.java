package com.eomaxl.springbootblog.service.impl;

import com.eomaxl.springbootblog.entity.Comment;
import com.eomaxl.springbootblog.entity.Post;
import com.eomaxl.springbootblog.exception.ResourceNotFound;
import com.eomaxl.springbootblog.payload.CommentDto;
import com.eomaxl.springbootblog.respository.CommentRepository;
import com.eomaxl.springbootblog.respository.PostRepository;
import com.eomaxl.springbootblog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }
    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFound("Post","id",postId));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapToDTO(newComment);
    }

    private CommentDto mapToDTO(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId){
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }
}
