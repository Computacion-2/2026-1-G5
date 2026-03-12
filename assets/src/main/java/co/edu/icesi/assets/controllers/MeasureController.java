package co.edu.icesi.assets.controllers;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.assets.model.Measurement;
import co.edu.icesi.assets.repositories.MeasureRepository;


@RestController
@RequestMapping("/measures")
public class MeasureController {

    @Autowired
    private MeasureRepository repository;
    
    @GetMapping
    public List<Measurement> getMeasurements() {
        Timestamp t1 = Timestamp.valueOf("2024-04-20 00:00:00");
        Timestamp t2 = Timestamp.valueOf("2024-04-30 23:59:59");
        String name = "Agitation (rpm)";
        return repository.findByAssetNameAndTimeBetween(name, t1, t2);
    }
    @GetMapping("/2")
    public List<Measurement> getMeasurements2() {
        Timestamp t1 = Timestamp.valueOf("2024-04-20 00:00:00");
        Timestamp t2 = Timestamp.valueOf("2024-04-30 23:59:59");
        String name = "Agitation (rpm)";
        return repository.findByAsset_NameAndTime_Between(name, t1, t2);
    }
    @GetMapping("/pageable")
    public List<Measurement> getMeasurementsPage() {
        Timestamp t1 = Timestamp.valueOf("2024-04-20 00:00:00");
        Timestamp t2 = Timestamp.valueOf("2024-04-30 23:59:59");
        String name = "Agitation (rpm)";
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        return repository.findByAsset_NameAndTime_Between(name, t1, t2, pageable);
    }
    @GetMapping("/process")
    public List<Measurement> getMeasurementsProcess() {
        Timestamp t1 = Timestamp.valueOf("2024-04-20 00:00:00");
        Timestamp t2 = Timestamp.valueOf("2024-04-30 23:59:59");
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        return repository.findByExecution_Process_IdAndTime_Between(2, t1, t2, pageable);
    }
}
