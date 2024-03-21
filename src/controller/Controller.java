package controller;

import model.Data;
import model.numbers.complexNumbers.ComplexNumber;
import view.UI;

public class Controller {
    private UI ui;
    private Data numbers;

    public Controller(UI ui) {
        this.ui = ui;
        numbers = new Data();
    }

    public ComplexNumber getByIndex(int index) {
        return numbers.get(index);
    }

    public int getCount() {
        return numbers.size();
    }

    public void createNumber(ComplexNumber complexNumber) {
        numbers.add(complexNumber);
    }

    public void createNumber(double realPart, double imaginaryPart) {
        numbers.add(new ComplexNumber(realPart, imaginaryPart));
    }

    public boolean removeNumber(int index) {
        if (numbers.size() > index) {
            numbers.delete(index);
            return true;
        }
        return false;
    }
}
