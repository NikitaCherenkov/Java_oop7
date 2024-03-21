package model.numbers.complexNumbers;

import model.numbers.complexNumbers.complexParts.ImaginaryPart;
import model.numbers.complexNumbers.complexParts.RealPart;

public class ComplexNumber {
    private RealPart realPart;
    private ImaginaryPart imaginaryPart;

    public ComplexNumber(double real, double imaginary) {
        realPart = new RealPart(real);
        imaginaryPart = new ImaginaryPart(imaginary);
    }

    ComplexNumber(RealPart real, ImaginaryPart imaginary) {
        realPart = real;
        imaginaryPart = imaginary;
    }

    @Override
    public String toString() {
        if (realPart.getValue() == 0) {
            if (imaginaryPart.getValue() < 0) return String.format("(%s)", imaginaryPart.toString());
            if (imaginaryPart.getValue() > 0) return String.format("%s", imaginaryPart.toString());
            return "0";
        }
        if (imaginaryPart.getValue() == 0) {
            if (realPart.getValue() < 0) return String.format("(%s)", realPart.toString());
            return realPart.toString();
        }
        StringBuilder sb = new StringBuilder();
        if (realPart.getValue() < 0) sb.append("(");
        sb.append(realPart.toString());
        if (imaginaryPart.getValue() > 0) sb.append("+");
        sb.append(imaginaryPart.toString());
        if (realPart.getValue() < 0) sb.append(")");
        return sb.toString();
    }

    public double getAbsoluteValue() {
        return Math.sqrt(Math.pow(realPart.getValue(), 2) + Math.pow(imaginaryPart.getValue(), 2));
    }

    public ComplexNumber plus(ComplexNumber complexNumber) {
        return new ComplexNumber(realPart.plus(complexNumber.realPart),
                imaginaryPart.plus(complexNumber.imaginaryPart));
    }

    public ComplexNumber minus(ComplexNumber complexNumber) {
        return new ComplexNumber(realPart.minus(complexNumber.realPart),
                imaginaryPart.minus(complexNumber.imaginaryPart));
    }

    public ComplexNumber multiplication(ComplexNumber complexNumber) {
        RealPart real = new RealPart(realPart.multiplication(complexNumber.realPart) -
                imaginaryPart.multiplication(complexNumber.imaginaryPart));
        ImaginaryPart imaginary = new ImaginaryPart(imaginaryPart.multiplication(complexNumber.realPart) +
                realPart.multiplication(complexNumber.imaginaryPart));
        return new ComplexNumber(real, imaginary);
    }

    public ComplexNumber divide(ComplexNumber complexNumber) {
        RealPart real = new RealPart((realPart.multiplication(complexNumber.realPart) +
                imaginaryPart.multiplication(complexNumber.imaginaryPart)) /
                (complexNumber.realPart.squaring() + complexNumber.imaginaryPart.squaring()));
        ImaginaryPart imaginary = new ImaginaryPart((imaginaryPart.multiplication(complexNumber.realPart) -
                realPart.multiplication(complexNumber.imaginaryPart)) /
                (complexNumber.realPart.squaring() + complexNumber.imaginaryPart.squaring()));
        return new ComplexNumber(real, imaginary);
    }
}
