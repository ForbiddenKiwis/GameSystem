import java.util.Scanner;

/*
Erfan Omidi,
Doctor Christopher Velasquez-Chavez,
Jean Marie Martin Nahayo,
Jerwinson-Flores Cunanan,
Lincoln Farrell


* Smuggler starts with Items meaning Items is aggregating to Smuggler(user)
City starts with Items meaning Items is also aggregating to City.
*
* */
public class Main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        //City Items declaridng all the items for the two entities (city and Smuggler)
        Item item1 = new Item("apple",3,5);
        Item item2 = new Item("rice",10,5);
        Item item3 = new Item("banana",3,5);
        Item item4 = new Item("water",2,5);
        Item item5 = new Item("plastic",15,5);

        Item[] cityItems = {item1, item2, item3, item4, item5};

        //Smuggler Items
        Item item6 = new Item("apple",3,5);
        Item item7 = new Item("rice",10,5);
        Item item8 = new Item("banana",3,5);
        Item item9 = new Item("water",8,5);
        Item item10 = new Item("plastic",15,5);
        Item[] smugglerItems = {item6, item7, item8, item9, item10};


        City city1 = new City("Montreal",cityItems);


        Smuggler smuggler = new Smuggler(smugglerItems, city1);


        smuggler.Menu();

    }

}