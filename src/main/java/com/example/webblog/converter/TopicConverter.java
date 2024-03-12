package com.example.webblog.converter;

import com.example.webblog.entity.TopicEntity;
import com.example.webblog.model.TopicRequestModel;
import com.example.webblog.model.TopicResponseModel;
import org.springframework.stereotype.Component;

@Component
public class TopicConverter {

    public TopicEntity toEntity(TopicRequestModel model){
        TopicEntity entity = new TopicEntity();
        entity.setName(model.getName());
        return entity;
    }

    public TopicEntity toEntity(TopicRequestModel model, TopicEntity entity){
        entity.setName(model.getName());
        return entity;
    }

    public TopicResponseModel toResponseModel(TopicEntity entity){
        TopicResponseModel model = new TopicResponseModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setCreatedDate(entity.getCreatedDate());
        model.setLastModifiedDate(entity.getLastModifiedDate());
        model.setDeleted(entity.isDeleted());
        model.setCreatedBy(entity.getCreatedBy().getUsername());
        model.setLastModifiedBy(entity.getLastModifiedBy().getUsername());
        if(entity.getDeletedBy() != null){
            model.setDeletedBy(entity.getDeletedBy().getUsername());
        }

        return model;
    }
}
