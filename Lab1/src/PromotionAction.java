import java.util.Random;
import java.util.Scanner;

public class PromotionAction implements Runnable{
    private StorageOfCoffee storage;
    private ShoppingCart cart;
    private boolean running = true;
    private java.util.function.Supplier<String> loginSupplier;
    Random random = new Random();

    PromotionAction(StorageOfCoffee storage, ShoppingCart cart, java.util.function.Supplier<String> loginSupplier) {
        this.storage = storage;
        this.cart = cart;
        this.loginSupplier = loginSupplier;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(60000);
                String currentLogin = loginSupplier.get();

                if ("person".equals(currentLogin)) {
                    Promotion();
                }
            } catch (InterruptedException e) {
                System.out.println("Акция прервана");
            }
        }
    }

    private synchronized void Promotion() {

        if (storage.getProducts().isEmpty()) {
            System.out.println("На складе нет товаров для акции");
            return;
        }

        int numberOfPrize = random.nextInt(storage.getProducts().size());
        Coffee randomCoffee = storage.getProducts().get(numberOfPrize);

        System.out.println("\nСЕКРЕТНАЯ АКЦИЯ!");

        try {
            int userGuess = random.nextInt(10) + 1;
            Thread.sleep(300);
            int secretNumber = random.nextInt(10) + 1;

            if (userGuess == secretNumber) {
                System.out.println("Вы невероятный везунчик получите кофе " + randomCoffee + " в подарок!");

                cart.addItem(randomCoffee);
                storage.BuySomeProduct(numberOfPrize);
            } else {
                System.out.println("Увы! Вам не повезло.");
                System.out.println("Но не беда, акция проводиться каждые 30 секунд!");
            }

        } catch (Exception e) {
            System.out.println("❌ Ошибка акции! Шанс потерян.");
        }
    }
}
