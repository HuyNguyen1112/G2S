package buoi4;

import java.util.Scanner;

public class Program {
    private Product[] products;
    private byte numOfProduct;
    private static final byte MAX = 100;

    public Program() {
        products = new Product[MAX];
        numOfProduct = 0;
    }

    public void addProduct(Product product) {
        if (numOfProduct < MAX) {
            products[numOfProduct++] = product;
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Cannot add more products. Storage is full!");
        }
    }

    public int inputId(Scanner sc) {

        String input;
        int id;
        do {
            do {
                System.out.print("Enter ID: ");
                input = sc.nextLine();
            }
            while (isIdEmpty(input));

            id = Integer.parseInt(input);
        }
        while (isIdDulicated(id));
        return id;
    }

    public String inputName(Scanner sc) {
        System.out.print("Enter Name: ");
        return sc.nextLine();
    }

    public float inputPrice(Scanner sc) {
        System.out.print("Enter Price: ");
        return Float.parseFloat(sc.nextLine());
    }

    public String inputBrand(Scanner sc) {
        System.out.print("Enter Brand: ");
        return sc.nextLine();
    }

    public String inputSize(Scanner sc) {
        System.out.print("Enter Size: ");
        return sc.nextLine();
    }

    public boolean isIdEmpty (String input) {
        if(input.trim().isEmpty()) {
            System.out.println("Không được để trống ID!");
            return true;
        }
        return false;
    }

    public boolean isIdDulicated (int id) {
        for (int i = 0; i<numOfProduct; i++) {
            if(id == products[i].id) {
                System.out.println("ID đã tồn tại!");
                return true;
            }
        }
        return false;
    }

    public void displayProduct() {
        if (numOfProduct == 0) {
            System.out.println("No products available.");
            return;
        }
        for (int i = 0; i < numOfProduct; i++) {
            System.out.println(products[i]);
        }
    }

    public Product findProduct(int id) {
        for (int i = 0; i < numOfProduct; i++) {
            if (products[i].id == id) {
                return products[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Program p = new Program();
        Scanner sc = new Scanner(System.in);
        final String ADD = "1";
        final String DISPLAY = "2";
        final String FIND = "3";
        final String EXIT ="0";
        String choice;
        do {
            System.out.println("1.Add Products");
            System.out.println("3.Display Products");
            System.out.println("4.Find Product");
            System.out.println("0.Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextLine();

            switch (choice) {
                case ADD:

                    System.out.print("Choose product type: 1.Electronics  2.Clothing");
                    byte type = Byte.parseByte(sc.nextLine());

                    int id = p.inputId(sc);
                    String name = p.inputName(sc);
                    float price = p.inputPrice(sc);

                    switch (type) {
                        case 1:
                            String brand = p.inputBrand(sc);
                            p.addProduct(new Electronics(id, name, price, brand));

                        case 2:
                            String size = p.inputSize(sc);
                            p.addProduct(new Clothing(id, name, price, size));
                    }
                    break;

                case DISPLAY:
                    p.displayProduct();
                    break;

                case FIND:
                    System.out.print("Enter product ID to find: ");
                    int searchId = Integer.parseInt(sc.nextLine());

                    Product foundProduct = p.findProduct(searchId);

                    if (foundProduct != null) {
                        System.out.println(foundProduct);
                    }

                    else {
                        System.out.println("Product not found!");
                    }

                    break;

                case EXIT:
                    System.out.println("Exiting ...");
            }
        }
        while (!choice.equals(EXIT));
    }

}
