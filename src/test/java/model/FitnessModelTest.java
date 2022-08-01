package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class FitnessModelTest {

    @Test
    void calculateFitnessModel() {
        FitnessModel fitnessModel = new FitnessModel(1L, 2, 3, 4, 5, LocalDate.now());
        assertEquals("Calculated Successfully", fitnessModel.calculate());
    }

    @Test
    void calculateFitnessModelWithDurationLessThanZero() {
        FitnessModel fitnessModel = new FitnessModel(1L, -2, 3, 4, 5, LocalDate.now());
        assertEquals("Duration can't be less than 0 hour", fitnessModel.calculate());
    }

    @Test
    void calculateFitnessModelWithDurationMoreThan100() {
        FitnessModel fitnessModel = new FitnessModel(1L, 200, 3, 4, 5, LocalDate.now());
        assertEquals("Duration can't be more than 24 hour", fitnessModel.calculate());
    }

    @Test
    void calculateFitnessModelWithDistanceLessThanZero() {
        FitnessModel fitnessModel = new FitnessModel(1L, 2, -3, 4, 5, LocalDate.now());
        assertEquals("Distance can't be less than 0 kilometer", fitnessModel.calculate());
    }

    @Test
    void calculateFitnessModelWithDistanceMoreThan100() {
        FitnessModel fitnessModel = new FitnessModel(1L, 2, 300, 4, 5, LocalDate.now());
        assertEquals("Distance can't be more than 100 kilometer", fitnessModel.calculate());
    }

    @Test
    void calculateFitnessModelWithCalorieLessThanZero() {
        FitnessModel fitnessModel = new FitnessModel(1L, 2, 3, -4, 5, LocalDate.now());
        assertEquals("Calorie can't be less than 0 kcal", fitnessModel.calculate());
    }

    @Test
    void calculateFitnessModelWithCalorieMoreThan10000() {
        FitnessModel fitnessModel = new FitnessModel(1L, 2, 3, 40000, 5, LocalDate.now());
        assertEquals("Calorie can't be more than 10000 kcal", fitnessModel.calculate());
    }

    @Test
    void calculateFitnessModelWithStepsLessThanZero() {
        FitnessModel fitnessModel = new FitnessModel(1L, 2, 3, 4, -5, LocalDate.now());
        assertEquals("Steps can't be less than 0 steps", fitnessModel.calculate());
    }

    @Test
    void calculateFitnessModelWithStepsMoreThan100000() {
        FitnessModel fitnessModel = new FitnessModel(1L, 2, 3, 4, 500000, LocalDate.now());
        assertEquals("Steps can't be more than 100000 steps", fitnessModel.calculate());
    }
}