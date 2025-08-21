package buoi5;

public class MainProgram {
    public static void main(String[] args) {
        ProductManagement pm = new ProductManagement();

        try {
            pm.addProduct(new Product(101, "Laptop", 999.99, 10));
            pm.addProduct(new Product(102, "Smartphone", 799.99, 20));
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


        try {
            Product product = pm.getProductByID(103);
            System.out.print(product);
        }
        catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            pm.updateProductQuantity(102, 15);
            System.out.println(pm.getProductByID(102));
        }
        catch (ProductNotFoundException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
