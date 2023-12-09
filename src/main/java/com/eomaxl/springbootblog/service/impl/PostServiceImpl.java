package com.eomaxl.springbootblog.service.impl;

import com.eomaxl.springbootblog.entity.Post;
import com.eomaxl.springbootblog.payload.PostDto;
import com.eomaxl.springbootblog.respository.PostRepository;
import com.eomaxl.springbootblog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // convert DTO to entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post newPost = postRepository.save(post);

        //convert entity to dto
        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setContent(newPost.getContent());
        postResponse.setDescription(newPost.getDescription());
        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy) {
        Pageable pageAble = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Post> allPosts = postRepository.findAll(pageAble);
        List<Post> listOfPosts = allPosts.getContent();
        List<PostDto> postDtos = listOfPosts.stream().map(this::mapToDTO).toList();

        return postDtos;
    }

    private PostDto mapToDTO(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    private Post mapToEntity(PostDto postDto){
        Post newPost = new Post();
        newPost.setId(postDto.getId());
        newPost.setTitle(postDto.getTitle());
        newPost.setDescription(postDto.getDescription());
        newPost.setContent(postDto.getContent());
        return newPost;
    }

    @Override
    public PostDto getPostById(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePostById(Long id, PostDto postDto){
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        postRepository.delete(post);
    }
}
