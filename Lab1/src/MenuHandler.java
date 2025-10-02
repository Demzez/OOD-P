import java.awt.desktop.SystemSleepEvent;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

class MenuHandler {
    private Map<Integer, Runnable> adminMenuActions;
    private Map<Integer, Runnable> userMenuActions;
    private Map<Integer, Runnable> userAPIMethods;

    public MenuHandler(Scanner scanner, StorageOfCoffee storage, ShoppingCart cart) {
        initializeAdminMenu(scanner, storage);
        initializeUserMenu(scanner, storage, cart);
        initializeUserAPI(scanner, storage);
    }

    private void initializeAdminMenu(Scanner scanner, StorageOfCoffee storage) {
        adminMenuActions = new HashMap<>();
        adminMenuActions.put(1, () -> storage.showStorage());
        adminMenuActions.put(2, () -> {
            initializeStorage(storage);
            System.out.println("Товар довезен!");
        });
        adminMenuActions.put(3, () -> storage.clearStorage());
        adminMenuActions.put(4, () -> addProductManually(scanner, storage));
    }
    private void initializeUserMenu(Scanner scanner, StorageOfCoffee storage, ShoppingCart cart) {
        userMenuActions = new HashMap<>();
        userMenuActions.put(1, () -> storage.showStorage());
        userMenuActions.put(2, () -> addToCart(scanner, storage, cart));
        userMenuActions.put(3, () -> {
            cart.showCart();
            System.out.printf("Общая сумма: %.2f руб.\n", cart.calculateTotal());
        });
        userMenuActions.put(4, () -> cart.clearCart());
        userMenuActions.put(5, () -> {
            cart.checkout();
            cart.clearCart();
        });
    }
    private void initializeUserAPI(Scanner scanner, StorageOfCoffee storage) {
        userAPIMethods = new HashMap<>();
        userAPIMethods.put(1, () -> storage.sortProductsByPrice());
        userAPIMethods.put(2, () -> {
            System.out.println("Введите максимальную цену для фильтрации:");
            double maxPrice = scanner.nextDouble();
            scanner.nextLine();
            List<Coffee> filtered = storage.filterProductsByMaxPrice(maxPrice);
            filtered.forEach(System.out::println); // ссылка на метод (эквивалентно x -> System.out.println(x))
        });
        userAPIMethods.put(3, () -> {
            Map<String, Double> grouped = storage.getProductPriceMap();
            grouped.forEach((country, price) -> {
                System.out.println("Кофе: " + country + " (" +price+ "руб. )");
            });
        });
        userAPIMethods.put(4, () -> {
            Map<String, List<Coffee>> grouped = storage.groupProductsByCountry();
            grouped.forEach((country, coffeeList) -> {
                System.out.println("Страна: " + country + " (" + coffeeList.size() + " товаров)");
            });
        });
        userAPIMethods.put(5, () -> {
            storage.getUniqueTypesOfCoffee().forEach(System.out::println);
        });
    }

    public void executeAdminAction(int choice) {
        adminMenuActions.getOrDefault(choice,
                () -> System.out.println("Неверный выбор. Попробуйте снова.")).run();
    }
    public void executeUserAction(int choice) {
        userMenuActions.getOrDefault(choice,
                () -> System.out.println("Неверный выбор. Попробуйте снова.")).run();
    }
    public void executeUserAPI(int choice) {
        userAPIMethods.getOrDefault(choice,
                () -> System.out.println("Неверный выбор. Попробуйте снова.")).run();
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
        System.out.println("Введите страну происхождения:");
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