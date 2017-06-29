
/**
 * Write a description of class Items here.
 * 
 * @author Callie Deas 
 * @version 11-30-2016
 */
public class Item 
{
    // instance variables - replace the example below with your own
    private int weight;
    private String description;
    
    /**
     * Constructor for objects of class Items
     */
    public Item(String name, int weightInOnces)
    {
        // initialise instance variables
        this.weight = weightInOnces ;
        this.description = name;
    }
    
    public String toString(){
        return description.toString();
    }
    
    public int getWeight()
    {
        return weight;
    }
    
    public String getName()
    {
        return description;
    }
    
}
