package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;

public class BmiModel {
    private final double weight;
    private final double height;

    private static final Logger logger = LogManager.getLogger();

    /**
     * Check the weight and height.
     * If it's in valid range it will calculate the bmi.
     * @return a String value with info message or the bmi
     */
    public String calculate() {
        if (getWeight() <= 40) {
            logger.info("Weight can't be less than 40 kg");
            return "Weight can't be less than 40 kg";
        } if (getWeight() >= 200) {
            logger.info("Weight can't be more than 200 kg");
            return "Weight can't be more than 200 kg";
        } if (getHeight() <= 1.4) {
            logger.info("Height can't be less than 1,4 meter");
            return "Height can't be less than 1,4 meter";
        } if (getHeight() >= 2.7) {
            logger.info("Height can't be greater than 2,7 meter");
            return "Height can't be greater than 2,7 meter";
        }
        logger.info("BMI is calculated successfully");
        DecimalFormat df = new DecimalFormat("##.##");
        return "Your BMI is " + df.format(getWeight() / Math.pow(getHeight(), 2));
    }

    public BmiModel(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

}
