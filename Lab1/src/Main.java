import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StorageOfCoffee storage = new StorageOfCoffee();
        ShoppingCart cart = new ShoppingCart();
        boolean isRunning = true;
        String login;

        outerLoop:
        while (true) {
            while (true) {
                System.out.println("Добро пожаловать. Введите свой логин:");
                System.out.println("Доступные логины: admin, person, end-для окончания работы.");
                login = scanner.nextLine();
                if (login.equals("admin") || login.equals("person") || login.equals("end")) {
                    break;
                }
                System.out.println("Неверный логин. Попробуйте снова.");
            }

            switch (login) {
                case "admin":
                    adminMenu(scanner, storage);
                    break;
                case "person":
                    userMenu(scanner, storage, cart);
                    break;
                case "end":
                    break outerLoop;
            }
        }
    }

    // Меню администратора
    private static void adminMenu(Scanner scanner, StorageOfCoffee storage) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== АДМИНИСТРАТОРСКОЕ МЕНЮ ===");
            System.out.println("1. Показать склад");
            System.out.println("2. Завести новый товар.");
            System.out.println("3. Очистить склад");
            System.out.println("4. Добавить товар вручную.");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            switch (choice) {
                case 1:
                    storage.showStorage();
                    break;
                case 2:
                    initializeStorage(storage);
                    System.out.println("Товар довезен!");
                    break;
                case 3:
                    storage.clearStorage();
                    break;
                case 4:
                    addProductManually(scanner, storage);
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Выход из администраторского меню.");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // Меню пользователя
    private static void userMenu(Scanner scanner, StorageOfCoffee storage, ShoppingCart cart) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== ПОЛЬЗОВАТЕЛЬСКОЕ МЕНЮ ===");
            System.out.println("1. Просмотреть ассортимент");
            System.out.println("2. Добавить товар в корзину");
            System.out.println("3. Просмотреть корзину");
            System.out.println("4. Очистить корзину");
            System.out.println("5. Оформить покупку");
            System.out.println("6. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            switch (choice) {
                case 1:
                    storage.showStorage();
                    break;
                case 2:
                    addToCart(scanner, storage, cart);
                    break;
                case 3:
                    cart.showCart();
                    System.out.printf("Общая сумма: %.2f руб.\n", cart.calculateTotal());
                    break;
                case 4:
                    cart.clearCart();
                    break;
                case 5:
                    cart.checkout();
                    cart.clearCart();
                    isRunning = false;
                    break;
                case 6:
                    isRunning = false;
                    System.out.println("Выход из пользовательского меню.");
                    for (int i = 0; i < cart.getItems().size(); i++) {
                        storage.addProduct(cart.getItems().get(i));
                    }
                    cart.clearCart();
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void initializeStorage(StorageOfCoffee storage) {
        // Арабика - 10 позиций
        storage.addProduct(new Arabica("Бразилия", 15.0, 500, true));
        storage.addProduct(new Arabica("Колумбия", 18.0, 250, false));
        storage.addProduct(new Arabica("Эфиопия", 20.0, 1000, true));
        storage.addProduct(new Arabica("Кения", 22.0, 300, false));
        storage.addProduct(new Arabica("Гватемала", 16.0, 750, true));
        storage.addProduct(new Arabica("Коста-Рика", 19.0, 400, false));
        storage.addProduct(new Arabica("Перу", 14.0, 600, true));
        storage.addProduct(new Arabica("Мексика", 13.0, 350, false));
        storage.addProduct(new Arabica("Танзания", 21.0, 200, true));
        storage.addProduct(new Arabica("Ямайка", 25.0, 150, false));

        // Робуста - 10 позиций
        storage.addProduct(new Robusta("Вьетнам", 12.0, 500, true));
        storage.addProduct(new Robusta("Индия", 10.0, 250, false));
        storage.addProduct(new Robusta("Индонезия", 11.0, 800, true));
        storage.addProduct(new Robusta("Уганда", 9.0, 450, false));
        storage.addProduct(new Robusta("Кот-д'Ивуар", 8.0, 700, true));
        storage.addProduct(new Robusta("Камерун", 10.5, 300, false));
        storage.addProduct(new Robusta("Мадагаскар", 13.0, 550, true));
        storage.addProduct(new Robusta("Филиппины", 11.5, 400, false));
        storage.addProduct(new Robusta("Таиланд", 14.0, 350, true));
        storage.addProduct(new Robusta("Бразилия", 12.5, 600, false));

        // Сублимированный кофе - 8 позиций
        storage.addProduct(new Freeze("Швейцария", 8.0, 200, true));
        storage.addProduct(new Freeze("Германия", 7.5, 250, false));
        storage.addProduct(new Freeze("Италия", 9.0, 150, true));
        storage.addProduct(new Freeze("Франция", 8.5, 300, false));
        storage.addProduct(new Freeze("США", 7.0, 400, true));
        storage.addProduct(new Freeze("Япония", 10.0, 100, false));
        storage.addProduct(new Freeze("Колумбия", 8.2, 350, true));
        storage.addProduct(new Freeze("Бразилия", 7.8, 500, false));

        // Порошковый кофе - 8 позиций
        storage.addProduct(new Spray("США", 6.0, 150, false));
        storage.addProduct(new Spray("Китай", 5.5, 200, true));
        storage.addProduct(new Spray("Россия", 6.5, 250, false));
        storage.addProduct(new Spray("Польша", 5.8, 300, true));
        storage.addProduct(new Spray("Турция", 7.0, 100, false));
        storage.addProduct(new Spray("Испания", 6.2, 350, true));
        storage.addProduct(new Spray("Великобритания", 6.8, 400, false));
        storage.addProduct(new Spray("Нидерланды", 5.9, 450, true));

        // Дополнительные премиум позиции - 4 позиции
        storage.addProduct(new Arabica("Гавайи", 35.0, 100, true));
        storage.addProduct(new Arabica("Ямайка Блю Маунтин", 45.0, 250, false));
        storage.addProduct(new Freeze("Швейцария Премиум", 15.0, 100, true));
        storage.addProduct(new Spray("Карибы", 100.0, 200, false));
    }

    private static void addProductManually(Scanner scanner, StorageOfCoffee storage) {
        String typeOfCoffee;
        while (true) {
            System.out.println("Введите вид кофе:");
            typeOfCoffee  = scanner.nextLine();
            if (typeOfCoffee.equals("Arabica") || typeOfCoffee.equals("Robusta") || typeOfCoffee.equals("Spray") || typeOfCoffee.equals("Freeze")) {
                break;
            }
            else {
                System.out.println("Такого кофе не существует.");
            }
        }
        System.out.println("Введите строну происхождения:");
        String countryOfOrigin = scanner.nextLine();
        System.out.println("Введите начальную цену:");
        double basePrice = scanner.nextDouble();
        System.out.println("Введите вес банки/покета:");
        int weight = scanner.nextInt();
        System.out.println("Нужна ли дополнительная обработка(true/false):");
        boolean isOk = scanner.nextBoolean();

        switch (typeOfCoffee){
            case "Arabica":
                storage.addProduct(new Arabica(countryOfOrigin, basePrice, weight, isOk));
                break;
            case "Robusta":
                storage.addProduct(new Robusta(countryOfOrigin, basePrice, weight, isOk));
                break;
            case "Spray":
                storage.addProduct(new Spray(countryOfOrigin, basePrice, weight, isOk));
                break;
            case "Freeze":
                storage.addProduct(new Freeze(countryOfOrigin, basePrice, weight, isOk));
                break;
        }

    }

    private static void addToCart(Scanner scanner, StorageOfCoffee storage, ShoppingCart cart) {
        int number;
        while (true) {
            System.out.println("Введите номер кофе для покупки:");
            number = scanner.nextInt();
            if (number-1 < storage.getProducts().size() && number >= 0) {
                break;
            }
            else {
                System.out.println("Товара под таким номером не существует.");
            }
        }
        cart.addItem(storage.getProducts().get(number-1));
        storage.BuySomeProduct(number);
    }

}

