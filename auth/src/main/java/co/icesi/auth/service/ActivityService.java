package co.icesi.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.icesi.auth.model.Activity;
import co.icesi.auth.model.Course;
import co.icesi.auth.repository.ActivityRepository;
import co.icesi.auth.repository.CourseRepository;

@Service
@Transactional
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    public List<Activity> getActivitiesByCourseId(Long courseId) {
        return activityRepository.findByCourseId(courseId);
    }

    public Activity createActivity(Activity activity, Long courseId) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));
        activity.setCourse(course);
        return activityRepository.save(activity);
    }

    public Activity updateActivity(Long id, Activity activityDetails) {
        Activity activity = activityRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));
        
        activity.setTitle(activityDetails.getTitle());
        activity.setDescription(activityDetails.getDescription());
        activity.setDueDate(activityDetails.getDueDate());
        
        return activityRepository.save(activity);
    }

    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new IllegalArgumentException("Actividad no encontrada");
        }
        activityRepository.deleteById(id);
    }
}