package buoi03;

import java.util.Scanner;

import static buoi03.AntiqueShop.*;

public class ItemList {
    private Item[] list;
    private int numOfItem;
    private final int MAX = 100;

    public ItemList() {
        list = new Item[MAX];
        numOfItem = 0;
    }

    public boolean addItem(Item item) {
        if (item == null || numOfItem >= MAX) {
            return false;
        }

        list[numOfItem++] = item;
        return true;
    }

    public boolean isListNull() {
        return numOfItem == 0;
    }

    public void displayAll() {
        if (isListNull())
            System.out.println("No Item in list");
        else
            for (int i = 0; i < numOfItem; i++) {
                System.out.println(list[i]);
            }
    }

    public Item findItem(String creator) {
        for (int i = 0; i < numOfItem; i++) {
            if (creator.equals(list[i].getCreator())) {
                return list[i];
            }
        }
        return null;
    }

    public Item findItemById(String id) {
        for (int i = 0; i < numOfItem; i++) {
            if (id.equals(list[i].getId())) {
                return list[i];
            }
        }
        return null;
    }

    public boolean updateItem(String id) {

        Item item = findItemById(id);
        if (item == null)
            return false;

        if (item instanceof Vase) {
            Vase vItem = (Vase) item;
            updateVase(vItem);
            return true;
        }

        else if (item instanceof Statue) {
            Statue sItem = (Statue) item;
            updateStatue(sItem);
            return true;
        }

        else if (item instanceof Painting) {
            Painting pItem = (Painting) item;
            updatePainting(pItem);
            return true;
        }

        return true;
    }

    public void updateVase(Vase vItem) {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println(vItem);
        System.out.print("Chọn thuộc tính muốn sửa(1 ->4): ");
        choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1 -> vItem.inputValue(sc);
            case 2 -> vItem.inputCreator(sc);
            case 3 -> vItem.inputHeight(sc);
            case 4 -> vItem.inputMaterial(sc);
        }

    }

    public void updateStatue(Statue sItem) {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println(sItem);
        System.out.print("Chọn thuộc tính muốn sửa(1 ->4): ");
        choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1 -> sItem.inputValue(sc);
            case 2 -> sItem.inputCreator(sc);
            case 3 -> sItem.inputWeight(sc);
            case 4 -> sItem.inputColor(sc);
        }

    }

    public void updatePainting(Painting pItem) {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println(pItem);
        System.out.print("Chọn thuộc tính muốn sửa(1 ->6): ");
        choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1 -> pItem.inputValue(sc);
            case 2 -> pItem.inputCreator(sc);
            case 3 -> pItem.inputHeight(sc);
            case 4 -> pItem.inputWidth(sc);
            case 5 -> pItem.isWaterColor(sc);
            case 6 -> pItem.isFramed(sc);
        }

    }

    public void displayItemByType(String type) {
        if (type.equals(DISPLAYVASE)) {
            for (int i = 0; i < numOfItem; i++) {
                if (list[i] instanceof Vase)
                    System.out.println(list[i]);
            }
        }

        else if (type.equals(DISPLAYSTATUE)) {
            for (int i = 0; i < numOfItem; i++) {
                if (list[i] instanceof Statue)
                    System.out.println(list[i]);
            }
        }

        else {
            for (int i = 0; i < numOfItem; i++) {
                if (list[i] instanceof Painting)
                    System.out.println(list[i]);
            }
        }

    }
}
