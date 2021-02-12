package pl.nowosielski.iterativerecuitmenttask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.nowosielski.iterativerecuitmenttask.service.MeasurementService;

@SpringBootApplication
public class IterativeRecuitmentTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(IterativeRecuitmentTaskApplication.class, args);
    }

}
