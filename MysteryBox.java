// import java.util.ArrayList;
// import java.util.Random;


// public class MysteryBox {
//     String boxType;
//     int xpBonus;
//     int freeLives;
//     int damageBonus;


//     MysteryBox(int newXPBonus) { // Common box
//         boxType = "Common";
//         xpBonus = newXPBonus;
//         freeLives = 0;
//         damageBonus = 0;
//     }


//     MysteryBox(int newXPBonus, int newFreeLives) { // Rare box
//         boxType = "Rare";
//         xpBonus = newXPBonus;
//         freeLives = newFreeLives;
//         damageBonus = 0;
//     }


//     MysteryBox(int newXPBonus, int newFreeLives, int newDamageBonus) { // Legendary box
//         boxType = "Legendary";
//         xpBonus = newXPBonus;
//         freeLives = newFreeLives;
//         damageBonus = newDamageBonus;
//     }


//     public int toInt() {
//         return xpBonus * 1000000 + freeLives * 1000 + damageBonus;
//     }


//     public static void main(String[] args) {

//         ArrayList<MysteryBox> boxes = new ArrayList<>();
//         boxes.add(new MysteryBox(50)); // Common box
//         boxes.add(new MysteryBox(100, 1)); // Rare box
//         boxes.add(new MysteryBox(200, 2, 50)); // Legendary box


//         Random random = new Random();
//         int diceRoll = random.nextInt(3) + 1; // 1 to 3


//         MysteryBox selectedBox = boxes.get(diceRoll - 1);


//         // rarity of the loot box
//         //System.out.println("You rolled a " + diceRoll);
//         System.out.println("You received a " + selectedBox.boxType + " box!");


//         // attributes of the selected box
//         System.out.println("XP Bonus: " + selectedBox.xpBonus);
//         System.out.println("Free Lives: " + selectedBox.freeLives);
//         System.out.println("Damage Bonus: " + selectedBox.damageBonus);
//         //if we have time, come back and make this harder: user has to guess number, and it has to match random
//     }

//     }

import java.util.ArrayList;
import java.util.Random;

public class MysteryBox {
    private String boxType;
    private int xpBonus;
    private int extraLives;
    private int damageBonus;

    public MysteryBox() {
        // This is a Constructor for MysteryBox mysteryBox = new MysteryBox(); to work
    }

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

    public int getXpBonus() {
        return xpBonus;
    }

    public int getExtraLives() {
        return extraLives;
    }

    public int getDamageBonus() {
        return damageBonus;
    }

    public MysteryBox(int xpBonus) { // Common box
        this.boxType = "Common";
        this.xpBonus = xpBonus;
        this.extraLives = 0;
        this.damageBonus = 0;
    }

    public MysteryBox(int xpBonus, int extraLives) { // Rare box
        this.boxType = "Rare";
        this.xpBonus = xpBonus;
        this.extraLives = extraLives;
        this.damageBonus = 0;
    }

    public MysteryBox(int xpBonus, int extraLives, int damageBonus) { // Legendary box
        this.boxType = "Legendary";
        this.xpBonus = xpBonus;
        this.extraLives = extraLives;
        this.damageBonus = damageBonus;
    }
}
