import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * GameTest - a class to test the zuul game engine.
 *
 * @author  William H. Hooper
 * @version 2012-07-06
 */
public class GameTest
{
    private Game game1;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        System.out.println("\f");
        game1 = new Game();
        String message = game1.readMessages();
        System.out.print(message + "> ");
    }

    /**
     * Issue a command and get the response.
     * As a side effect, it prints its output on the terminal
     * in the same form as a game dialog.
     * @param cmd a command such as "help"
     * @return the game output that results from the commmand.
     */
    private String getResponse(String cmd)
    {
        System.out.println(cmd);
        game1.processInput(cmd);
        String message = game1.readMessages();
        System.out.print(message + "> ");
        return message;
    }

    /**
     * Execute a command.
     * As a side effect, it prints its output on the terminal
     * in the same form as a game dialog.
     * @param cmd a command such as "go east"
     * This test will always succeed unless there is an error
     * in the game code.
     */
    public void testCommand(String cmd)
    {
        testCommand(cmd, "", true);
    }

    /**
     * Test whether a given command gets the expected response.
     * This method only succeeds if the reply is received.
     * As a side effect, it prints its output on the terminal
     * in the same form as a game dialog.
     * @param cmd a command such as "help"
     * @param reply a portion of the expected response.
     * It is sufficient to supply a unique word or phrase that
     * appears in the output.
     */
    public void testCommand(String cmd, String reply)
    {
        testCommand(cmd, reply, true);
    }

    /**
     * Test whether a given command gets the expected response.
     * This method only succeeds if the reply is received.
     * As a side effect, it prints its output on the terminal
     * in the same form as a game dialog.
     * @param cmd a command such as "help"
     * @param reply a portion of the expected response.
     * @param match true if you want the response to match the 
     * reply, false if you don't
     * It is sufficient to supply a unique word or phrase that
     * appears in the output.
     */
    public void testCommand(String cmd, String reply, 
    boolean match)
    {
        String message = getResponse(cmd);
        assertEquals(match, message.contains(reply));
    }

    @Test
    public void start()
    {
        assertEquals(false, game1.finished());
    }

    //     @Test
    //     public void theatre()
    //     {
    //         testCommand("go east", "theater");
    //     }
    // 
    //     @Test
    //     public void noDoor()
    //     {
    //         testCommand("go north", "no door!");
    //     }

    @Test
    public void quit()
    {
        testCommand("quit");
        assertEquals(true, game1.finished());
        testCommand("go east", "Tomorrowland", false);
        testCommand("anything", "game is over");
    }

    @Test
    public void cannotGo()
    {
        testCommand("go south-east", "cannot go");
    }

    @Test
    public void map()
    {
        //there is more than what I had in the map I sent before

        testCommand("go east", "Tomorrowland");
        testCommand("go east", "Space Mountain");
        testCommand("go ride", "inside the Space Mountain");
        testCommand("go outside", "Space Mountain");
        testCommand("go west", "Tomorrowland");
        testCommand("go west", "center");
        testCommand("go east", "Tomorrowland");
        testCommand("go north", "Fantasyland");
        testCommand("go north", "Under the Sea");
        testCommand("go ride", "inside the Under the Sea");
        testCommand("go outside", "Under the Sea");
        testCommand("go south", "Fantasyland");
        testCommand("go south-west", "center");
        testCommand("go north-east", "Fantasyland");
        testCommand("go west", "Carrousel");
        testCommand("go east", "Fantasyland");
        testCommand("go west", "Carrousel");
        testCommand("go south", "Castle");
        testCommand("go south", "center");
        testCommand("go north", "Castle");
        testCommand("go north", "Carrousel");
        testCommand("go west", "Liberty Square");
        testCommand("go east", "Carrousel");
        testCommand("go west", "Liberty Square");
        testCommand("go south-east", "center");
        testCommand("go north-west", "Liberty Square");
        testCommand("go west", "Haunted Mansion");
        testCommand("go ride", "inside the Haunted Mansion");
        testCommand("go outside", "Haunted Mansion");
        testCommand("go east", "Liberty Square");
        testCommand("go south-west", "Adventureland");
        testCommand("go north-east", "Liberty Square");
        testCommand("go south-west", "Adventureland");
        testCommand("go west", "Pirates");
        testCommand("go ride", "inside the Pirates");
        testCommand("go outside", "Pirates");
        testCommand("go east", "Adventureland");
        testCommand("go north", "Frontierland");
        testCommand("go north", "Splash");
        testCommand("go ride", "inside the Splash");
        testCommand("go outside", "Splash");
        testCommand("go south", "Frontierland");
        testCommand("go south", "Adventureland");
        testCommand("go east", "center");
        testCommand("go west", "Adventureland");
        testCommand("go east", "center");
        testCommand("go south", "Main");
        testCommand("go north", "center");
        testCommand("go south", "Main");
        testCommand("go south", "entrance");
        testCommand("go north", "Main");

    }

    @Test
    public void backPositive()
    {
        testCommand("go north", "Castle");
        testCommand("go north", "Carrousel");
        testCommand("go east", "Fantasyland");
        testCommand("go south", "Tomorrowland");
        testCommand("back", "Fantasyland");
        testCommand("back", "Carrousel");
        testCommand("back", "Castle");
    }

    @Test
    public void backNegative()
    {
        testCommand("go north", "Castle");
        testCommand("back", "center");
        testCommand("back", "You can't");
    }

    @Test
    public void items()
    {
        testCommand("go north-east", "Fantasyland");
        testCommand("go north", "Under");
        testCommand("go ride", "hat");
    }

    @Test
    public void characters()
    {
        testCommand("go north", "Mickey Mouse");
    }

    @Test
    public void takeCrown()
    {
        testCommand("go north", "Castle");
        testCommand("go north", "Carrousel");
        testCommand("go west", "Square");
        testCommand("go west", "Mansion");
        testCommand("go ride", "crown");
        testCommand("take crown", "You took");
        testCommand("look", "crown", false);
        testCommand("inventory", "crown");
    }

    @Test
    public void takePack()
    {
        testCommand("take backpack", "You took");
        testCommand("look", "backpack", false);
    }

    @Test
    public void bringSarah()
    {
        testCommand("go west", "Adventureland");
        testCommand("go west", "Pirates");
        testCommand("go ride", "Sarah");
        testCommand("bring Sarah", "is now");
        testCommand("look", "Sarah", false);

    }

    @Test
    public void takehat()
    {
        testCommand("go north-east", "");
        testCommand("go north", "");
        testCommand("go ride", "");
        testCommand("take hat", "took");
        testCommand("look", "hat", false);

    }

    @Test
    public void checkInventory()
    {
        testCommand("take backpack", "took");
        testCommand("inventory", "backpack");
    }

    @Test
    public void dropPack()
    {
        testCommand("take backpack", "You took");
        testCommand("look", "backpack", false);
        testCommand("go north", " ");
        testCommand("drop backpack", "dropped");
        testCommand("inventory", "backpack", false);
        testCommand("look", "backpack", true);
    }

    @Test
    public void drophat()
    {
        testCommand("go north-east", "");
        testCommand("go north", "");
        testCommand("go ride", "");
        testCommand("take hat", "took");
        testCommand("look", "hat", false);
        testCommand("go outside", " ");
        testCommand("drop hat", "dropped");
        testCommand("look", "hat", true);
        testCommand("inventory", "hat", false);
    }

    @Test 
    public void askMickeyNeg()
    {
        testCommand("ask Mickey", "that character");
    }
    
    @Test 
    public void askMickeyPos()
    {
        testCommand("go north", "");
        testCommand("ask Mickey", "might have seen");
    }
    
    @Test 
    public void askHiro()
    {
        testCommand("go west", "");
        testCommand("go west", "");
        testCommand("ask Hiro", "might have come across");
    }
    
    @Test 
    public void askRapunzel()
    {
        testCommand("go east", "");
        testCommand("go east", "");
        testCommand("ask Rapunzel", "I think");
    }
    
    @Test
    public void giveHat()
    {
      testCommand("go north", "Mickey");
      testCommand("go north", " ");
      testCommand("go east", " ");
      testCommand("go north", " ");
      testCommand("go ride", " ");
      testCommand("take hat", "hat");
      testCommand("go outside", " ");
      testCommand("go south", " ");
      testCommand("go west", " ");
      testCommand("go south"," ");
      testCommand("give hat", "you");
    }

}
