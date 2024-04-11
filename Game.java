
import java.util.Random;
import java.util.Scanner;

public class Game {
private static Scanner scanner = new Scanner(System.in);
private static Player player;
private static Fighters selectedFighter;
private static Fighters enemyOne = new Fighters("Dhvanay" , 50 , "megaweight" , 70.0 );
private static Fighters enemyTwo = new Fighters("Harsha" , 3 , "mediumweight" , 100.0 );
private static Fighters enemyThree = new Fighters("Ali" , 3 , "heavyweight" , 100.0 );
private static boolean enemyAlive;

public static void main(String[] args) {
Game game = new Game();
game.menu();
}


private void menu() {
System.out.println("Welcome to our Kundan and Aayan's Text-Based RPG!");
wait(2);
System.out.println("Enter your name.");
String name = scanner.nextLine();
player = new Player(name, 1, 0, 1);
clear();
System.out.println("Hello " + name.toUpperCase() + "!");
wait(2);
clear();
System.out.println("Here are a list of fighters:");
selectedFighter = player.pickFighter();
wait(2);
clear();
System.out.println("Let's begin the game!");
wait(2);
clear();
System.out.println("Initial Stats:");
playerStats();
wait(4);
clear();
stageOne();
}

private static void stageOne() {
step();
clear();
pickDirection();
String choice = scanner.nextLine();
if (choice.equalsIgnoreCase("left")) {
goLeft();
System.out.println("Woah! You see a mystery box!");
System.out.println("Enter open.");
while (true) {
    try {
        String openBox = scanner.nextLine().trim().toLowerCase();
        if (openBox.equals("open")) {
            clear();
            openMysteryBox();
            System.out.println("You have made it to the next level!");
            wait(2);
            clear();
            stageTwo();
        } else {
            System.out.println("Invalid input. Please type 'open' to open the box.");
        }
    } catch (Exception e) {
        System.out.println("Invalid input. Please type 'open' to open the box.");
    }
}

} else {
    goRight();
    System.out.println("You have encountered an enemy! Fight or flight?");
    while (true) {
        try {
            String engage = scanner.nextLine().trim().toLowerCase();
            if (engage.equals("fight")) {
                clear();
                fightOne();
                break;
            } else if (engage.equals("flight")) {
                clear();
                System.out.println("You have chosen to flee.");
                player.removeLives(1);
                if (player.getLives() == 0) {
                    System.out.println("However, that has a cost.");
                    System.out.println("As a violation of the fighter code, you are no longer allowed to fight.");
                    System.out.println("Game over.");
                }
                break;
            } else {
                System.out.println("Invalid input. Please type 'fight' to engage in combat or 'flight' to flee.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please type 'fight' to engage in combat or 'flight' to flee.");
        }
    }
}
}
private static void stageTwo() {
playerStats();
wait(3);
clear();
step();
clear();
pickDirection();
String choice = scanner.nextLine();
if (choice.equalsIgnoreCase("left")) {
goLeft();
System.out.println("You have encountered an enemy!");
fightTwo(true);

}
else if (choice.equalsIgnoreCase("right")) {
goRight();
riddle();
fightTwo(true);
}

}

private static void stageThree() {
playerStats();
wait(3);
clear();
step();
clear();
pickDirection();
scanner.nextLine(); //Debugging: closing scanner to get rid of "enter" processed by the previous scanner (taking int)
String choice = scanner.nextLine();
if(choice.equalsIgnoreCase("left")) {
goLeft();
System.out.println("Woah! You see a mystery box!");
System.out.println("Enter open.");
while (true) {
    try {
        String openBox = scanner.nextLine().trim().toLowerCase();
        if (openBox.equals("open")) {
            clear();
            openMysteryBox();
            System.out.println("You have made it to the next level!");
            wait(2);
            clear();
            stageTwo();
        } else {
            System.out.println("Invalid input. Please type 'open' to open the box.");
        }
    } catch (Exception e) {
        System.out.println("Invalid input. Please type 'open' to open the box.");
    }
}
}

else if (choice.equalsIgnoreCase("right")) {
goRight();
riddle();
fightThree(true);
}



}

private static void openMysteryBox() {
MysteryBox mysteryBox = new MysteryBox();
mysteryBox.openBox();
int xpBonus = mysteryBox.getXpBonus();
int extraLives = mysteryBox.getExtraLives();
int damageBonus = mysteryBox.getDamageBonus();
player.addXP(xpBonus);
player.addLives(extraLives);
selectedFighter.addDamageBonus(damageBonus);
System.out.println("Updated Stats:");
System.out.println("XP: " + player.getXP());
System.out.println("Level: " + player.getLevel());
System.out.println("Lives: " + player.getLives());
System.out.println("Damage Bonus: " + selectedFighter.getDamageBonus());
wait(3);
clear();
}


private static void riddle() {
System.out.println("I see you that you'd like to fight.");
System.out.println("However, unauthorized individuals shall not pass.");
System.out.println("But, if you tell me the magic number between 1-20, I will let you pass.");
System.out.println("Enter a number.");
Random random = new Random();
int randomNumber = random.nextInt(20) + 1;
int guess;
do {
guess = scanner.nextInt();
if (guess < randomNumber) {
System.out.println("Higher!");
}
else if (guess > randomNumber) {
System.out.println("Lower!");
}
else if (guess == randomNumber) {
System.out.println("Perfect!");
}
}
while (!(guess == randomNumber));
clear();
System.out.println("Now you can proceed!");
//fightTwo(true);
}


private static void fightOne() {
System.out.println("Let's begin the fight!");
wait(2);
clear();
enemyOneStats();
wait(3);
clear();
enemyAlive = true;

while (player.getLives() > 0 && enemyAlive == true) {
//enemyAlive = true;
enemyOne.setHealth(75.0);
selectedFighter.setHealth(100.0);
System.out.println("Lives: " + player.getLives());

do {
attackSelect();
int attackChoice = scanner.nextInt();
if(attackChoice == 1) {
clear();
selectedFighter.attack(enemyOne, selectedFighter, "punch", 10.0);
enemyOne.attack(selectedFighter, enemyOne, "uppercut" , 40.0);
wait(3);
clear();
}

if (attackChoice == 2) {
clear();
selectedFighter.attack(enemyOne, selectedFighter, "kick", 20.0);
enemyOne.attack(selectedFighter, enemyOne, "uppercut" , 40.0);
wait(3);
clear();

}

if(attackChoice == 3) {
clear();
selectedFighter.dodge(selectedFighter, enemyOne, enemyOne.getHealth());
wait(3);
//checkPlayerChances();

}


} while(enemyOne.getHealth() > 0 && selectedFighter.getHealth() > 0);

if (selectedFighter.getHealth() <= 0) {
        enemyAlive = true;
        player.removeLives(1);
    } else if (enemyOne.getHealth() <= 0) {
        System.out.println("You won the fight!");
        enemyAlive = false;  
        player.addXP(150);
        selectedFighter.gainXP(150);
        playerStats();
        wait(5);
     
}
   }

if (enemyAlive) {
clear();
gameOver();
}
else {
clear();
System.out.println("Moving on to the next level!");
wait(2);
clear();
scanner.nextLine();
stageTwo();
}
}

private static void fightTwo(boolean extraDamage) {
System.out.println("Let's begin the fight!");
 wait(2);
   clear();
   enemyTwoStats();
   wait(3);
   clear();
enemyAlive = true;

while (player.getLives() > 0 && enemyAlive == true) { //ChatGPT: when verifying method for errors, mentioned to change from || to &&
//enemyAlive = true;
    enemyTwo.setHealth(100.0);
selectedFighter.setHealth(100.0);
System.out.println("Lives: " + player.getLives());

    do {
   
        attackSelect();
        int attackChoice = scanner.nextInt();
        if (attackChoice == 1) {
            clear();
            if (extraDamage)
                selectedFighter.attack(enemyTwo, selectedFighter, "punch", 10.0 + selectedFighter.getDamageBonus());
           
            else
                selectedFighter.attack(enemyTwo, selectedFighter, "punch", 10.0);
            enemyTwo.attack(selectedFighter, enemyTwo, "uppercut", 20.0);
            wait(3);
            clear();
        }
        if (attackChoice == 2) {
            clear();
            if (extraDamage)
                selectedFighter.attack(enemyTwo, selectedFighter, "kick", 20.0 + selectedFighter.getDamageBonus());
            else
                selectedFighter.attack(enemyTwo, selectedFighter, "kick", 20.0);
            enemyTwo.attack(selectedFighter, enemyTwo, "punch", 10.0);
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
                    enemyTwo.attack(selectedFighter, enemyTwo, "uppercut" , 25.0);
                    wait(3);
                    clear();
                }
            } else {
                // Without extra damage, dodge is always successful
                System.out.println("You dodged the attack!");
            }
        }
    } while (enemyTwo.getHealth() > 0 && selectedFighter.getHealth() > 0);
 
    if (selectedFighter.getHealth() <= 0 && enemyTwo.getHealth() <= 0) {
    coinFlip();
   
    } else if (selectedFighter.getHealth() <= 0) {
        enemyAlive = true;
        player.removeLives(1);
   
    } else if (enemyTwo.getHealth() <= 0) {
        System.out.println("You won the fight!");
        enemyAlive = false;      
        player.addXP(150);
        selectedFighter.gainXP(150);
        playerStats();  
     
}
}

if (enemyAlive) { //ChatGPT: while checking method for errors, mentioned that (enemyAlive == true) can be simplified to this line
gameOver();
}
else {
System.out.println("Moving on to the next level!");
wait(2);
clear();
stageThree();
}

}

