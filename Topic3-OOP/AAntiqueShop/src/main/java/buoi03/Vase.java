package buoi03;

import java.util.Scanner;

public class Vase extends Item {
    private int height;
    private String material;

    public Vase() {
    }

    public Vase(String id, int value, String creator, int height, String material) {
        super(id, value, creator);
        this.height = height;
        this.material = material;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public void input() {
        super.input();

        Scanner sc = new Scanner(System.in);

        inputHeight(sc);

        inputMaterial(sc);
    }

    public void inputHeight(Scanner sc) {
        do {
            System.out.print("Enter height: ");
            height = Integer.parseInt(sc.nextLine());
        }
        while (isHeightLessThanZeroOrGreaterThanTwoThousand(height));

    }

    public void inputMaterial(Scanner sc) {
        do {
            System.out.print("Enter material: ");
            material = sc.nextLine();
        }
        while (isMaterialEmpty(material));
    }

    public boolean isHeightLessThanZeroOrGreaterThanTwoThousand(int height) {
        if (height<0 || height>2000) {
            System.out.println("Height phải >= 0 và <=2000");
            return true;
        }
        return false;
    }

    public boolean isMaterialEmpty(String material) {
        if (material.trim().isEmpty()) {
            System.out.println("Material không được để trống");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Vase{" +
                "id='" + id + '\'' +
                ", 1.value=" + value +
                ", 2.creator='" + creator + '\'' +
                ", 3.height=" + height +
                ", 4.material='" + material + '\'' +
                '}';
    }
}
