import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private String imageName;
    private HashMap<String, Room> exits;
    private ArrayList<Item> objects;
    private HashSet<Character> characters;
    private HashSet<Family> members;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        objects = new ArrayList<>();
        characters = new HashSet<>();
        members = new HashSet<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */

    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public void addItem(String description, int weightInOnces){
        Item object = new Item(description, weightInOnces);
        objects.add(object);
    }

    public void addFamily(String name){
        Family member = new Family(name);
        members.add(member);
    }

    public Item takeItem(String itemName){
        for(Item thing : objects){
            String name = itemName.toLowerCase();
            if(thing.getName().toLowerCase().contains(name)){
                objects.remove(thing);
                return thing;
            }
        }
        return null;
    }

    public Family bringFam(String memName){
        for(Family member : members){
            String name = member.getName();
            if(name.contains(memName)){
                members.remove(member);
                return member;
            }
        }
        return null;
    }

    public Item dropItem(String itemName){
        for(Item thing : objects){
            String name = thing.getName();
            if(name.contains(itemName)){
                objects.remove(thing);
                return thing;
            }
        }
        return null;
    }

    public String getItemString()
    {
        String itemString = "";
        for(Item it : objects){
            itemString += "\nYou see " + it + ". It is " + 
            it.getWeight() + " ounces";
        }

        return itemString;
    }

    public void addCharacter(String name){
        Character character = new Character(name);
        characters.add(character);
    }

    public String getCharacterString()
    {
        String charString = "";
        for(Character it : characters){
            charString += "\nYou see " + it;
        }

        return charString;
    }

    public String getFamilyString()
    {
        String memString = "";
        for(Family it : members){
            memString += "\nYou see " + it;
        }

        return memString;
    }
    
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys){
            returnString += " " + exit;
        }
        return returnString;
    }

    public String getLongDescription()
    {
        return "You are " + description + getItemString() +
        getCharacterString() + getFamilyString() + ".\n" + 
        getExitString();
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    //     public boolean hasBeen()
    //     {
    //         
    //     }

    /*************************************************************
     * added by William H. Hooper, 2006-11-28
     *************************************************************/
    /**
     * @return a String, which hopefully contains the file name
     * of an Image file.
     */
    public String getImage()
    {
        return imageName;
    }

    /**
     * associate an image with this room
     * @param filename a String containing a file.
     * The file <b>must</b> reside in the same directory as this
     * code, and must be in a format viewable in the Java AWT.
     * Readable formats include: 
     * PNG, JPG (RGB color scheme only), GIF
     */
    public void setImage(String filename)
    {
        imageName = filename;
    }
}
