package com.fitness.activityservice.service;


import com.fitness.activityservice.ActivityRepository;
import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private  final ActivityRepository activityRepository;



      private ActivityResponse mapToResponse(Activity activity){
          ActivityResponse response=new ActivityResponse();
          response.setId(activity.getId());
          response.setUserId(activity.getUserId());
          response.setType(activity.getType());
          response.setDuration(activity.getDuration());
          response.setCaloriesBurned(activity.getCaloriesBurned());

          response.setStartTime(activity.getStartTime());
          response.setAdditionalMetrics(activity.getAdditionalMetrics());
          response.setCreatedAt(activity.getCreatedAt());
          response.setUpdatedAt(activity.getUpdatedAt());

          return  response;
      }

    public ActivityResponse trackActivity(ActivityRequest request) {
        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type((request.getType()))
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        Activity savedAactivity = activityRepository.save(activity);
        return  mapToResponse(savedAactivity);
    }

    public List<ActivityResponse> getUserActivities(String userId) {
            List<Activity> activities=  activityRepository.findAllByUserId(userId);

            return activities.stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
    }

    public ActivityResponse getUActivityById(String activityId) {
            return activityRepository.findById(activityId)
                    .map(this::mapToResponse)
                    .orElseThrow(()-> new RuntimeException("activites not found with id"));
    }
}
