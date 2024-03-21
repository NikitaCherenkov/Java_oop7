package view;

import controller.Controller;
import utils.logger.Log;
import model.numbers.complexNumbers.ComplexNumber;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UI {
    private static final Logger log = Log.logger(UI.class.getName());;
    Scanner in;
    Controller controller;
    boolean validCommand;

    public UI() {
        in = new Scanner(System.in);
        controller = new Controller(this);
        validCommand = false;
    }

    public void run() {
        log.log(Level.INFO, "Application started");
        while (true) {
            PrintHelp();
            Commands com = getCommand();
            if (com == Commands.EXIT) {
                log.log(Level.INFO, "Application stopped");
                return;
            }
            if (com == Commands.ADD) commandAdd();
            if (com == Commands.DELETE) commandDelete();
            if (com == Commands.READ) commandRead();
            if (com == Commands.PLUS) commandPlus();
            if (com == Commands.MINUS) commandMinus();
            if (com == Commands.MULT) commandMult();
            if (com == Commands.DIVIDE) commandDivide();
        }
    }

    private void commandRead() {
        log.log(Level.INFO, "execute Read method");
        System.out.printf("Количество чисел: %d%n", controller.getCount());
        for (int i = 0; i < controller.getCount(); i++) {
            System.out.printf("%d : %s%n", i + 1, controller.getByIndex(i));
        }
    }

    private void commandAdd() {
        log.log(Level.INFO, "execute Add method");
        String realString = consoleRead("Введите вещественную часть комплексного числа: ");
        String imaginaryString = consoleRead("Введите мнимую часть комплексного числа: ");
        try {
            double realPart = Double.parseDouble(realString);
            double imaginaryPart = Double.parseDouble(imaginaryString);
            controller.createNumber(realPart, imaginaryPart);
        }
        catch (Exception e) {
            log.log(Level.WARNING, "Exception", e);
            e.printStackTrace();
        }
    }

    private void commandDelete() {
        log.log(Level.INFO, "execute Delete method");
        String removeIndex = consoleRead("Введите индекс удаляемого числа: ");
        System.out.printf("Удалено число %s : ", removeIndex);
        try {
            int toRemove = Integer.parseInt(removeIndex) - 1;
            System.out.printf("%s%n", controller.getByIndex(toRemove).toString());
            if (!controller.removeNumber(toRemove)) System.err.println("Указанный индекс отсутствует");
        }
        catch (Exception e) {
            log.log(Level.WARNING, "Exception", e);
            e.printStackTrace();
        }
    }

    private void commandPlus() {
        log.log(Level.INFO, "execute Plus method");
        try {
            String firstValue = consoleRead("Введите индекс первого слагаемого: ");
            ComplexNumber firstCN = controller.getByIndex(Integer.parseInt(firstValue) - 1);
            System.out.printf("Первое слагаемое %s%n", firstCN);
            String secondValue = consoleRead("Введите индекс второго слагаемого: ");
            ComplexNumber secondCN = controller.getByIndex(Integer.parseInt(secondValue) - 1);
            System.out.printf("Второе слагаемое %s%n", secondCN);
            ComplexNumber result = firstCN.plus(secondCN);
            System.out.printf("Результат сложения: %s%n", result.toString());
            System.out.printf("добавлен с индексом %d%n", controller.getCount() + 1);
            controller.createNumber(result);
        }
        catch (Exception e) {
            log.log(Level.WARNING, "Exception", e);
            e.printStackTrace();
        }
    }

    private void commandMinus() {
        log.log(Level.INFO, "execute Minus method");
        try {
            String firstValue = consoleRead("Введите индекс уменьшаемого: ");
            ComplexNumber firstCN = controller.getByIndex(Integer.parseInt(firstValue) - 1);
            System.out.printf("Уменьшаемое: %s%n", firstCN);
            String secondValue = consoleRead("Введите индекс вычитаемого: ");
            ComplexNumber secondCN = controller.getByIndex(Integer.parseInt(secondValue) - 1);
            System.out.printf("Вычитаемое %s%n", secondCN);
            ComplexNumber result = firstCN.minus(secondCN);
            System.out.printf("Разность: %s%n", result);
            System.out.printf("элемент добавлен с индексом %d%n", controller.getCount() + 1);
            controller.createNumber(result);
        }
        catch (Exception e) {
            log.log(Level.WARNING, "Exception", e);
            e.printStackTrace();
        }
    }

    private void commandMult() {
        log.log(Level.INFO, "execute Mult method");
        try {
            String firstValue = consoleRead("Введите индекс первого множителя: ");
            ComplexNumber firstCN = controller.getByIndex(Integer.parseInt(firstValue) - 1);
            System.out.printf("Первый множитель: %s%n", firstCN);
            String secondValue = consoleRead("Введите индекс второго множителя: ");
            ComplexNumber secondCN = controller.getByIndex(Integer.parseInt(secondValue) - 1);
            System.out.printf("Второй множитель %s%n", secondCN);
            ComplexNumber result = firstCN.multiplication(secondCN);
            System.out.printf("Произведение: %s%n", result);
            System.out.printf("элемент добавлен с индексом %d%n", controller.getCount() + 1);
            controller.createNumber(result);
        }
        catch (Exception e) {
            log.log(Level.WARNING, "Exception", e);
            e.printStackTrace();
        }
    }

    private void commandDivide() {
        log.log(Level.INFO, "execute Divide method");
        try {
            String firstValue = consoleRead("Введите индекс делимого: ");
            ComplexNumber firstCN = controller.getByIndex(Integer.parseInt(firstValue) - 1);
            System.out.printf("Делимое: %s%n", firstCN);
            String secondValue = consoleRead("Введите индекс делителя: ");
            ComplexNumber secondCN = controller.getByIndex(Integer.parseInt(secondValue) - 1);
            System.out.printf("Делитель %s%n", secondCN);
            ComplexNumber result = firstCN.divide(secondCN);
            System.out.printf("Частное: %s%n", result);
            System.out.printf("элемент добавлен с индексом %d%n", controller.getCount() + 1);
            controller.createNumber(result);
        }
        catch (Exception e) {
            log.log(Level.WARNING, "Exception", e);
            e.printStackTrace();
        }
    }

    private String consoleRead(String message) {
        System.out.print(message);
        return in.nextLine();
    }

    private Commands getCommand() {
        Commands com;
        validCommand = false;
        while (!validCommand) {
            String command = consoleRead("Введите команду: ").toUpperCase();
            try {
                com = Commands.valueOf(command);
                validCommand = true;
                return com;
            }
            catch (IllegalArgumentException e) {
                System.err.println("Указанная команда отсутствует");
            }
        }
        return null;
    }

    private void PrintHelp() {
        System.out.println("Команды для работы с комплексными числами:");
        System.out.println("ADD - ввести новое число");
        System.out.println("READ - вывести список всех чисел");
        System.out.println("DELETE - удалить введённое число");
        System.out.println("PLUS - сложить два числа");
        System.out.println("MINUS - вычитание одного числа из другого");
        System.out.println("MULT - умножение двух чисел");
        System.out.println("DIVIDE - деление двух чисел");
        System.out.println("EXIT - выход");
    }
}
