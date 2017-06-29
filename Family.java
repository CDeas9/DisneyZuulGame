
/**
 * Write a description of class Family here.
 * 
 * @author Callie Deas
 * @version 3-16-17
 */
public class Family
{
    // instance variables - replace the example below with your own
    private String name;

    /**
     * Constructor for objects of class Character
     */
    public Family(String Name)
    {
        // initialise instance variables
        this.name = Name;
    }

    public String toString(){
        return name.toString();
    }
    
    public String getName()
    {
        return name;
    }

}
