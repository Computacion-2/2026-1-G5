package co.edu.icesi.assets.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.assets.model.Measurement;

@Repository
public interface MeasureRepository extends JpaRepository<Measurement, Integer>{

    List<Measurement> findByAssetNameAndTimeBetween(String name, Timestamp t1, Timestamp t2);
    List<Measurement> findByAsset_NameAndTime_Between(String name, Timestamp t1, Timestamp t2);

    List<Measurement> findByAsset_NameAndTime_Between(String name, Timestamp t1, Timestamp t2, Pageable pageable);


}
