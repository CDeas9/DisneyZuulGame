import java.util.Stack;
import java.util.HashSet;

/**
 *  <p>
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  
 *  Users can walk around some scenery. That's all. It should really 
 *  be extended to make it more interesting!
 * <p>
 *  To play this game, create an instance of this class and call the 
 *  "play" method.
 * <p>
 *  This main class creates and initialises many others: 
 *  it creates all rooms, 
 *  and creates the parser to process commands. 
 *  It also evaluates and
 *  executes the commands that the parser returns.
 * <p>
 *  Revised 2012.06.21 by William H. Hooper: 
 *  The game now receives all input via the processInput() method,
 *  and collects all output into the String messages.
 *  These changes allow the Game to be played by a test method,
 *  by a controller embedded in BlueJ,
 *  from the console,
 *  from an applet, etc.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @author  Callie Deas
 * @version 2017.05.21
 */

public class Game 
{
    private Parser parser;
    private String messages;
    private boolean wantToQuit;
    private Room lastRoom;
    private Stack<Room> history;
    private Player child;
    private HashSet<Item> objects;
    private HashSet<Family> members;

    /****************************************************************
     * Revisions 2012-06-27 by William H. Hooper
     * The following methods were deleted from the class:
     * 
     *      play() - Game play is now directed from the interface.
     *      getInput() - getting user input is now the job of the
     *          interface.
     *      
     * The following public methods were added to the class:
     * 
     *      processInput() - sends user input to the game
     *      readMessages() returns all the messages ready to be sent
     *      finished() - tells the calling interface 
     *          whether the game is over.
     *      
     *      print(), println(), - these methods replace System.out.* 
     *          methods, and redirect output to a message string.
     *          The calling interface reads the messages spooled and 
     *          controls how they are displayed.
     ****************************************************************/

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();

