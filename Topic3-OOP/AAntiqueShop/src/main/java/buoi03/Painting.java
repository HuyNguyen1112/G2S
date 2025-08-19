package buoi03;

import java.util.Scanner;

public class Painting extends Item {
    private int height;
    private int width;
    private boolean isWaterColor;
    private boolean isFramed;

    public Painting() {
    }

    public Painting(String id, int value, String creator, int height, int width, boolean isWaterColor, boolean isFramed) {
        super(id, value, creator);
        this.height = height;
        this.width = width;
        this.isWaterColor = isWaterColor;
        this.isFramed = isFramed;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isWaterColor() {
        return isWaterColor;
    }

    public void setWaterColor(boolean waterColor) {
        isWaterColor = waterColor;
    }

    public boolean isFramed() {
        return isFramed;
    }

    public void setFramed(boolean framed) {
        isFramed = framed;
    }

    @Override
    public void input() {
        super.input();

        Scanner sc = new Scanner(System.in);

        inputHeight(sc);

        inputWidth(sc);

        isWaterColor(sc);

        isFramed(sc);

    }

    public void inputHeight(Scanner sc) {
        do {
            System.out.print("Enter height: ");
            height = Integer.parseInt(sc.nextLine());
        }
        while (isHeightLessThanZeroOrGreaterThanTwoThousand(height));
    }

    public void inputWidth(Scanner sc) {
        do {
            System.out.print("Enter width: ");
            width = Integer.parseInt(sc.nextLine());
        }
        while (isWidthLessThanZeroOrGreaterThanThreeThousand(width));
    }

    public void isWaterColor(Scanner sc) {
        int number;
        do {
            System.out.print("Water Color(Enter number: 1.true/2.false)?: ");
            number = Integer.parseInt(sc.nextLine());
            if (number != 1 && number != 2)
                System.out.println("Invalid value!");
        }
        while (number != 1 && number != 2);

        if (number == 1)
            isWaterColor = true;
        else
            isWaterColor = false;
    }

    public void isFramed(Scanner sc) {
        int number;
        do {
            System.out.print("Framed(Enter number: 1.true/2.false)?: ");
            number = Integer.parseInt(sc.nextLine());
            if (number != 1 && number != 2)
                System.out.println("Invalid value!");
        }
        while (number != 1 && number != 2);

        if (number == 1)
            isFramed = true;
        else
            isFramed = false;
    }

    public boolean isHeightLessThanZeroOrGreaterThanTwoThousand(int height) {
        if (height < 0 || height > 2000) {
            System.out.println("Height phải >= 0 và <=2000");
            return true;
        }
        return false;
    }

    public boolean isWidthLessThanZeroOrGreaterThanThreeThousand(int width) {
        if (width < 0 || width > 3000) {
            System.out.println("Width phải >= 0 và <=3000");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Statue{" +
                "id='" + id + '\'' +
                ", 1.value=" + value +
                ", 2.creator='" + creator + '\'' +
                ", 3.height=" + height +
                ", 4.width=" + width +
                ", 5.isWaterColor=" + isWaterColor +
                ", 6.isFramed=" + isFramed +
                '}';
    }
}
