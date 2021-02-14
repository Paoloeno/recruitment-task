package pl.nowosielski.iterativerecuitmenttask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nowosielski.iterativerecuitmenttask.entity.Measurement;
import pl.nowosielski.iterativerecuitmenttask.enums.EngineeringUnit;
import pl.nowosielski.iterativerecuitmenttask.repository.MeasurementRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementService {

    private MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public boolean putSomeDataToDatabase() {
        try {
            for (int i = 0; i < 10; i++) {
                BigDecimal value = BigDecimal.valueOf(Math.random() * 1000);
                boolean quality = value.compareTo(BigDecimal.valueOf(500.0)) == -1;

                Measurement measurement = new Measurement();
                measurement.setValue(value);
                measurement.setIsGoodQuality(quality);
                measurement.setUnit(enginieeringUnits.get(i));
                measurement.setMeasurementTime(someDateTimes.get(i));

                measurementRepository.save(measurement);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Measurement getLatestMeasurement(){
        return measurementRepository.findFirstByOrderByMeasurementTimeDesc().orElse(null);
    }

    public BigDecimal getAvarageValueBetweenDateTimes(LocalDateTime begin, LocalDateTime end, boolean isGoodValue){
        return average(getValuesBetweenDateTimes(begin, end, isGoodValue));
    }

    public List<BigDecimal> getValuesBetweenDateTimes(LocalDateTime begin, LocalDateTime end, boolean isGoodValue){
        List<Measurement> measurementList = measurementRepository.findAllByMeasurementTimeBetweenAndIsGoodQuality(begin, end, isGoodValue);
        return measurementList.stream().map(Measurement::getValue).collect(Collectors.toList());
    }

    private BigDecimal average(List<BigDecimal> values) {
        if(values.size() == 0){
            return BigDecimal.ZERO;
        }
        BigDecimal sum = values.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(new BigDecimal(values.size()), 3 , RoundingMode.HALF_UP);
    }


    private final List<LocalDateTime> someDateTimes = Arrays.asList(
            LocalDateTime.of(2019, 5, 18, 7, 44, 17),
            LocalDateTime.of(2019, 7, 12, 14, 24, 48),
            LocalDateTime.of(2019, 8, 14, 4, 15, 27),
            LocalDateTime.of(2018, 1, 3, 20, 7, 12),
            LocalDateTime.of(2018, 9, 1, 7, 3, 0),
            LocalDateTime.of(2020, 3, 8, 2, 55, 12),
            LocalDateTime.of(2020, 5, 7, 7, 23, 55),
            LocalDateTime.of(2020, 8, 5, 1, 11, 12),
            LocalDateTime.of(2020, 2, 11, 7, 57, 50),
            LocalDateTime.of(2021, 2, 3, 8, 0, 3),
            LocalDateTime.of(2021, 2, 5, 9, 2, 16)
    );

    private final List<EngineeringUnit> enginieeringUnits = Arrays.asList(
            EngineeringUnit.PA, EngineeringUnit.KG, EngineeringUnit.M, EngineeringUnit.M_2, EngineeringUnit.M_S,
            EngineeringUnit.W, EngineeringUnit.PA, EngineeringUnit.KG, EngineeringUnit.M, EngineeringUnit.M_2
    );
}
