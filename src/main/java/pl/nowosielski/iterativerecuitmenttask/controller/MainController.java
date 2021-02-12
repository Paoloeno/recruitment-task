package pl.nowosielski.iterativerecuitmenttask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nowosielski.iterativerecuitmenttask.entity.Measurement;
import pl.nowosielski.iterativerecuitmenttask.service.MeasurementService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MainController {

    private MeasurementService measurementService;

    @Autowired
    public MainController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping(value = "/fillInDataTable")
    public ResponseEntity<Void> fillInDateTable(){
        boolean jobDone = measurementService.putSomeDataToDatabase();

        if(jobDone){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/latestValue")
    public Measurement getLatestValue(){
        return measurementService.getLatestMeasurement();
    }

    @GetMapping(value = "/avarageValue/from={begin}/to={end}/isGoodValue={isGoodValue}")
    public BigDecimal getAverageValueBetweenDateTimes(@PathVariable String begin,
                                                      @PathVariable String end,
                                                      @PathVariable boolean isGoodValue){
        return measurementService.getAvarageValueBetweenDateTimes(LocalDateTime.parse(begin), LocalDateTime.parse(end), isGoodValue);
    }

    @GetMapping(value = "/valuesBetween/from={begin}/to={end}")
    public List<BigDecimal> getValuesBetweenDateTimes(@PathVariable String begin,
                                                      @PathVariable String end){
        return measurementService.getValuesBetweenDateTimes(LocalDateTime.parse(begin), LocalDateTime.parse(end), true);
    }
}
