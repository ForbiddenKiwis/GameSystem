import java.util.Random;
import java.util.Scanner;

public class City{

    private String oldCityName;
    public Item[] items;
    public String cityName;
    private static int fineHistory = 100;


    public City(String cityName,Item[] items){
        this.items= items;
        this.cityName = cityName;
        this.oldCityName = cityName;

    }
    //setting city name we will need it when we change to another city
    public void setCityName(String newCityName){

        this.oldCityName = this.cityName;
        this.cityName = newCityName;
    }
    // returns the currenct cityname
    public String getCityName(){
        return cityName;
    }

    // returns the old city name
    public String getOldCityName(){
        return oldCityName;
    }
    public void moveCity(){
        Random random = new Random();
        // we put all the city name in an array
        String[] citieNames = {"Montreal", "London", "Toronto", "Paris", "Bangkok"};
        String randomCity;

        // we do a do While loop to get a random number that random number will decide the next city
        // if it is the same as the old cityName it will redo a loop
        do {
            int randomNumberCity = random.nextInt(5);
            randomCity = citieNames[randomNumberCity];

        }while (randomCity.equals(getOldCityName()));
        setCityName(randomCity);
        // we call priceChange to randomize the price and quantity of the city
        priceChange();
        // here we call the getCaught method.
        boolean caught = getCaught();
        // if returns true it will ask to pay a fine
        if(caught){
            payFine();
            // when he pays he gets moved to another city and the quantity of one of his items gets lower
        }else {

            System.out.println("\nYou have successfully move to " + getCityName()+"\n");
        }
    }

    // method to change the price and the quantity of the city item when the name changes
    public void priceChange(){
        if (!cityName.equals(oldCityName)){
            Random random = new Random();

            // foreach loop to randomize the price of the item
            for (Item item : items){
                int newPrice =  random.nextInt(30);
                item.setPrice(newPrice);
            }
            // foreach loop to randomize the quantity of the item

            for(Item item : items){
                int newQuantity = random.nextInt(30);
                item.setQuantity(newQuantity);
            }
        }
    }
    public boolean getCaught(){

        int numItems = items.length;

        //Calculate the probability based on the quantity of items
        double baseProbability = 0.01;
        double probability = 0; // 1%/item of getting caught

        int totalQuantity = Smuggler.getTotalItem();
        probability += baseProbability * totalQuantity;


        //Create random number
        Random rand = new Random();
        double random = rand.nextDouble();

        //Check if the user got caught based on the
        return random <= probability;
    }
    public int payFine(){
        Scanner reader = new Scanner(System.in);
        String fineAns;
        Random random = new Random();
        // we put all the city name in an array
        String[] itemsNames = {"apple", "rice", "banana", "water", "plastic"};
        String randomName;
        int randomNumber = random.nextInt(5);
        randomName = itemsNames[randomNumber];

        fineHistory = 100;

        int smugglerNewQuantity;
        int itemQuantity;
        String itemName ;
        for (Item item:items) {
            if (randomName.equals(item.getName())){
                itemName = item.getName();
                itemQuantity = Smuggler.getItemQuantity(itemName);
                smugglerNewQuantity = itemQuantity - 1;
                Smuggler.setItemQuantity(itemName,smugglerNewQuantity);
            }
        }

        do {
            System.out.println(fineHistory);
            System.out.println("\nYou have been caught!!\nDo you wish to pay the fine (y/n) of: "+ fineHistory+" $ ?\n");
            fineAns = reader.next();
            // if the user wants to pay fine we get the price of the fine and charge him and we update his amount
            if (fineAns.equals("y") || fineAns.equals("Y")) {
                if (Smuggler.getMoney() >= fineHistory) {

                    int userMoney = Smuggler.getMoney();
                    int totMon = userMoney - fineHistory;
                    Smuggler.setMoney(totMon);
                    fineHistory += 50;
                    System.out.println("Money to pay later:"+ fineHistory);
                    Smuggler.Menu();
                    return fineHistory;
                } else {
                    System.out.println("\nYou don't have enough money\nYou have lost the game");
                    System.exit(0);
                }

            } else if (fineAns.equals("n") || fineAns.equals("N")) {

                System.out.println("\nThank you for playing!!!\nYou have lost the game");
                System.exit(0);
            }
        }while (!fineAns.equals("y") || !fineAns.equals("Y") || !fineAns.equals("n") || !fineAns.equals("N"));
        return fineHistory;
    }
    // set the quantity of the item in the city

    public void setItemQuantity(String itemName, int newQuanty){
        for (Item item: items){
            if (item.getName().equals(itemName)){
                item.setQuantity(newQuanty);
                return;
            }
        }
    }
    // get the quantity of the item in the city

    public int getItemQuantity(String itemName){
        for(Item item : items){
            if (item.getName().equals(itemName)){
                return item.getquantity();
            }
        }
        return 0;
    }
    // get the price of the item in the city
    public int getItemPrice(String itemName){
        for(Item item : items){
            if (item.getName().equals(itemName)){
                return item.getPrice();
            }
        }
        return 0;
    }
    // display the items of the city and the name
    public void displayCity(){
        System.out.println("\ncity name: "+cityName);
        for (Item item : items) {
            item.displayItemcity();
            System.out.println("\n");
        }
    }

}