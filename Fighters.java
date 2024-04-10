import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Fighters {
private static final int XP = 0;
private String name;
private int level;
private String type;
private double health;
private int damageBonus;



public Fighters(String name, int level, String type, double health) {
this.name = name;
this.level = level;
this.type = type;
this.setHealth(health);


}


public void setHealth(double newHealth) {
    this.health = newHealth;
    }


public String getName() {
return this.name;
}


public int getLevel() {
return this.level;
}


public String getType() {
return this.type;
}

public int getDamageBonus() {
    return damageBonus;
}


public void dealtDamage(double damageAmount) {
health -= damageAmount; //Used ChatGPT to clarify how to subtract health (I thought we had to use setter, but ran into an error as object was inaccessible)
//System.out.println("Opponent now has " + health);
}


public double getHealth () {
return this.health;
}




public void attack(Fighters opponent, Fighters attackerName , String attackName, double damage) {
System.out.println(opponent.getName() + " 's current health is " + opponent.getHealth() + "." + attackerName.getName() + " did " + attackName + ". It did " + damage + ".");
    opponent.dealtDamage(damage);
    System.out.println(opponent.getName() + " has " + opponent.getHealth());
   }
   

public void dodge(Fighters attackerName, Fighters opponent, double damage) { //fix dodge methpd
    boolean dodge;
    Random random = new Random();
    int ifDodge = random.nextInt(5) + 1; // 1 to 6
    if(ifDodge < 3) {
    dodge = true;
    System.out.println("You have dodged the attack! The enemy wasn't expecting it, so you were able to land an attack!");
    System.out.println("The enemy lost all their health!");
   //set enemy health to 0
    opponent.dealtDamage(damage);
    }
    else {
    dodge = false;
    System.out.println("The dodge failed - the enemy got their attack!");
    System.out.println("You weren't expecting the attack! You lost all your health!");
    //set player's fighter health to 0
    attackerName.dealtDamage(attackerName.getHealth());
    }
    }


    public void gainXP(int xp) {
        System.out.println(name + " gained " + xp + " XP.");
         int XP = xp;
        // Check if level up
        checkLevelUp();
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
   
    private int calculateXPToNextLevel() { //Chatgpt
        // Calculate XP required to reach the next level based on     formula
        return (level * 50) + 50; //the player needs 100 XP to reach level 2.
    }
   

    public void addDamageBonus(int damageBonus) {
        this.damageBonus += damageBonus;
    }


    public static void main(String[] args) {
    /*Fighters aayan = new Fighters("name" ,1, "large" , 100.0 );
    Fighters kundan = new Fighters("name" , 1, "agile", 100.0);
    ArrayList<Fighters> fighterList = new ArrayList<>();
    Collections.addAll(fighterList, kundan, aayan);
    for(Fighters f: fighterList) {
        System.out.println(f.name);
    */
       
    Fighters kundan = new Fighters("name" , 1 , "agile" , 100.0);
    Fighters enemy = new Fighters("enemy", 1, "agile" , 90.0);


    //kundan.attack(enemy, "punch", 10);
   // enemy.attack(kundan, "kick", 15);


    // int xpGained = 50;
    // kundan.gainXP(xpGained);


    System.out.println("Kundan's health: " + kundan.getHealth());
    System.out.println("Enemy's health: " + enemy.getHealth());
    }


}