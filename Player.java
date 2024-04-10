import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private static int XP = 0;
    private static Scanner scanner = new Scanner(System.in);
    private String name;
    private int level;
    private int extraLives;

    Player(String name, int level, int extraLives) {
        this.name = name;
        this.level = level;
        this.extraLives = extraLives;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setLevel(int newLevel) {
        this.level = newLevel;
    }

    public void setExtraLives(int newLives) {
        this.extraLives = newLives;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getExtraLives() {
        return extraLives;
    }

    public void addExtraLives(int extraLives) {
        this.extraLives += extraLives; // Add the received extra lives to the current count
    }
    
    /**
     * Adds levels to the player based on the XP bonus received.
     * @param xpBonus The XP bonus received.
     */
    public void addLevel(int xpBonus) {
        int levelUpRequirement = calculateXPToNextLevel();
        int requiredXP = levelUpRequirement - xpBonus;

        if (requiredXP <= 0) {
            level++;
            System.out.println("Congratulations! You leveled up to level " + level + "!");
        } else {
            System.out.println("You need " + requiredXP + " more XP to level up.");
        }
    }

    private int calculateXPToNextLevel() {
        return (level * 50) + 50;
    }

    public void addXP(int xpAmount) {
        XP += xpAmount; // Add the received XP to the current XP
        checkLevelUp(); // Check if leveling up after adding XP
    }
    
    private void checkLevelUp() {
        int xpToNextLevel = calculateXPToNextLevel();
        if (XP >= xpToNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        System.out.println(name + " leveled up to level " + level + "!");
    }

    public Fighters pickFighter() {
        ArrayList<Fighters> fighterList = new ArrayList<>();
        fighterList.add(new Fighters("Sid", 1, "lightweight", 100.0));
        fighterList.add(new Fighters("Param", 1, "heavyweight", 100.0));

        System.out.println("Name Lvl Type Health");
        for (Fighters f : fighterList) {
            System.out.println(f.getName() + " " + f.getLevel() + " " + f.getType() + " " + f.getHealth());
        }

        System.out.println("Pick a character using the numeric value: Sid (1) or Param (2)");
        int choice = scanner.nextInt();
        Fighters selectedFighter = fighterList.get(choice - 1);

        System.out.println("You have chosen " + selectedFighter.getName());
        System.out.println("Level: " + selectedFighter.getLevel());
        System.out.println("Type: " + selectedFighter.getType());
        System.out.println("Health: " + selectedFighter.getHealth());

        return selectedFighter;
    }

    public static void main(String[] args) {
        // Example usage or additional functionality can be added here
    }

    public int getXP() {
        return XP;
    }
    
}
