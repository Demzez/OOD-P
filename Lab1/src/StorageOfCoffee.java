import java.util.ArrayList;
import java.util.List;

class StorageOfCoffee {
    private List<Coffee> products;

    public StorageOfCoffee() { products = new ArrayList<>(); }

    public void addProduct(Coffee coffee) {
        products.add(coffee);
    }

    public void showStorage() {
        if (products.isEmpty()) {
            System.out.println("Полки пусты.");
            return;
        }
        System.out.println("\n=== Ассортимент Кофе ===");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }
    }

    public void BuySomeProduct(Coffee coffee) {
        if (products.contains(coffee)) {
            products.remove(coffee);
        }
        else {
            System.out.println("Покупка отклонена, на складе нет товара.");
        }
    }

    public void clearStorage() {
        products.clear();
        System.out.println("Хранилище очищено.");
    }
}