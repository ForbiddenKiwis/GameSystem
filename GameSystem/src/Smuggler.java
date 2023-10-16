import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Smuggler {
    private static Scanner reader = new Scanner(System.in);

    private static Item[] items;
    private static City city1;
    // we declare the money the user will start
    private static int Money = 300;

    //constructor
    public Smuggler(Item[] items, City city1) {
        Smuggler.items = items;
        Smuggler.city1 = city1;

    }

    // method to buy items from the city
    public static void buyItems() {
        // we declare var transaction for the user to be in a loop if they wrongly answer the questions Mouhahaha
        boolean transaction = false;
        String itemName;
        int itemBuyQuantity;
        int smugglerNewQuantity;

        // loop for transaction so until it is true it will not stop loop Infinity!!
        while (!transaction) {
            System.out.println("\nWhich items would you like to buy to the city?\n");
            // we ask the user to enter the name of the item they wish to buy
            itemName = reader.next();

            //we declare Itemfound false in case that the user doesn't have the item
            boolean itemFound = false;

            // we do a for each loop to find an Item with the same name.
            for (Item item : items) {
                // if there is an item with the same name we change the item found to true so we don't get the prompt to reenter the name of the item
                if (itemName.equals(item.getName())) {
                    itemFound = true;
                    // Quantity

                    //we ask the quantity that user would like to sell we try to convert it to an int ,so we don't have an exception.
                    System.out.println("\nHow much quantity would you like to buy??  \n");

                    try {
                        itemBuyQuantity = reader.nextInt();
                        int smugglerQuantity = city1.getItemQuantity(itemName);

                        // Check if user has enough quantity and isn't tricking by putting numbers below 0
                        if (itemBuyQuantity > 0 && itemBuyQuantity <= smugglerQuantity) {
                            // we put smuggler quantity and items bought to a variable name smugglerNewQuantity and that var we use
                            // the setQuantity to update the quantity of smuggler
                            smugglerNewQuantity = smugglerQuantity + itemBuyQuantity;


                            // here we get the price of the item
                            int cityItemQuantity;
                            int buyPrice = city1.getItemPrice(itemName);

                            String userConfirmation;
                            //Confirmation y/n
                            do {
                                System.out.println("You are buying "+itemBuyQuantity+" "+itemName+" for "+buyPrice+" $ each totaling " +buyPrice*itemBuyQuantity + " $.\nAre you sure you want to proceed? (y/n)");
                                userConfirmation = reader.next();
                                if(userConfirmation.equals("n")|| userConfirmation.equals("N")){
                                    System.out.println("Cancelling order ");
                                    // here we recall menu ,so we don't get stuck in the loop
                                    Menu();
                                    break;
                                }
                                else if (userConfirmation.equals("Y")|| userConfirmation.equals("y")){
                                    if(getMoney() < buyPrice*itemBuyQuantity){
                                        System.out.println("You dont sufficient funds.");
                                        Menu();
                                    }
                                    else {
                                        System.out.println("Buying " + itemBuyQuantity + " " + itemName);

                                        // here is the setQuantity for the smuggler
                                        Smuggler.setItemQuantity(itemName,smugglerNewQuantity);
                                        // here we get the new quantity for the city, and we set it to update it in the city
                                        cityItemQuantity = city1.getItemQuantity(itemName);
                                        city1.setItemQuantity(itemName, cityItemQuantity - itemBuyQuantity);
                                        Smuggler.setItemQuantity(itemName, smugglerNewQuantity);
                                        // here is the new money for the smuggler we also update it after the transaction
                                        int newMoney = getMoney() - buyPrice * itemBuyQuantity;
                                        setMoney(newMoney);
                                        Menu();
                                        break;
                                    }
                                }
                                else {
                                    System.out.println("\nInvalid choice try again.\n");
                                }
                            }while (!userConfirmation.equals("Y")|| !userConfirmation.equals("y") || !userConfirmation.equals("n")||  !userConfirmation.equals("N"));                       } else {
                            System.out.println("\nInvalid quantity or not enough of " + itemName + " in city inventory.\n");
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("\nInvalid input. Please enter a valid integer.\n");
                        reader.next(); // Consume the invalid input.
                    }
                }
            }
            if (!itemFound) {
                System.out.println("\nItem: " + itemName + " is not an item!\n");
            }
        }
    }




// selling items to the city
    // it is basically the same logic as buy items

    public static void sellItems() {
        // we have the bool transaction for the loop

        boolean transaction = false;
        String itemName;
        int itemSellQuantity;
        int smugglerNewQuantity;

        while (!transaction) {
            System.out.println("\nWhich of your items would you like to sell to the city?\n");
            Scanner reader = new Scanner(System.in);
            itemName = reader.next();
            boolean itemFound = false;

            for (Item item : items) {
                if (itemName.equals(item.getName())) {
                    itemFound = true;
                    boolean validQuantityEntered = false;

                    do {
                        // Ask the user how much they want to sell and it will check if the smuggler has the amount
                        System.out.println("\nHow much quantity would you like to sell?\n");

                        try {
                            itemSellQuantity = reader.nextInt();
                            int smugglerQuantity = Smuggler.getItemQuantity(itemName);

                            //When Smuggler have item amount it will deduct the item from smugler inventory.
                            if (itemSellQuantity > 0 && itemSellQuantity <= smugglerQuantity) {
                                smugglerNewQuantity = smugglerQuantity - itemSellQuantity;

                                int sellPrice;
                                do {
                                    //It will keep asking how much the smugler wants to sell the item if the price of the item is not an int
                                    System.out.println("For how much would you like to sell your item.");
                                    System.out.println("Don't forget the city will not buy if it is more than what they sell for.\n");

                                    //If the input is an int it will ask the user to confirm their decision
                                    if (reader.hasNextInt()) {
                                        sellPrice = reader.nextInt();
                                        int cityItemPrice = city1.getItemPrice(itemName);
                                        String awn;

                                        do {
                                            System.out.println("\nYou are selling " + itemSellQuantity + " " + itemName + " for " + sellPrice + " $ each totaling "+sellPrice*itemSellQuantity+" $.");
                                            System.out.println("Are you sure you want to sell the item? (y/n)");

                                            awn = reader.next();

                                            //If user anwser yes then the money will be deposited to the user and it go back to the menu
                                            if (awn.equalsIgnoreCase("y")) {
                                                //Check to see if the item sell price is bigger to the maximum sell price of the city if its not above the max sell price of city it add the items price to the smugler money
                                                if (sellPrice <= cityItemPrice) {
                                                    transaction = true;
                                                    Smuggler.setItemQuantity(itemName, smugglerNewQuantity);
                                                    int cityItemQuantity = city1.getItemQuantity(itemName);
                                                    city1.setItemQuantity(itemName, cityItemQuantity + itemSellQuantity);

                                                    int newMoney = getMoney() + sellPrice * itemSellQuantity;
                                                    setMoney(newMoney);

                                                    System.out.println("\nYou have successfully sold " + itemSellQuantity + " of the item: " + itemName + " for: " + sellPrice + " $.\n");
                                                    Menu();
                                                } else {
                                                    //If it is not the case decline transaction and go back to the menu
                                                    System.out.println("\nThe city has not accepted your offer!\n");
                                                    transaction = true;
                                                    Menu();
                                                }
                                                //If user selects no decline transaction and go back to the menu
                                            } else if (awn.equalsIgnoreCase("n")) {
                                                System.out.println("\nTransaction declined.\n");
                                                Menu();
                                                //If the user inputs smt else they will redo the whole process
                                            } else {
                                                System.out.println("\nInvalid input. Please enter 'y' for yes or 'n' for no.\n");
                                            }
                                        } while (!awn.equalsIgnoreCase("y") && !awn.equalsIgnoreCase("n"));
                                    } else {
                                        System.out.println("\nInvalid input. Please enter a valid integer for the sell price.\n");
                                        reader.next(); // Consume the invalid input.
                                    }
                                } while (!transaction);
                            } else {
                                System.out.println("\nInvalid quantity or not enough of " + itemName + " in your inventory.\n");

                                // Display "How much quantity would you like to sell?" message again
                                validQuantityEntered = false;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("\nInvalid input. Please enter a valid integer for the quantity.\n");
                            reader.next(); // Consume the invalid input.
                        }
                    } while (!validQuantityEntered);
                }
            }

            if (!itemFound) {
                System.out.println("\nItem: " + itemName + " is not in your inventory!\n");
            }
        }
    }



    public static void Menu(){
        byte userChoice;
        boolean validInput = false;

        //Check Smuggler money if it reach the amount they win
        if (Smuggler.Money >= 5000){
            System.out.println("Congratulations! You win.");
            System.exit(0);
        }
        System.out.println("*********** Welcome to Smuggler Party ***********");

        // user selection and we go to a switch for the menu
        do {
            System.out.println("Get 5000$ to win the game");
            System.out.println("\nEnter 1 to look at your inventory");
            System.out.println("Enter 2 to check your money");
            System.out.println("Enter 3 to see the name of the current city");
            System.out.println("Enter 4 to sell your items to the city");
            System.out.println("Enter 5 to buy items from the city");
            System.out.println("Enter 6 to travel to another city");
            System.out.println("Enter 7 to exit the game \n");
            do{
                try {
                    userChoice = reader.nextByte();
                    validInput = true;
                }catch (Exception e){
                    System.out.println("Invalid input. Please enter a number.");
                    reader.nextLine();
                    userChoice = 0;
                }
            }while (!validInput);

            switch (userChoice) {
                case 1:
                    System.out.println("\nInventory: ");
                    printItems();
                    break;
                case 2:
                    System.out.println("\nMoney: $" + getMoney()+"\n");

                    break;
                case 3:
                    System.out.println("\nCurrent City: \n");
                    city1.displayCity();
                    break;
                case 4:
                    printItems();

                    sellItems();
                    break;
                case 5:
                    city1.displayCity();
                    buyItems();
                    break;
                case 6:
                    city1.moveCity();

                    break;
                case 7:
                    String exitChoice;
                    do {

                        System.out.println("\nDo you really wish to exit the game? Enter 'y/n' to Exit\n");
                        exitChoice = reader.next();
                        if (exitChoice.equals("Y") ||  exitChoice .equals("y")) {
                            System.out.println("\nGoodbye dear user!");
                            reader.close();
                            System.exit(0);
                        }
                        else if (exitChoice.equals("n") || exitChoice.equals("N")) {
                            userChoice = 0;
                            break;
                        }

                    }while (!exitChoice.equals("Y") ||  !exitChoice .equals("y")||!exitChoice.equals("n") || !exitChoice.equals("N"));
                    break;
                default:
                    System.out.println("\nInvalid input. Please try again.\n");
            }
        } while (userChoice != 7);

        reader.close();
    }
    // set the smuggler money
    public static void setMoney(int amount){
        Smuggler.Money = amount;
    }
    // gets is current money
    public static int getMoney() {
        return Money;
    }

    // shows the smuggler items
    public static void printItems(){
        for (Item item : items) {
            item.displayItemSmuggler();
            System.out.println();
        }
    }
    // returns the total amount of items
    public static int getTotalItem(){
        int totalQuantity =0;
        for (Item item:items) {

            totalQuantity += item.getquantity();
        }return totalQuantity;
    }
    public static int getItemQuantity(String itemName){
        for(Item item : items){
            if (item.getName().equals(itemName)){
                return item.getquantity();
            }
        }
        return 0;
    }
    public static void setItemQuantity(String itemName, int newQuanty){
        for (Item item: items){
            if (item.getName().equals(itemName)){
                item.setQuantity(newQuanty);
                return;
            }
        }
    }

}
