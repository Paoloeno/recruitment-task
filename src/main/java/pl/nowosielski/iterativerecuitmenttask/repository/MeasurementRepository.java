package pl.nowosielski.iterativerecuitmenttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.nowosielski.iterativerecuitmenttask.entity.Measurement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    Optional<Measurement> findFirstByOrderByMeasurementTimeDesc();

    List<Measurement> findAllByMeasurementTimeBetweenAndIsGoodQuality(LocalDateTime begin, LocalDateTime end, boolean isGoodQuality);
}
