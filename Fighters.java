import java.util.Random;

public class Fighters {

private int XP = 0;

private String name;

private int level;

private String type;

private double health;

private int damageBonus;

// CONSTRUCTOR

public Fighters(String name, int level, String type, double health) {

this.name = name;

this.level = level;

this.type = type;

this.setHealth(health);

}

// PROCESSING 

/*

* Method allows for player health to be subtracted, used in conjunction with attack and dodge (this method is called upon) 

@return damageAmount = double

*/

public void dealtDamage(double damageAmount) {

health -= damageAmount; //Used ChatGPT to clarify how to subtract health (I thought we had to use setter, but ran into an error)

//System.out.println("Opponent now has " + health);

}

/*

* runs the player attack, which prints current health, runs attack based on user input, and then calls dealtDamage()

to subtract player health

@param opponent: Fighters, attackerName: opponent, attackName : String, damage: double

*/

public void attack(Fighters opponent, Fighters attackerName , String attackName, double damage) {

System.out.println(opponent.getName() + "'s current health is " + opponent.getHealth() + ". " + attackerName.getName() + " did " + attackName + ". It did " + damage + " damage.");

opponent.dealtDamage(damage);

if (opponent.getHealth() < 0) { //before this, printed out negative health in fight

System.out.println(opponent.getName() + " has 0.0 HP.");

}

else {

System.out.println(opponent.getName() + " has " + opponent.getHealth() + "HP.");

}

}

/*

* runs the player dodge - dodge occurs by chance using a random, and boolean dodge works with random to print out outcome

(dodge or no dodge) - based on outcome, dealtDamage is called upon to remove all of opposing player's health


@param opponent: Fighters, attackerName: opponent, attackName : String, damage: double

*/

public void dodge(Fighters attackerName, Fighters opponent, double damage) {

boolean dodge;

Random random = new Random();

int ifDodge = random.nextInt(5) + 1; // 1 to 6

if(ifDodge < 3) {

dodge = true;

System.out.println("You have dodged the attack! The enemy wasn't expecting it, so you were able to land an attack!");

System.out.println("The enemy lost all their health!");

opponent.dealtDamage(damage);

}

else {

dodge = false;

System.out.println("The dodge failed - the enemy got their attack!");

System.out.println("You weren't expecting the attack! You lost all your health!");

attackerName.dealtDamage(attackerName.getHealth()); //removes rest of player health

}

}

/* Method adds XP based on mystery boxes being accessed or winning fights, and then checkLevelUp() runs to see if 

player gained a level

@param xpAmount: int

*/

public void addXP(int xpAmount) {

XP += xpAmount;

System.out.println(name + " gained " + xpAmount + " XP. " + name + " now has " + XP + " XP in total.");

checkLevelUp();

}

/*how much XP is needed to move on to next level, increases with higher levels

* @return calculateXPToNextLevel: int - used in checkLevelUp() in calculation to see if XP requirement was met

*/

private int calculateXPToNextLevel() {

// Calculate XP required to reach the next level based on formula

return (level * 100); // Assuming 100 XP required for each level

}

/* Method called upon by addXP to see if level up occurs, based on calculation to see how much XP is required to level up,

and if fighter has that much xp accumulated, levelup() called if XP requirements are met

*/

private void checkLevelUp() {

int xpToNextLevel = calculateXPToNextLevel();

if (XP >= xpToNextLevel) {

levelUp();

}

}

//add level and cpnveys to user

private void levelUp() {

level++;

System.out.println(name + " leveled up to level " + level + "!");

// Reset XP to 0 after leveling up

XP = 0;

}


/* adding damage if mysteryBox is accessed and box holds an extra damage perk

* @param damageBonus: int

*/

public void addDamageBonus(int damageBonus) {

this.damageBonus += damageBonus;

}


// GETTERS

/*method accesses and prints fighter name

* @return name: String

*/

public String getName() {

return this.name;

}

/*method accesses and prints fighter level

* @return level: int

*/

public int getLevel() {

return this.level;

}

/*method accesses and prints fighter type

* @return type String

*/

public String getType() {

return this.type;

}

/*method accesses and prints fighter damage bonus

* @return damageBonus: int

*/

public int getDamageBonus() {

return damageBonus;

}

/*method accesses and prints fighter health

* @return health: double

*/

public double getHealth () {

return this.health;

}

//SETTERS

// method modifies player health - used primarily when resetting health of fighters when a fight resets (back to full health)

public void setHealth(double newHealth) {

this.health = newHealth;

}

public static void main(String[] args) {


}

}