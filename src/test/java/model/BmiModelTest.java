package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BmiModelTest {

    @Test
    void calculateBmi() {
        BmiModel bmiModel = new BmiModel(80, 1.8);
        assertEquals("Your BMI is 24,69", bmiModel.calculate());
    }

    @Test
    void calculateBmiWithBadResult() {
        BmiModel bmiModel = new BmiModel(80, 1.9);
        assertNotEquals("Your BMI is 24,69", bmiModel.calculate() );
    }

    @Test
    void calculateBmiWhenWeightLessThan40() {
        BmiModel bmiModel = new BmiModel(39, 1.9);
        assertEquals("Weight can't be less than 40 kg", bmiModel.calculate() );
    }

    @Test
    void calculateBmiWhenWeightMoreThan200() {
        BmiModel bmiModel = new BmiModel(201, 1.9);
        assertEquals("Weight can't be more than 200 kg", bmiModel.calculate() );
    }

    @Test
    void calculateBmiWhenHeightLessThan140cm() {
        BmiModel bmiModel = new BmiModel(40, 1.3);
        assertEquals("Height can't be less than 1,4 meter", bmiModel.calculate() );
    }

    @Test
    void calculateBmiWhenHeightMoreThan272cm() {
        BmiModel bmiModel = new BmiModel(41, 2.73);
        assertEquals("Height can't be greater than 2,7 meter", bmiModel.calculate() );
    }
}