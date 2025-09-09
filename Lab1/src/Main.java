import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();
        boolean isRunning = true;

        System.out.println("Добро пожаловать в магазин кофе!");



        while (isRunning) {
            System.out.println("Введите логин:");
            String login = scanner.nextLine();

            if (login.equals("admin")) {
                while (isRunning) {
                    System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
                    System.out.println("1. Добавить Арабику в корзину");
                    System.out.println("2. Добавить Робусту в корзину");
                    System.out.println("3. Добавить Растворимый кофе в корзину");
                    System.out.println("4. Показать корзину");
                    System.out.println("5. Рассчитать итоговую сумму");
                    System.out.println("6. Очистить корзину");
                    System.out.println("0. Выйти");
                    System.out.print("Выберите пункт меню: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера

                    switch (choice) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 0:
                            isRunning = false;
                            System.out.println("Спасибо за посещение! До свидания!");
                            break;

                        default:
                            System.out.println("Неверный пункт меню. Попробуйте снова.");
                    }
                }
            } else if (login.equals("user")) {
                while (isRunning) {
                    System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
                    System.out.println("1. Добавить Арабику в корзину");
                    System.out.println("2. Добавить Робусту в корзину");
                    System.out.println("3. Добавить Растворимый кофе в корзину");
                    System.out.println("4. Показать корзину");
                    System.out.println("5. Рассчитать итоговую сумму");
                    System.out.println("6. Очистить корзину");
                    System.out.println("0. Выйти");
                    System.out.print("Выберите пункт меню: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера

                    switch (choice) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 0:
                            isRunning = false;
                            System.out.println("Спасибо за посещение! До свидания!");
                            break;

                        default:
                            System.out.println("Неверный пункт меню. Попробуйте снова.");
                    }
                }
            }
            else {
                System.out.println("Неверный ввод.");
            }
        }
        scanner.close();
    }
}