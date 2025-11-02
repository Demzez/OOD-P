package model;

public abstract class Medicine {
    private String name;
    private double price;
    private int quantity;
    private String manufacturer;
    private String category;

    public Medicine(String name, double price, int quantity, String manufacturer, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
        this.category = category;
    }

    // Геттеры и сеттеры
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getManufacturer() { return manufacturer; }
    public String getCategory() { return category; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public abstract String getType();
}



