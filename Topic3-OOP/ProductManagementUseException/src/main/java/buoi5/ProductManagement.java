package buoi5;

public class ProductManagement {
    private Product[] products = new Product[10];
    private int productCount = 0;

    public void addProduct(Product product) throws IllegalArgumentException {
        if(product == null) {
            throw new IllegalArgumentException("Product không được null nhé");
        }

        if(productCount>= products.length) {
            throw new IllegalArgumentException("List full rồi!");
        }

        products[productCount++] = product;

    }

    public Product getProductByID(int productID) throws ProductNotFoundException {
        for(int i = 0; i < productCount; i++) {
            if(productID  == products[i].getProductID()) {
                return products[i];
            }
        }

        throw new ProductNotFoundException("Không tìm thấy sản phẩm có ID: " + productID);
    }

    public void updateProductQuantity(int productID, int newQuantity) throws ProductNotFoundException {
        Product product = getProductByID(productID);

        product.setQuantityInStock(newQuantity);

        System.out.println("Sửa số lượng sản phẩm "+ product.getName() +" có id: " +productID +" thành công!");
    }

}
