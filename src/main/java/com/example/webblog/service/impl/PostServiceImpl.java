package com.example.webblog.service.impl;

import com.example.webblog.constant.PostStatus;
import com.example.webblog.converter.PostConverter;
import com.example.webblog.entity.PostEntity;
import com.example.webblog.entity.PostStatusEntity;
import com.example.webblog.entity.TopicEntity;
import com.example.webblog.entity.UserEntity;
import com.example.webblog.exception.NotFoundException;
import com.example.webblog.model.PaginationModel;
import com.example.webblog.model.PostRequestModel;
import com.example.webblog.model.PostResponseModel;
import com.example.webblog.repository.PostRepository;
import com.example.webblog.repository.PostStatusRepository;
import com.example.webblog.repository.TopicRepository;
import com.example.webblog.service.FilesStorageService;
import com.example.webblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private PostStatusRepository postStatusRepository;
    @Autowired
    @Qualifier("thumbnailStorageService")
    private FilesStorageService filesStorageService;
    @Autowired
    private AuditorAware<UserEntity> auditorAware;
    @Autowired
    private PostConverter postConverter;

    @Override
    public PaginationModel getAllPosts(int page, int limit, String search) {
        List<PostResponseModel> models = new ArrayList<>();

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        List<PostEntity> entities = postRepository.findAll(pageable).getContent();

        int totalPage = (int) Math.ceil((double) totalItem() / limit);

        if(search != null){
            entities = postRepository.findByTitleOrDescriptionOrContentContaining(search.trim(), pageable);
            totalPage = (int) Math.ceil((double) totalSearchingItem(search.trim()) / limit);
        }

        entities.forEach(entity -> {
            PostResponseModel model = postConverter.toResponseModel(entity);
            models.add(model);
        });

        PaginationModel paginationModel = new PaginationModel();
        paginationModel.setPage(page);
        paginationModel.setTotalPage(totalPage);
        paginationModel.setResults(models);

        return paginationModel;
    }

    @Override
    public PostResponseModel getPostById(int id) {
        PostEntity post = postRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Post does not exist")
        );

        return postConverter.toResponseModel(post);
    }

    @Override
    public PostResponseModel createPost(PostRequestModel model) {
        PostEntity newPost = postConverter.toEntity(model);

        TopicEntity topic = topicRepository.findById(model.getTopicId()).get();
        PostStatusEntity status = postStatusRepository.findByName(PostStatus.PENDING.getName()).get();

        newPost.setTopic(topic);
        newPost.setStatus(status);

        filesStorageService.save(model.getThumbnail());
        String url = filesStorageService.buildFileUrl(model.getThumbnail());

        newPost.setThumbnail(url);

        newPost = postRepository.save(newPost);

        return postConverter.toResponseModel(newPost);
    }

    @Override
    public PostResponseModel updatePost(int id, PostRequestModel model) {
        PostEntity oldPost = postRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Post does not exist")
        );

        PostEntity updatedPost = postConverter.toEntity(model, oldPost);

        TopicEntity topic = topicRepository.findById(model.getTopicId()).get();
        PostStatusEntity status = postStatusRepository.findByName(PostStatus.PENDING.getName()).get();

        updatedPost.setTopic(topic);
        updatedPost.setStatus(status);

        if(model.getThumbnail() != null){
            filesStorageService.save(model.getThumbnail());
            String url = filesStorageService.buildFileUrl(model.getThumbnail());

            updatedPost.setThumbnail(url);
        }

        updatedPost = postRepository.save(updatedPost);

        return postConverter.toResponseModel(updatedPost);
    }

    @Override
    public PostResponseModel deletePost(int id) {
        PostEntity post = postRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Post does not exist")
        );

        PostStatusEntity status = postStatusRepository.findByName(PostStatus.DELETED.getName()).get();

        post.setStatus(status);
        post.setDeleted(true);
        post.setDeletedBy(auditorAware.getCurrentAuditor().get());

        post = postRepository.save(post);

        return postConverter.toResponseModel(post);
    }

    @Override
    public PostResponseModel approvePost(int postId, int statusId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException("Post does not exist")
        );

        PostStatusEntity status = postStatusRepository.findById(statusId).orElseThrow(
                () -> new NotFoundException("Post status does not exist")
        );

        post.setStatus(status);
        post.setApprovedBy(auditorAware.getCurrentAuditor().get());

        post = postRepository.save(post);

        return postConverter.toResponseModel(post);
    }

    @Override
    public Resource getThumbnail(String filename) {
        return filesStorageService.load(filename);
    }

    @Override
    public int totalItem() {
        return (int) postRepository.count();
    }

    @Override
    public int totalSearchingItem(String search) {
        return (int) postRepository.countSearchingItem(search);
    }
}
