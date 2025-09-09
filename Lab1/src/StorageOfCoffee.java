import java.util.HashMap;
import java.util.Map;

class StorageOfCoffee {
    private HashMap<String, Integer> products;

    public StorageOfCoffee() { products = new HashMap<>(); }

    public void Refill() {
        products.put ("Arabica", 22222);
        products.put ("Robusta", 22222);
        products.put ("Spray", 22222);
        products.put ("Freeze", 22222);
    }

    public void ShowStore() {
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    public void BuySomeQuantity(String nameOfCoffee, Integer subtractQuantity) {
        Integer currentQuantity = products.get(nameOfCoffee);
        if (currentQuantity - subtractQuantity >= 0) {
            products.replace(nameOfCoffee, currentQuantity - subtractQuantity);
        }
        else {
            System.out.println("Покупка отклонена, на складе не достаточно товара.");
        }
    }

    public void AddCurrentCoffee(String nameOfCoffee, Integer subtractQuantity) {
        Integer currentQuantity = products.get(nameOfCoffee);
        products.replace(nameOfCoffee, currentQuantity + subtractQuantity);
    }

    public HashMap<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, Integer> products) { this.products = products; }

}