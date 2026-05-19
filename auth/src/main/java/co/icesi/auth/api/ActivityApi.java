package co.icesi.auth.api;

import co.icesi.auth.dtos.activities.ActivityDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
public interface ActivityApi {

    @PostMapping
    @PreAuthorize("hasAuthority('ACTIVITY_CREATE')")
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO);

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ACTIVITY_UPDATE')")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable Long id, @RequestBody ActivityDTO activityDTO);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ACTIVITY_DELETE')")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id);

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ACTIVITY_READ')")
    public ResponseEntity<ActivityDTO> getActivity(@PathVariable Long id);

    @GetMapping("/course/{courseId}")
    @PreAuthorize("hasAuthority('ACTIVITY_READ')")
    public ResponseEntity<List<ActivityDTO>> getActivitiesByCourse(@PathVariable Long courseId);
}
