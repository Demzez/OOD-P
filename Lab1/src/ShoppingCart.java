import java.util.ArrayList;
import java.util.List;

class ShoppingCart {
    private List<Coffee> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Coffee coffee) {
        items.add(coffee);
        System.out.println("Добавлено в корзину: " + coffee.getName());
    }

    public void showCart() {
        if (items.isEmpty()) {
            System.out.println("Корзина пуста.");
            return;
        }
        System.out.println("\n=== ВАША КОРЗИНА ===");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }

    public void checkout() {
        System.out.println("\n=== ВАШИ ПОКУПКИ ===");
        double yourPrice = 0;
        for (int i = 0; i < items.size(); i++) {
            yourPrice += items.get(i).calculatePrice();
            System.out.println((i + 1) + "." + items.get(i));
        }
        System.out.println("Сумма покупки: " + yourPrice + " руб.");
    }

    public double calculateTotal() {
        double total = 0;
        for (Coffee item : items) {
            total += item.calculatePrice();
        }
        return total;
    }

    public void clearCart() {
        items.clear();
        System.out.println("Корзина очищена.");
    }

    public List<Coffee> getItems() {
        return items;
    }
}