package model;

import model.numbers.complexNumbers.ComplexNumber;

import java.util.ArrayList;
import java.util.List;

public class Data {
    List<ComplexNumber> complexNumbers;

    public Data() {
        complexNumbers = new ArrayList<>();
    }

    public ComplexNumber get(int index) {
        return complexNumbers.get(index);
    }

    public void add(ComplexNumber complexNumber) {
        complexNumbers.add(complexNumber);
    }

    public void delete(int index) {
        if (complexNumbers.size() > index) complexNumbers.remove(index);
    }

    public int size() {
        return complexNumbers.size();
    }
}
