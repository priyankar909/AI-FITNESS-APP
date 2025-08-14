package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommmendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class Recommendationservice {
    private  final RecommmendationRepository recommmendationRepository;


    public List<Recommendation> getUserRecommendation(String userId) {
        return  recommmendationRepository.findByUserId(userId);
    }

    public Recommendation getActivityRecommendation(String activityId) {
        return  recommmendationRepository.findByActivityId(activityId)
                .orElseThrow(()-> new RuntimeException("No recomemdation is found for this acivity id " + activityId));
    }
}
