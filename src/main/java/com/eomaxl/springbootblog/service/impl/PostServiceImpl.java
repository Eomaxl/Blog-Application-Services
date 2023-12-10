package com.eomaxl.springbootblog.service.impl;

import com.eomaxl.springbootblog.entity.Post;
import com.eomaxl.springbootblog.payload.PostDto;
import com.eomaxl.springbootblog.payload.PostResponse;
import com.eomaxl.springbootblog.respository.PostRepository;
import com.eomaxl.springbootblog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
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
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir){
        Pageable pageAble = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Post> allPosts = postRepository.findAll(pageAble);
        List<Post> listOfPosts = allPosts.getContent();
        List<PostDto> postDtos = listOfPosts.stream().map(this::mapToDTO).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNo(allPosts.getNumber());
        postResponse.setPageSize(allPosts.getSize());
        postResponse.setTotalElements(allPosts.getTotalElements());
        postResponse.setTotalPages(allPosts.getTotalPages());
        postResponse.setLast(allPosts.isLast());
        return postResponse;
    }

    private PostDto mapToDTO(Post post){
        PostDto postDto = mapper.map(post, PostDto.class);
        return postDto;
    }

    private Post mapToEntity(PostDto postDto){
        Post newPost = mapper.map(postDto, Post.class);
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
