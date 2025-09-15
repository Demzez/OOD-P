import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StorageOfCoffee storage = new StorageOfCoffee();
        ShoppingCart cart = new ShoppingCart();
        MenuHandler menu = new MenuHandler(scanner, storage, cart);
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
                    adminMenu(scanner, storage, menu);
                    break;
                case "person":
                    userMenu(scanner, storage, cart, menu);
                    break;
                case "end":
                    break outerLoop;
            }
        }
    }

    // Меню администратора
    private static void adminMenu(Scanner scanner, StorageOfCoffee storage, MenuHandler menu) {
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

            if (choice == 5) {
                isRunning = false;
                System.out.println("Выход из администраторского меню.");
                break;
            }
            else {
                menu.executeAdminAction(choice);
            }
        }
    }

    // Меню пользователя
    private static void userMenu(Scanner scanner, StorageOfCoffee storage, ShoppingCart cart, MenuHandler menu) {
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


            if (choice == 5) {
                menu.executeUserAction(5);
                isRunning = false;
            } else if (choice == 6) {
                isRunning = false;
                System.out.println("Выход из пользовательского меню.");
                for (int i = 0; i < cart.getItems().size(); i++) {
                    storage.addProduct(cart.getItems().get(i));
                }
                cart.clearCart();
            }
            else {
                menu.executeUserAction(choice);
            }
        }
    }


}

