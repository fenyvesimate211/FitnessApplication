package model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FitnessModel {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private double duration;
    @Column(nullable = false)
    private double distance;
    @Column(nullable = false)
    private double calorie;
    @Column(nullable = false)
    private int steps;
    @Column(nullable = false)
    private LocalDate date;

    public FitnessModel(double duration, double distance, double calorie, int steps, LocalDate date) {
        this.duration = duration;
        this.distance = distance;
        this.calorie = calorie;
        this.steps = steps;
        this.date = date;
    }

    /**
     * Check if the duration, distance, calorie, steps are out of range.
     * @return a String value if in range or out of range
     */
    public String calculate() {
        if (getDuration() <= 0){
            return "Duration can't be less than 0 hour";
        }
        if (getDuration() >= 100) {
            return "Duration can't be more than 24 hour";
        }
        if (getDistance() <= 0) {
            return "Distance can't be less than 0 kilometer";
        }
        if (getDistance() >= 100) {
            return "Distance can't be more than 100 kilometer";
        }
        if (getCalorie() <= 0) {
            return "Calorie can't be less than 0 kcal";
        }
        if (getCalorie() >= 10000) {
            return "Calorie can't be more than 10000 kcal";
        }
        if (getSteps() <= 0) {
            return "Steps can't be less than 0 steps";
        }
        if (getSteps() >= 100000) {
            return "Steps can't be more than 100000 steps";
        }
        return "Calculated Successfully";
    }
}
