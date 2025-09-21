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
            System.out.println((i + 1) + "." + products.get(i));
        }
    }

    public void BuySomeProduct(int number) {
        if (number-1 <= products.size() && number >= 0) {
            products.remove(products.get(number-1));
        }
        else {
            System.out.println("Покупка отклонена, на складе нет товара под данным номером.");
        }
    }

    public void clearStorage() {
        products.clear();
        System.out.println("Хранилище очищено.");
    }

    public List<Coffee> getProducts() {
        return products;
    }


    // Многопоточные сортироки
    public synchronized void sortAscending() {
        products.sort((c1, c2) -> Double.compare(c1.calculatePrice(), c2.calculatePrice()));
    }

    public synchronized void sortDescending() {
        products.sort((c1, c2) -> Double.compare(c2.calculatePrice(), c1.calculatePrice()));
    }

    // Потоковая функция добавления акционерных товаров
    public synchronized void PromotionPlus() {

    }
}