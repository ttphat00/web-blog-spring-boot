package com.example.webblog.service.impl;

import com.example.webblog.converter.TopicConverter;
import com.example.webblog.entity.TopicEntity;
import com.example.webblog.entity.UserEntity;
import com.example.webblog.exception.NotFoundException;
import com.example.webblog.model.TopicRequestModel;
import com.example.webblog.model.TopicResponseModel;
import com.example.webblog.repository.TopicRepository;
import com.example.webblog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicConverter topicConverter;
    @Autowired
    private AuditorAware<UserEntity> auditorAware;

    @Override
    public List<TopicResponseModel> getAllTopics() {
        List<TopicResponseModel> models = new ArrayList<>();

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<TopicEntity> entities = topicRepository.findAll(sort);

        entities.forEach(entity -> {
            TopicResponseModel model = topicConverter.toResponseModel(entity);
            models.add(model);
        });

        return models;
    }

    @Override
    public TopicResponseModel createTopic(TopicRequestModel model) {
        TopicEntity newTopic = topicConverter.toEntity(model);
        newTopic = topicRepository.save(newTopic);

        return topicConverter.toResponseModel(newTopic);
    }

    @Override
    public TopicResponseModel updateTopic(int id, TopicRequestModel model) {
        TopicEntity oldTopic = topicRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Topic does not exist")
        );

        TopicEntity updatedTopic = topicConverter.toEntity(model, oldTopic);
        updatedTopic = topicRepository.save(updatedTopic);

        return topicConverter.toResponseModel(updatedTopic);
    }

    @Override
    public TopicResponseModel deleteTopic(int id) {
        TopicEntity topic = topicRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Topic does not exist")
        );

        topic.setDeleted(true);
        topic.setDeletedBy(auditorAware.getCurrentAuditor().get());
        topic = topicRepository.save(topic);

        return topicConverter.toResponseModel(topic);
    }
}
