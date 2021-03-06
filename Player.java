import java.util.Random;
import java.util.HashSet;
import java.util.Stack;
import java.util.ArrayList;
/**
 * Write a description of class Player here.
 * 
 * @author Callie Deas 
 * @version 05-21-2017
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private int maxWeight;
    private int currentWeight;
    //private String name;
    private Random gen;
    private ArrayList<Item> inventory;
    private Stack<Room> history;
    private HashSet<Family> members;
    private HashSet<Item> characterItems;

    /**
     * Constructor for objects of class Player
     */
    public Player(Room startingRoom)
    {
        gen = new Random();
        maxWeight = gen.nextInt(30) +10;
        inventory = new ArrayList<>();
        currentWeight = 0;
        history = new Stack<>();
        currentRoom = startingRoom; 
        members = new HashSet<>();
        characterItems = new HashSet<>();
    }

    public void setRoom(Room nextRoom){
        currentRoom = nextRoom;
    }

    public Room getRoom(){
        return currentRoom;
    }

    public String goRoom(String direction){
        Room nextRoom = currentRoom.getExit(direction);
        if(nextRoom != null){
            history.push(currentRoom); 
            currentRoom = nextRoom;
            return (currentRoom.getLongDescription());
        }else{
            return ("You cannot go that way.");
        }

    }

    public String back(){
        if(history.empty()) {
            return "You can't go back any further...\n" +
            currentRoom.getLongDescription();
        }
        currentRoom = history.pop();
        return currentRoom.getLongDescription();
    }

    public String takeItem(String itemName)
    {
        Item object = currentRoom.takeItem(itemName);

        if(itemName == null) {  
            return "Couldn't find that item...";
        }

        if(currentWeight + object.getWeight() > maxWeight){
            return "You cannot carry more than " + maxWeight + " ounces.";
        }

        inventory.add(object);
        currentWeight += object.getWeight();
        if(itemName.equals("pack") || itemName.equals("backpack")){
            maxWeight += 5;
            return "You took " + itemName +". You can now carry 5 more ounces";

        }else{
            return ("You took " + itemName);
        }
    }

    public String bringFam(String memberName){
        Family member = currentRoom.bringFam(memberName);
        if(memberName == null) {
            return "There is no one in your family named that.";
        }

        members.add(member);
        return (memberName + " is now with you.");
    }

  
    public String dropItem(String partial)
    {
        for(Item thing : inventory) {
            String discription = thing.getName();
            if(discription.contains(partial)){
                if(partial == "backpack"){
                    inventory.remove(thing);
                    currentRoom.addItem(discription, thing.getWeight());
                    return "You have dropped " + discription + ". You can " + 
                    "carry 5 less ounces"; 
                }else {

                    inventory.remove(thing);
                    currentRoom.addItem(discription, thing.getWeight());
                    return "You have dropped " + discription;
                }
            }
        }
        return "You cannot drop that.";
    }

    public String checkInventory()
    {
        for(Item item : inventory){
            return item.getName();
        }

        return null;
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }
}