private static void fightThree(boolean extraDamage) {
System.out.println("Let's begin!");
 wait(2);
   clear();
   enemyThreeStats();
   wait(3);
   clear();
enemyAlive = true;

while (player.getLives() > 0 && enemyAlive == true) {
//enemyAlive = true;
    enemyThree.setHealth(100.0);
selectedFighter.setHealth(100.0);
System.out.println("Lives: " + player.getLives());

    do {
   
        attackSelect();
        int attackChoice = scanner.nextInt();
        if (attackChoice == 1) {
            clear();
            if (extraDamage)
                selectedFighter.attack(enemyThree, selectedFighter, "punch", 10.0 + selectedFighter.getDamageBonus());
           
            else
                selectedFighter.attack(enemyThree, selectedFighter, "punch", 10.0);
            enemyThree.attack(selectedFighter, enemyThree, "uppercut", 20.0);
            wait(3);
            clear();
        }
        if (attackChoice == 2) {
            clear();
            if (extraDamage)
                selectedFighter.attack(enemyThree, selectedFighter, "kick", 20.0 + selectedFighter.getDamageBonus());
            else
                selectedFighter.attack(enemyThree, selectedFighter, "kick", 20.0);
            enemyThree.attack(selectedFighter, enemyThree, "punch", 10.0);
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
                    enemyThree.attack(selectedFighter, enemyThree, "uppercut" , 25.0);
                    wait(3);
                    clear();
                }
            } else {
                // Without extra damage, dodge is always successful
                System.out.println("You dodged the attack!");
            }
        }
    } while (enemyThree.getHealth() > 0 && selectedFighter.getHealth() > 0);
 
    if (selectedFighter.getHealth() <= 0 && enemyThree.getHealth() <= 0) {
    coinFlip();
} else if (selectedFighter.getHealth() <= 0) {
        enemyAlive = true;
        player.removeLives(1);
    } else if (enemyThree.getHealth() <= 0) {
        System.out.println("You won the fight!");
        enemyAlive = false;        
        player.addXP(150);
        selectedFighter.gainXP(150);
        playerStats();
     
}
}

