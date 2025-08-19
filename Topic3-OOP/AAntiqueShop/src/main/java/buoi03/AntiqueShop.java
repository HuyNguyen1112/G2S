package buoi03;

import java.util.Scanner;

public class AntiqueShop {

    public static final String ADDVASE = "1";
    public static final String ADDSTATUE = "2";
    public static final String ADDPAINTING = "3";
    public static final String DISPLAYALL = "4";
    public static final String FINDBYCREATOR = "5";
    public static final String DISPLAYVASE = "6";
    public static final String DISPLAYSTATUE = "7";
    public static final String DISPLAYPAINTING = "8";
    public static final String UPDATEBYID = "9";
    public static final String QUIT = "10";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ItemList itemList = new ItemList();
        String choice;

        do {
            System.out.println("\n===== Antique Shop =====");
            System.out.println("1. Add a new Vase");
            System.out.println("2. Add a new Statue");
            System.out.println("3. Add a new Painting");
            System.out.println("4. Display all items");
            System.out.println("5. Find the items by the creator");
            System.out.println("6. Display the list of vase items ");
            System.out.println("7. Display the list of statue items ");
            System.out.println("8. Display the list of painting items ");
            System.out.println("9. Update the item by ID");
            System.out.println("10. Quit");
            System.out.print("Input your choice: ");
            choice = sc.nextLine();
            switch (choice) {
                case ADDVASE:
                    Vase vItem = new Vase();
                    vItem.input();
                    if (itemList.addItem(vItem)) {
                        System.out.println("added");
                    }
                    break;

                case ADDSTATUE:
                    Statue sItem = new Statue();
                    sItem.input();
                    if (itemList.addItem(sItem)) {
                        System.out.println("added");
                    }
                    break;

                case ADDPAINTING:
                    Painting pItem = new Painting();
                    pItem.input();
                    if (itemList.addItem(pItem)) {
                        System.out.println("added");
                    }
                    break;

                case DISPLAYALL:
                    itemList.displayAll();
                    break;

                case FINDBYCREATOR:
                    String creator;
                    System.out.println("Enter the creator to find the item: ");
                    creator = sc.nextLine();
                    System.out.println(itemList.findItem(creator));
                    break;

                case DISPLAYVASE:
                    itemList.displayItemByType(DISPLAYVASE);
                    break;

                case DISPLAYSTATUE:
                    itemList.displayItemByType(DISPLAYSTATUE);
                    break;

                case DISPLAYPAINTING:
                    itemList.displayItemByType(DISPLAYPAINTING);
                    break;

                case UPDATEBYID:
                    String id;
                    System.out.print("Enter ID to update: ");
                    id = sc.nextLine();
                    if (itemList.updateItem(id))
                        System.out.println("updated");
                    else
                        System.out.println("false to update, can't find the item");
                    break;

                case QUIT:
                    System.out.println("Đang thoát...");
                    break;

                default:
                    System.out.println("Invalid choice!");
                    break;

            } // end switch
        } while (!choice.equals("10")); // end while


    }
}
