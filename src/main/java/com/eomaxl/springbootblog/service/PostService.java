package com.eomaxl.springbootblog.service;

import com.eomaxl.springbootblog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy);
    PostDto getPostById(Long id);
    PostDto updatePostById(Long id, PostDto postDto);
    void deletePostById(Long id);
}
