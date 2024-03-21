package model.numbers;

public abstract class Number {
    private double value;

    public double getValue() {
        return value;
    }

    public double squaring() {
        return Math.pow(value, 2);
    }
}