if (enemyAlive) {
gameOver();
}
else {
wait(2);
clear();
System.out.println("Thank you for playing our game! We hope you've enjoyed it.");
wait(2);
clear();
System.out.println("Final Stats: ");
playerStats();
//add fighter stats? - ask kundan
wait(4);
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
System.out.println("Punch (1) - 10 damage");
System.out.println("Kick (2) - 20 damage");
System.out.println("Dodge (3) - Avoid enemy's next attack");
}

 /*private static void checkPlayerChances() {  // in fighter class, set enemy health to 0 and set player health to 0 so this method works
if (selectedFighter.getHealth() == 0) {
if (player.getExtraLives() == 0) {
System.out.println("Game over.");
}
else {
System.out.println("You have another chance!");
wait(3);
selectedFighter.setHealth(100.0);
player.setExtraLives(player.getExtraLives() - 1);
}
}
}
*/

private static void coinFlip() {
System.out.println("It has come to a coin flip!");
Random random = new Random();
int coinFlip = random.nextInt(2) + 1 ;

if (coinFlip == 1) {
System.out.println("Coin flip has come back in favour of enemy.");
enemyAlive = true;
player.removeLives(1);
}
else {
System.out.println("Coin flip has come back in favour of " + selectedFighter.getName());
enemyAlive = false;
}
}
private static void enemyOneStats() {
System.out.println("Enemy name: " + enemyOne.getName());
System.out.println("Level: " + enemyOne.getLevel());
System.out.println("Type: " + enemyOne.getType());
System.out.println("Health: " + enemyOne.getHealth());
}
private static void enemyTwoStats() {
System.out.println("Enemy name: " + enemyTwo.getName());
System.out.println("Level: " + enemyTwo.getLevel());
System.out.println("Type: " + enemyTwo.getType());
System.out.println("Health: " + enemyTwo.getHealth());
}

private static void enemyThreeStats() {
System.out.println("Enemy name: " + enemyThree.getName());
System.out.println("Level: " + enemyThree.getLevel());
System.out.println("Type: " + enemyThree.getType());
System.out.println("Health: " + enemyThree.getHealth());
}
private static void playerStats() {
System.out.println("Player Name: " + player.getName());
System.out.println("Level: " + player.getLevel());
System.out.println("XP: " + player.getXP());
}

private static void pickDirection() {
    System.out.println("Left or right?");
}

private static void goLeft() {
clear();
System.out.println("Going left.");
wait(1);
clear();
step();
clear();
}

private static void goRight() {
clear();
System.out.println("Going right.");
wait(1);
clear();
step();
clear();
}
private static void gameOver() {
System.out.println("You put up a good fight. Game over.");
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


}