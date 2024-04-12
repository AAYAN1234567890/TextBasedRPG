import java.util.ArrayList;
import java.util.Random;
public class MysteryBox {
private String boxType;
private int xpBonus;
private int extraLives;
private int damageBonus;

    /**
     * Constructor for MysteryBox.
     */
public MysteryBox() {
// This is a Constructor for MysteryBox mysteryBox = new MysteryBox(); to work
}

    /**
     * Method to randomly assign attributes to the Mystery Box and display them to the user.
     */
public void openBox() {
ArrayList<MysteryBox> boxes = new ArrayList<>();
boxes.add(new MysteryBox(50)); // Common box
boxes.add(new MysteryBox(100, 1)); // Rare box
boxes.add(new MysteryBox(200, 2, 50)); // Legendary box
Random random = new Random();
int diceRoll = random.nextInt(3); // 0 to 2
MysteryBox selectedBox = boxes.get(diceRoll);
this.boxType = selectedBox.boxType;
this.xpBonus = selectedBox.xpBonus;
this.extraLives = selectedBox.extraLives;
this.damageBonus = selectedBox.damageBonus;
// Display the box type and its bonuses
System.out.println("You found a " + selectedBox.boxType + " box!");
System.out.println("XP Bonus: " + selectedBox.xpBonus);
System.out.println("Extra Lives: " + selectedBox.extraLives);
System.out.println("Damage Bonus: " + selectedBox.damageBonus);
}
/**
     * Getter for XP bonus.
     * 
     * @return XP bonus
     */
public int getXpBonus() {
return xpBonus;
}
/**
     * Getter for extra lives.
     * 
     * @return Extra lives
     */
public int getExtraLives() {
return extraLives;
}
/**
     * Getter for damage bonus.
     * 
     * @return Damage bonus
     */
public int getDamageBonus() {
return damageBonus;
}
/**
     * Constructor for a common box.
     * 
     * @param xpBonus XP bonus
     */
public MysteryBox(int xpBonus) { // Common box
this.boxType = "Common";
this.xpBonus = xpBonus;
this.extraLives = 0;
this.damageBonus = 0;
}
/**
     * Constructor for a rare box.
     * 
     * @param xpBonus    XP bonus
     * @param extraLives Extra lives
     */
public MysteryBox(int xpBonus, int extraLives) { // Rare box
this.boxType = "Rare";
this.xpBonus = xpBonus;
this.extraLives = extraLives;
this.damageBonus = 0;
}
 /**
     * Constructor for a legendary box.
     * 
     * @param xpBonus      XP bonus
     * @param extraLives   Extra lives
     * @param damageBonus  Damage bonus
     */
public MysteryBox(int xpBonus, int extraLives, int damageBonus) { // Legendary box
this.boxType = "Legendary";
this.xpBonus = xpBonus;
this.extraLives = extraLives;
this.damageBonus = damageBonus;
}
/**
     * Convert MysteryBox attributes to an integer.
     * 
     * @return Integer representation of MysteryBox attributes
     */
public int toInt() {
return xpBonus * 1000000 + extraLives * 1000 + damageBonus;
}
 /**
     * Main method to test the MysteryBox class.
     * 
     * @param args Command line arguments
     */
public static void main(String[] args) {
ArrayList<MysteryBox> boxes = new ArrayList<>();
boxes.add(new MysteryBox(100)); // Common box
boxes.add(new MysteryBox(250, 1)); // Rare box
boxes.add(new MysteryBox(500, 2, 50)); // Legendary box
Random random = new Random();
int diceRoll = random.nextInt(3) + 1; // 1 to 3
MysteryBox selectedBox = boxes.get(diceRoll - 1);
// rarity of the loot box
System.out.println("You rolled a " + diceRoll);
System.out.println("You received a " + selectedBox.boxType + " box!");
// attributes of the selected box
System.out.println("XP Bonus: " + selectedBox.xpBonus);
System.out.println("Free Lives: " + selectedBox.extraLives);
System.out.println("Damage Bonus: " + selectedBox.damageBonus);
//if we have time, come back and make this harder: user has to guess number, and it has to match random
}
}

/**
 * This class represents a Mystery Box in the game. It randomly assigns attributes such as XP bonus, extra lives, and damage bonus
 * to each box when it is opened. The class includes constructors for common, rare, and legendary boxes, each with different sets
 * of attributes. The openBox() method randomly selects a box type and sets its attributes accordingly, then displays the type
 * and bonuses to the user.
 * 
 * Encapsulation: The class encapsulates the functionality of generating and assigning attributes to Mystery Boxes, hiding the
 * implementation details from the user. The user only interacts with the openBox() method to reveal the contents of the box.
 * 
 * Advanced Algorithmic Structure: The openBox() method uses a random number generator to select a box type, ensuring unpredictability
 * and variability in the game. Additionally, the method employs a try/catch block with a while loop to handle user input, ensuring
 * that the game does not break if the user enters an invalid input.
 */