import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Objects;

// Главный класс приложения
public class CoffeeShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();
        boolean isRunning = true;

        System.out.println("Добро пожаловать в магазин кофе!");

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
                    System.out.print("Введите страну происхождения: ");
                    String arabicaCountry = scanner.nextLine();
                    System.out.print("Введите базовую цену за кг: ");
                    double arabicaPrice = scanner.nextDouble();
                    System.out.print("Введите вес (кг): ");
                    double arabicaWeight = scanner.nextDouble();
                    System.out.print("Мытый процесс? (true/false): ");
                    boolean isWashed = scanner.nextBoolean();
                    cart.addItem(new Arabica(arabicaCountry, arabicaPrice, arabicaWeight, isWashed));
                    break;

                case 2:
                    System.out.print("Введите страну происхождения: ");
                    String robustaCountry = scanner.nextLine();
                    System.out.print("Введите базовую цену за кг: ");
                    double robustaPrice = scanner.nextDouble();
                    System.out.print("Введите вес (кг): ");
                    double robustaWeight = scanner.nextDouble();
                    System.out.print("Введите уровень кофеина (1-10): ");
                    int caffeineLevel = scanner.nextInt();
                    cart.addItem(new Robusta(robustaCountry, robustaPrice, robustaWeight, caffeineLevel));
                    break;

                case 3:
                    System.out.print("Введите название бренда: ");
                    String brandName = scanner.nextLine();
                    System.out.print("Введите цену за банку: ");
                    double instantPrice = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Введите тип (сублимированный/порошковый): ");
                    String instantType = scanner.nextLine();
                    System.out.print("Введите объем банки (г): ");
                    int volume = scanner.nextInt();
                    cart.addItem(new InstantCoffee(brandName, instantPrice, instantType, volume));
                    break;

                case 4:
                    cart.showCart();
                    break;

                case 5:
                    double total = cart.calculateTotal();
                    System.out.printf("Общая сумма вашего заказа: %.2f руб.\n", total);
                    break;

                case 6:
                    cart.clearCart();
                    break;

                case 0:
                    isRunning = false;
                    System.out.println("Спасибо за посещение! До свидания!");
                    break;

                default:
                    System.out.println("Неверный пункт меню. Попробуйте снова.");
            }
        }
        scanner.close();
    }

}