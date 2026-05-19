package co.icesi.auth.api;

import co.icesi.auth.dtos.activities.ActivityDTO;
import co.icesi.auth.mapper.ActivityMapper;
import co.icesi.auth.model.Activity;
import co.icesi.auth.service.interfaces.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ActivityController implements ActivityApi {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public ResponseEntity<ActivityDTO> createActivity(ActivityDTO activityDTO) {
        Activity activity = activityMapper.toEntity(activityDTO);
        Activity created = activityService.createActivity(activity, activityDTO.getCourseId());
        return ResponseEntity.ok(activityMapper.toDto(created));
    }

    @Override
    public ResponseEntity<ActivityDTO> updateActivity(Long id, ActivityDTO activityDTO) {
        Activity activity = activityMapper.toEntity(activityDTO);
        Activity updated = activityService.updateActivity(id, activity);
        return ResponseEntity.ok(activityMapper.toDto(updated));
    }

    @Override
    public ResponseEntity<Void> deleteActivity(Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ActivityDTO> getActivity(Long id) {
        Activity activity = activityService.getActivity(id);
        return ResponseEntity.ok(activityMapper.toDto(activity));
    }

    @Override
    public ResponseEntity<List<ActivityDTO>> getActivitiesByCourse(Long courseId) {
        List<Activity> activities = activityService.getActivitiesByCourse(courseId);
        List<ActivityDTO> dtos = activities.stream()
                .map(activityMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
