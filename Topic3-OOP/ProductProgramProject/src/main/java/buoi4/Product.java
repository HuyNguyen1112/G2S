package buoi4;

import java.util.Scanner;

public abstract class Product {
    protected int id;
    protected String name;
    protected float price;

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
