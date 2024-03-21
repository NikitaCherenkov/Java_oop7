package model.numbers.complexNumbers.complexParts;

import model.numbers.Mathematical;
import model.numbers.Number;

import java.text.DecimalFormat;

public class RealPart extends Number implements Mathematical {
    private double value;

    public double getValue() {
        return value;
    }

    public double squaring() {
        return Math.pow(value, 2);
    }

    public RealPart(double real) {
        value = real;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat();
        format.setDecimalSeparatorAlwaysShown(false);
        return String.format("%s", format.format(value));
    }

    @Override
    public double getAbsoluteValue() {
        return Math.abs(value);
    }

    @Override
    public double plus(double number) {
        return value + number;
    }

    public double plus(Number number) {
        return value + number.getValue();
    }

    @Override
    public double minus(double number) {
        return value - number;
    }

    public double minus(Number number) {
        return value - number.getValue();
    }

    @Override
    public double multiplication(double number) {
        return value * number;
    }

    public double multiplication(Number number) {
        return value * number.getValue();
    }
}
