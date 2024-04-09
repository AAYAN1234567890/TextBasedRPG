import java.util.Scanner;
public class Game {
private static Scanner scanner = new Scanner(System.in);
private static Player player;
//private static Fighters optionOne = new Fighters("MK" , 1 , "agile" , 100.0); //replace this with actual player chosen fighter
private static Fighters selectedFighter;
private static Fighters enemyOne = new Fighters("Dhvanay" , 50 , "megaweight" , 70.0 );
public static void main(String[] args) {
Game game = new Game();
game.menu();
game.stageOne();
}
private void menu() {
System.out.println("Welcome to our game. Enter name");
String name = scanner.nextLine();
player = new Player(name, 1, 0, 0);
System.out.println("Hello " + name.toUpperCase() + "!");
selectedFighter = player.pickFighter();
wait(2);
clear();
System.out.println("Lets begin the game.");
System.out.println("Initial Stats:");
playerStats();
wait(4);
clear();
}
private void stageOne() {
step();
pickDirection();
String choice = scanner.nextLine();
if (choice.equalsIgnoreCase("left")) {
clear();
System.out.println("Going left.");
wait(1);
clear();
step();
System.out.println("Woah! You see a mystery box!");
System.out.println("Enter open.");
String openBox = scanner.nextLine();
if (openBox.equalsIgnoreCase("open")) {
clear();
openMysteryBox();
}
else {
System.out.println("Go ahead, open the box!");
}
} else {
clear();
System.out.println("Going right.");
wait(1);
clear();
step();
System.out.println("You have encountered any enemy! Fight or flight?");
String engage = scanner.nextLine();
if (engage.equalsIgnoreCase("fight")) {
clear();
fightOne();
}
else if (engage.equalsIgnoreCase("flight")) {
clear();
System.out.println("You have chosen to flee.");
if(player.getExtraLives() == 0) {
System.out.println("However, that has a cost.");
System.out.println("As a violation of the fighter code, you are no longer allowed to fight.");
System.out.println("Game over.");
}
}
}
}
private static void stageTwo() {
step();
pickDirection();
String choice = scanner.nextLine();
if (choice.equalsIgnoreCase("left")) {
System.out.println("You have encountered any enemy! Time to fight!"); // Aayan: add fight method
fightTwo(true); // Pass true for extra damage
}
else {
System.out.println("riddle"); // i'll do this
}
}
private static void openMysteryBox() {
MysteryBox mysteryBox = new MysteryBox();
mysteryBox.openBox();
int xpBonus = mysteryBox.getXpBonus();
int extraLives = mysteryBox.getExtraLives();
int damageBonus = mysteryBox.getDamageBonus();
player.addXP(xpBonus);
player.addExtraLives(extraLives);
selectedFighter.addDamageBonus(damageBonus);
System.out.println("Updated Stats:");
System.out.println("XP: " + player.getXP());
System.out.println("Level: " + player.getLevel());
System.out.println("Extra Lives: " + player.getExtraLives());
System.out.println("Damage Bonus: " + selectedFighter.getDamageBonus());
wait(3);
clear();
System.out.println("Onto the next level!");
wait(1);
clear();
wait(1);
stageTwo();
}
private static void fightOne() {
System.out.println("Let's begin!");
wait(1);
clear();
enemyOneStats();
wait(3);
clear();
do {
attackSelect();
int attackChoice = scanner.nextInt();
if(attackChoice == 1) {
clear();
selectedFighter.attack(enemyOne, selectedFighter, "punch", 15.0);
enemyOne.attack(selectedFighter, enemyOne, "uppercut" , 50.0);
wait(3);
clear();
}
if (attackChoice == 2) {
clear();
selectedFighter.attack(enemyOne, selectedFighter, "kick", 20.0);
enemyOne.attack(selectedFighter, enemyOne, "uppercut" , 50.0);
wait(3);
clear();
}
if(attackChoice == 3) {
clear();
selectedFighter.dodge(selectedFighter, enemyOne, enemyOne.getHealth());
checkPlayerChances();
}
} while(enemyOne.getHealth() > 0 && selectedFighter.getHealth() > 0);
if (selectedFighter.getHealth() == 0) {
gameOverMessage();
}
else {
System.out.println("Onto the next level!");
stageTwo();
}
}

private static void fightTwo(boolean extraDamage) {
    System.out.println("Let's begin!");
    wait(1);
    clear();
    enemyOneStats();
    wait(3);
    clear();
    do {
        attackSelect();
        int attackChoice = scanner.nextInt();
        if (attackChoice == 1) {
            clear();
            if (extraDamage)
                selectedFighter.attack(enemyOne, selectedFighter, "punch", 15.0 + selectedFighter.getDamageBonus());
            else
                selectedFighter.attack(enemyOne, selectedFighter, "punch", 15.0);
            enemyOne.attack(selectedFighter, enemyOne, "uppercut", 50.0);
            wait(3);
            clear();
        }
        if (attackChoice == 2) {
            clear();
            if (extraDamage)
                selectedFighter.attack(enemyOne, selectedFighter, "kick", 20.0 + selectedFighter.getDamageBonus());
            else
                selectedFighter.attack(enemyOne, selectedFighter, "kick", 20.0);
            enemyOne.attack(selectedFighter, enemyOne, "uppercut", 50.0);
            wait(3);
            clear();
        }
        if (attackChoice == 3) {
            clear();
            if (extraDamage) {
                // if player has extra damage, see whether the dodge is successful or not.
                if (isDodgeSuccessful()) {
                    System.out.println("You dodged the attack!");
                } else {
                    System.out.println("You failed to dodge the attack!");
                    // selectedFighter.dodge(selectedFighter, enemyOne, enemyOne.getHealth());
					enemyOne.attack(selectedFighter, enemyOne, "uppercut" , 50.0);
                    checkPlayerChances();
                }
            } else {
                // Without extra damage, dodge is always successful
                System.out.println("You dodged the attack!");
            }
        }
    } while (enemyOne.getHealth() > 0 && selectedFighter.getHealth() > 0);
    if (selectedFighter.getHealth() == 0) {
        
		gameOverMessage();
    } else {
        System.out.println("You won the fight!");
        
    }
}

private static boolean isDodgeSuccessful() {
    // Generate a random number between 1 and 100
    int randomNum = (int) (Math.random() * 100) + 1;
    // 70% chance of successful dodge
    return randomNum <= 70;
}

private static void attackSelect() {
System.out.println("Pick an attack!");
System.out.println("Punch (1) - 15 damage");
System.out.println("Kick (2) - 20 damage");
System.out.println("Dodge (3) - Avoid enemy's next attack");
}
private static void gameOverMessage() {
System.out.println("You may have lost, but taking on a stronger opponent is impressive.");
}
private static void checkPlayerChances() {  // in fighter class, set enemy health to 0 and set player health to 0 so this method works
if (selectedFighter.getHealth() == 0) {
if (player.getExtraLives() == 0) {
System.out.println("Game over.");
}
else {
System.out.println("You have another chance!");
}
}
else if (enemyOne.getHealth() == 0) {
System.out.println("You win!");
player.addXP(750);
}
}
private static void enemyOneStats() {
System.out.println("Enemy name: " + enemyOne.getName());
System.out.println("Level: " + enemyOne.getLevel());
System.out.println("Type: " + enemyOne.getType());
System.out.println("Health: " + enemyOne.getHealth());
}
private static void playerStats() {
System.out.println("Player Name: " + player.getName());
System.out.println("Level: " + player.getLevel());
System.out.println("XP: " + player.getXP());
}
private static void wait(int sec) { //brought this over from our old CS Project (Text-Based RPG)
try {
Thread.sleep(sec * 1000);
} catch (InterruptedException e) {
e.printStackTrace();
}
}
private static void clear() {
for (int i = 0; i < 50; i++) System.out.println();
}
private static void step() {
for(int i = 0; i < 6; i ++ ) {
System.out.println("Step.");
wait(1);
}
}
private static void pickDirection() {
System.out.println("Left or right?");
}
}
