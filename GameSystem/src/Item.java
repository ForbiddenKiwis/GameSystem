public class Item {
    String name;
    int price;
    int quantity;

    public Item(String name, int price, int quantity){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }
    // get the name of the item
    public String getName(){
        return name;
    }


    // Setting price this method will be called everytime the price change
    // and we need to set it.
    public void setPrice(int price){
        this.price = price;
    }
    // get the price
    public int getPrice(){
        return price;
    }
    // we set the quantity
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    // get the quantity
    public int getquantity(){
        return quantity;
    }
    // display the items
    public String toString(){
        return "Name: " + name + "\nPrice: $" + price + "\nQuantity: " + quantity;
    }
    // display the items
    public String cityToString(){
        return "Name: " + name + "\nsell price and buy price: $" + price + "\nQuantity: " + quantity;
    }
    // display city items
    public void displayItemcity(){
        System.out.println(cityToString());
    }

    // display smuggler items
    public void displayItemSmuggler(){
        System.out.println(toString());
    }
}
