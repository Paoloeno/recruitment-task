package pl.nowosielski.iterativerecuitmenttask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.nowosielski.iterativerecuitmenttask.enums.EngineeringUnit;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Measurement {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "MEASUREMENT_TIME", nullable = false)
    private LocalDateTime measurementTime;

    @Column(name = "VALUE", nullable = false)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "UNIT", nullable = false)
    private EngineeringUnit unit;

    @Column(name = "QUALITY", nullable = false)
    private Boolean isGoodQuality;
}
