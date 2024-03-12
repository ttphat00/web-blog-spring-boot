package com.example.webblog.converter;

import com.example.webblog.entity.PostEntity;
import com.example.webblog.model.PostRequestModel;
import com.example.webblog.model.PostResponseModel;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {

    public PostEntity toEntity(PostRequestModel model){
        PostEntity entity = new PostEntity();
        entity.setTitle(model.getTitle());
        entity.setDescription(model.getDescription());
        entity.setContent(model.getContent());

        return entity;
    }

    public PostEntity toEntity(PostRequestModel model, PostEntity entity){
        entity.setTitle(model.getTitle());
        entity.setDescription(model.getDescription());
        entity.setContent(model.getContent());

        return entity;
    }

    public PostResponseModel toResponseModel(PostEntity entity){
        PostResponseModel model = new PostResponseModel();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setDescription(entity.getDescription());
        model.setThumbnail(entity.getThumbnail());
        model.setContent(entity.getContent());
        model.setTopic(entity.getTopic().getName());
        model.setStatus(entity.getStatus().getName());
        model.setCreatedBy(entity.getCreatedBy().getUsername());
        model.setLastModifiedBy(entity.getLastModifiedBy().getUsername());
        if(entity.getDeletedBy() != null){
            model.setDeletedBy(entity.getDeletedBy().getUsername());
        }
        if(entity.getApprovedBy() != null){
            model.setApprovedBy(entity.getApprovedBy().getUsername());
        }
        model.setCreatedDate(entity.getCreatedDate());
        model.setLastModifiedDate(entity.getLastModifiedDate());
        model.setDeleted(entity.isDeleted());

        return model;
    }
}
