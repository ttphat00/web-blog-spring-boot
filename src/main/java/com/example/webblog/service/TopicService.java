package com.example.webblog.service;

import com.example.webblog.model.TopicRequestModel;
import com.example.webblog.model.TopicResponseModel;

import java.util.List;

public interface TopicService {
    List<TopicResponseModel> getAllTopics();
    TopicResponseModel createTopic(TopicRequestModel model);
    TopicResponseModel updateTopic(int id, TopicRequestModel model);
    TopicResponseModel deleteTopic(int id);
}
