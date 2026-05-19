package co.icesi.auth.service;

import co.icesi.auth.model.Activity;
import co.icesi.auth.repository.ActivityRepository;
import co.icesi.auth.repository.CourseRepository;
import co.icesi.auth.service.interfaces.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActivityServiceImp implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Activity createActivity(Activity activity, Long courseId) {
        activity.setCourse(courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found")));
        return activityRepository.save(activity);
    }

    @Override
    public Activity updateActivity(Long id, Activity activityDetails) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
        activity.setName(activityDetails.getName());
        activity.setDescription(activityDetails.getDescription());
        activity.setDeadline(activityDetails.getDeadline());
        activity.setWeight(activityDetails.getWeight());
        return activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public Activity getActivity(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
    }

    @Override
    public List<Activity> getActivitiesByCourse(Long courseId) {
        return activityRepository.findByCourseId(courseId);
    }
}
