package buoi03;

import java.util.Scanner;

public class Item {
    protected String id;
    protected int value;
    protected String creator;
    protected static String[] listId = new String[100];
    protected static int count = 0;

    public Item() {
    }

    public Item(String id, int value, String creator) {
        this.id = id;
        this.value = value;
        this.creator = creator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void input() {
        Scanner sc = new Scanner(System.in);

        do {
            do {
                System.out.print("Enter id: ");
                id = sc.nextLine();
            }
            while (isIdEmpty(id));
        }
        while (isIdDulicated(id));
        listId[count++]=id;


        inputValue(sc);

        inputCreator(sc);
    }

    public void inputValue(Scanner sc) {
        do {
            System.out.print("Enter value: ");
            value = Integer.parseInt(sc.nextLine());
        }
        while (isValueLessThanZero(value));

    }

    public void inputCreator(Scanner sc) {
        do {
            System.out.print("Enter creator: ");
            creator = sc.nextLine();
        }
        while (isCreatorEmpty(creator));

    }

    public boolean isIdDulicated(String id) {
        for(int i=0; i<count; i++) {
            if(id.trim().equalsIgnoreCase(listId[i])) {
                System.out.println("Id đã tồn tại");
                return true;
            }
        }
        return false;
    }

    public boolean isIdEmpty(String id) {
        if (id.trim().isEmpty()) {
            System.out.println("ID không được để trống");
            return true;
        }
        return false;
    }

    public boolean isValueLessThanZero(int value) {
        if (value<0) {
            System.out.println("Value phải >= 0");
            return true;
        }
        return false;
    }

    public boolean isCreatorEmpty(String creator) {
        if (creator.trim().isEmpty()) {
            System.out.println("Creator không được để trống");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", creator='" + creator + '\'' +
                '}';
    }
}