        history = new Stack<>();
        parser = new Parser();
        messages = "";
        wantToQuit = false;
        printWelcome();
        objects = new HashSet<>();
        members = new HashSet<>();
    }    

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room center, castle, street, Tomorrowland, Adventureland, Carrousel, Fantasyland,
        LibertySquare, Frontierland, entrance, PirateRide, inPirateRide, SplashMount,
        inSplashMount, HauntedMansion, inHauntedMansion, UtSRide, inUtSRide, SpaceMount,
        inSpaceMount;

        center = new Room("the center of Magic Kingdom");
        castle = new Room("in front of Cinderella's Castle");
        street = new Room("on Main Street");
        Tomorrowland = new Room("in Tomorrowland");
        Adventureland = new Room("in Adventureland");
        Carrousel = new Room("in front of Prince Charmming's Carrousel");
        Fantasyland = new Room("in Fantasyland");
        LibertySquare = new Room("in Liberty Square");
        Frontierland = new Room("in Frontierland");
        entrance = new Room("the front entrance of the Magic Kingdom");
        PirateRide = new Room("outside the Pirates of the Carribbean ride");
        inPirateRide = new Room("inside the Pirates of the Carribbean ride");
        SplashMount = new Room("outside Splash Mountain ride");
        inSplashMount = new Room("inside the Splash Mountain ride");
        HauntedMansion = new Room("outside the Haunted Mansion ride");
        inHauntedMansion = new Room("inside the Haunted Mansion ride");
        UtSRide = new Room("outside the Under the Sea ride");
        inUtSRide = new Room("inside the Under the Sea ride");
        SpaceMount = new Room("outside the Space Mountain ride");
        inSpaceMount = new Room("inside the Space Mountain ride");

        // assign images
        center.setImage("stock-photo-2098903.jpg");
        //         theatre.setImage("lecture-hall.jpg");
        //         pub.setImage("cozy-little-pub.jpg");
        //         lab.setImage("computer-lab.jpg");
        //         office.setImage("cluttered-office.jpg");

        //center.setExits(castle, Tomorrowland, street, Adventureland);
        center.setExit("north", castle);
        center.setExit("north-east", Fantasyland);
        center.setExit("north-west", LibertySquare);
        center.setExit("east", Tomorrowland);
        center.setExit("south", street);
        center.setExit("west", Adventureland);
        center.addItem("a backpack", 0);

        castle.setExit("north", Carrousel);
        castle.setExit("south", center);

        castle.addCharacter("Mickey Mouse");

        street.setExit("north", center);
        street.setExit("south", entrance);

        Tomorrowland.setExit("north", Fantasyland);
        Tomorrowland.setExit("west", center);
        Tomorrowland.setExit("east", SpaceMount);
        SpaceMount.setExit("west", Tomorrowland);
        SpaceMount.setExit("ride", inSpaceMount);
        SpaceMount.addCharacter("Rapunzel");
        inSpaceMount.setExit("outside", SpaceMount);
        inSpaceMount.addFamily("your brother, Andrew");

        Adventureland.setExit("north-east", LibertySquare);
        Adventureland.setExit("east", center);
        Adventureland.setExit("west", PirateRide);
        Adventureland.setExit("north", Frontierland);
        PirateRide.setExit("east", Adventureland);
        PirateRide.setExit("ride", inPirateRide);
        PirateRide.addCharacter("Hiro and Baymax");
        inPirateRide.setExit("outside", PirateRide);
        inPirateRide.addFamily("your sister, Sarah");

        Carrousel.setExit("east", Fantasyland);
        Carrousel.setExit("south", castle);
        Carrousel.setExit("west", LibertySquare);

        Fantasyland.setExit("west", Carrousel);
        Fantasyland.setExit("south-west", center);
        Fantasyland.setExit("south", Tomorrowland);
        Fantasyland.setExit("north", UtSRide);
        UtSRide.setExit("south", Fantasyland);
        UtSRide.setExit("ride", inUtSRide);
        inUtSRide.setExit("outside", UtSRide);
        inUtSRide.addItem("Mickey's magic hat", 3);
        inUtSRide.addItem("a small shell", 2);

        LibertySquare.setExit("south-west", Adventureland);
        LibertySquare.setExit("east", Carrousel);
        LibertySquare.setExit("south-east", center);
        LibertySquare.setExit("west", HauntedMansion);
        HauntedMansion.setExit("east", LibertySquare);
        HauntedMansion.setExit("ride", inHauntedMansion);
        inHauntedMansion.setExit("outside", HauntedMansion);
        inHauntedMansion.addItem("Rapunzel's crown", 10);

        Frontierland.setExit("south", Adventureland);
        Frontierland.setExit("north", SplashMount);
        SplashMount.setExit("south", Frontierland);
        SplashMount.setExit("ride", inSplashMount);
        inSplashMount.setExit("outside", SplashMount);
        inSplashMount.addItem("Baymax's microchip", 1);
        inSplashMount.addItem("a towel that was left behind", 4);

        entrance.setExit("north", street);
        entrance.addFamily("your mother");
        entrance.addFamily("your father");

        child = new Player(center);
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        Room currentRoom = child.getCurrentRoom();
        println("Welcome to the World of Zuul!");
        println("World of Zuul is a new, incredibly boring adventure game.");
        println("Type 'help' if you need help.");
        println();
        println("You are " + currentRoom.getLongDescription());

        println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, 
     * otherwise false is returned.
     */
    private void processCommand(Command command) 
    {
        if(command.isUnknown()) {
            println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if(commandWord.equals("help")) {
            printHelp();
        }
        else if(commandWord.equals("go")) {
            goRoom(command);
        }
        else if(commandWord.equals("quit")) {
            quit(command);
        }else if(commandWord.equals("look")){//6.14
            look();
        }else if(commandWord.equals("take")){//6.15
            take(command);
        }else if(commandWord.equals("back")){//6.23
            back();
        }else if(commandWord.equals("bring")){
            bring(command);
        }else if(commandWord.equals("drop")){
            drop(command);
        }else if(commandWord.equals("inventory")){
            inventory();
        }else if(commandWord.equals("leave")){
            leave();
        }else if(commandWord.equals("ask")){
            ask(command);
        }
        else if(commandWord.equals("give")){
            give(command);
        }
    }

    // implementations of user commands:
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        println("You are lost. You are alone. You wander");
        println("around in the Magic Kingdom. You must find your family.");
        println("If you see a character, you can ask them for help, but ");
        println("they might ask for your help.");
        println();
        println("Your command words are:");
        println(parser.showCommands());
    }

    private void look()
    {
        Room currentRoom = child.getCurrentRoom();
        println(currentRoom.getLongDescription());
    }

    private void back(){
        println(child.back());
    }

    private void take(Command command) 
    {
        if(!command.hasSecondWord()) {
            println("Take what?");
            return;
        }

        String itemName = command.getSecondWord();

        String result = child.takeItem(itemName);
        println(result);
    }

    private void bring(Command command) 
    {
        if(!command.hasSecondWord()) {
            println("Bring who?");
            return;
        }
        String memName = command.getSecondWord();
        if(memName.equals("Sarah") || memName.equals("Andrew") || memName.equals("mother")
        || memName.equals("father")){
            String result = child.bringFam(memName);
            Room currentRoom = child.getCurrentRoom();
            currentRoom.bringFam(memName);
            println(result);
        }else{
            println("That is not an family member. Try take if you have found an item."); 
        }

    }

    private void inventory(){
        println(child.checkInventory());
    }

    private void drop(Command command) 
    {
        Room currentRoom = child.getCurrentRoom();
        if(!command.hasSecondWord()) {
            println("Drop what?");
            return;
        }

        String itemName = command.getSecondWord();
        String result = child.dropItem(itemName);

        println(result);
    }

    private void leave(){
        Room currentRoom = child.getCurrentRoom();
        if(!currentRoom.getLongDescription().contains("entrance")){
            println("You have to leave the park from the entrance.");
        }else if(currentRoom.getLongDescription().contains("entrance") && 
        members.size() == 4){
            println("Congratulations, you have won the game!");
            wantToQuit = true;
        }else if(currentRoom.getLongDescription().contains("entrance") && 
        members.size() != 4){
            println("Sorry, you left without all of your family and lost.");
            wantToQuit = true;
        }

    }

    private void ask(Command command){
        Room currentRoom = child.getCurrentRoom();
        if(!command.hasSecondWord()) {
            println("Ask who?");
            return;
        }

        String charName = command.getSecondWord();
        if(!currentRoom.getCharacterString().contains(charName)){
            println("Sorry, that character is not in this room.");
            return;
        }else if(charName.contains("Mickey")){
            println("Hi child! I might have seen your sister, but I ");
            println("am so worried about my lost hat that I cannot remember ");
            println("where she was. If you can find my hat and bring it ");
            println("to me, I might be able to remember.");

        }else if(charName.contains("Hiro")){
            println("Hey! Baymax and I might have come across your brother. He could ");
            println("show you exactly where if I could find his microchip. If you ");
            println("find it and bring it to us, I will put it back in him and ");
            println("he can show you where he saw your brother.");
        }else if(charName.contains("Rapunzel")){
            println("Well, hello there. Yes, I think I did spot your parents. They ");
            println("were together looking for you and your siblings. Flinn was going ");
            println("to help them, but he also took off with my crown again. If you can ");
            println("find my crown, you might find them!");
        }
    }

    private void give(Command command) 
    {
        Room currentRoom = child.getCurrentRoom();
        if(!command.hasSecondWord()) {
            println("Give what?");
            return;
        }

        String itemName = command.getSecondWord();

        if(currentRoom.getCharacterString().contains("Mickey") && itemName.contains("hat")){
            println("Thank you so much for bringing me my magic hat! Now I can ");
            println("help grant wishes for the kids, including you. ");
            println("I remember where I saw your sister. She was at the Pirates of ");
            println("the Caribbean ride. ");
        }else if(currentRoom.getCharacterString().contains("Mickey") && !itemName.contains("hat")){
            println("I am sorry, that is not my hat. There might be another ");
            println("character in the Kingdom that needs that though. ");
        }else if(currentRoom.getCharacterString().contains("Hiro") && itemName.contains("microship")){
            println("Thank you so much for finding Baymax's microchip! ");
            println("Let's look back at the videos he has recorded and see where your brother was. ");
            println("That's it! We saw him at Space Mountain. ");
        }else if(currentRoom.getCharacterString().contains("Hiro") && !itemName.contains("microchip")){
            println("Awe man, that actually isn't his microchip. There might be another ");
            println("character in the Kingdom that needs that though. ");
        }else if(currentRoom.getCharacterString().contains("Rapunzel") && itemName.contains("crown")){
            println("My crown! Oh, you did not find Flynn and your parents? I'm sorry. ");
            println("Is there a place you all decided to meet if you were lost? ");
            println("They might be at the entrance to the park. That would be an easy place to meet. ");
        }else if(currentRoom.getCharacterString().contains("Rapunzel") && !itemName.contains("crown")){
            println("Thank you for helping me, but this is not my crown. There might be another ");
            println("character in the Kingdom that needs that though. ");

        }
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        println(child.goRoom(direction));

    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private void quit(Command command) 
    {
        if(command.hasSecondWord()) {
            println("Quit what?");
            wantToQuit = false;
        }
        else {
            println("Thank you for playing.  ");
            wantToQuit = true;  // signal that we want to quit
        }
    }

    /****************************************************************
     * Methods added 2012-06-27 by William H. Hooper
     ****************************************************************/

    /**
     * process commands or queries to the game
     * @param input user-supplied input
     */
    public void processInput(String input)
    {
        if(wantToQuit) {
            println("Sorry, this game is over.");
            return;
        }

        if(input.trim().equals("")) {
            println();
            return;
        }

        Command command = parser.getCommand(input);
        processCommand(command);
    }

    /**
     * clear and return the output messages
     * @return current contents of the messages.
     */
    public String readMessages()
    {
        String oldMessages = messages;
        messages = "";
        return oldMessages;
    }

    /**
     * @return true when the game is over.
     */
    public boolean finished()
    {
        return wantToQuit;
    }

    /**
     * add a message to the output list.
     * @param message the string to be displayed
     */
    private void print(String message)
    {
        messages += message;
    }

    /**
     * add a line to the output list.
     */
    private void println()
    {
        messages += "\n";
    }

    /**
     * add a message to the output list, 
     * followed by newline.
     * @param message the string to be displayed
     * @see readMessages
     */
    private void println(String message)
    {
        messages += message + "\n";
    }

    /**
     * @return an Image from the current room
     * @see Image
     */
    public String getImage()
    {
        Room currentRoom = child.getCurrentRoom();
        return currentRoom.getImage();
    }
}
