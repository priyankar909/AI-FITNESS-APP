package com.fitness.aiservice.contoller;


import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.service.Recommendationservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendation")
public class RecommendationController {

    private  final Recommendationservice recommendationservice;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable  String userId){
          return ResponseEntity.ok(recommendationservice.getUserRecommendation(userId));
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendation(@PathVariable  String activityId){
        return ResponseEntity.ok(recommendationservice.getActivityRecommendation(activityId));
    }



}
