package co.icesi.auth.service.interfaces;

import co.icesi.auth.model.Activity;
import java.util.List;

public interface ActivityService {
    Activity createActivity(Activity activity, Long courseId);
    Activity updateActivity(Long id, Activity activity);
    void deleteActivity(Long id);
    Activity getActivity(Long id);
    List<Activity> getActivitiesByCourse(Long courseId);
}
