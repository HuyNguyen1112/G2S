package buoi03;

import java.util.Scanner;

public class Statue extends Item{
    private int weight;
    private String color;

    public Statue() {
    }

    public Statue(String id, int value, String creator, int weight, String color) {
        super(id, value, creator);
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void input() {
        super.input();

        Scanner sc = new Scanner(System.in);

        inputWeight(sc);

        inputColor(sc);
    }

    public void inputWeight(Scanner sc) {
        do {
            System.out.print("Enter weight: ");
            weight = Integer.parseInt(sc.nextLine());
        }
        while (isWeightLessThanZeroOrGreaterThanOneThousand(weight));
    }

    public void inputColor(Scanner sc) {
        do {
            System.out.print("Enter color: ");
            color = sc.nextLine();
        }
        while (isColorEmpty(color));
    }

    public boolean isWeightLessThanZeroOrGreaterThanOneThousand(int weight) {
        if (weight<0 || weight>1000) {
            System.out.println("Weight phải >= 0 và <=1000");
            return true;
        }
        return false;
    }

    public boolean isColorEmpty(String color) {
        if (color.trim().isEmpty()) {
            System.out.println("Color không được để trống");
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
                ", 3.weight=" + weight +
                ", 4.color='" + color + '\'' +
                '}';
    }
}
