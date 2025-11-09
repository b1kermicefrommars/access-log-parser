import java.util.Scanner;

public  class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число:");
        int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число:");
        int secondNumber = new Scanner(System.in).nextInt();
        int sum = firstNumber + secondNumber;
        int rest = firstNumber - secondNumber;
        int mul = firstNumber * secondNumber;
        double quotient = firstNumber / secondNumber;

        System.out.println("Сумма: " + sum);
        System.out.println("Разница :" + rest);
        System.out.println("Произведение: " + mul);
        System.out.println("Частное: " + quotient);
    }
}