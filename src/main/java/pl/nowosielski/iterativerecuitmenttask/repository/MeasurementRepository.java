package pl.nowosielski.iterativerecuitmenttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.nowosielski.iterativerecuitmenttask.entity.Measurement;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    Measurement findFirstByOrderByMeasurementTimeDesc();

    List<Measurement> findAllByMeasurementTimeBetweenAndIsGoodQuality(LocalDateTime begin, LocalDateTime end, boolean isGoodQuality);
}
