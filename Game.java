import java.util.Scanner;

public class Game {

    private static Scanner scanner = new Scanner(System.in);
    private static Player player;
    private static Fighters selectedFighter;
    private static Fighters enemyOne = new Fighters("Dhvanay", 50, "megaweight", 70.0);
    private static Fighters enemyTwo = new Fighters("Harsha", 3, "mediumweight", 100.0);

    public static void main(String[] args) {
        Game game = new Game();
        game.menu();
        game.stageOne();
    }

    private void menu() {
        System.out.println("Welcome to our game. Enter name");
        String name = scanner.nextLine();
        player = new Player(name, 1, 0); // Removed extraLives initialization
        System.out.println("Hello " + name.toUpperCase() + "!");
        selectedFighter = player.pickFighter();
        wait(2);
        clear();
        System.out.println("Let's begin the game.");
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
            clear();
            step();
            System.out.println("Woah! You see a mystery box!");
            System.out.println("Enter open.");
            String openBox = scanner.nextLine();
            if (openBox.equalsIgnoreCase("open")) {
                clear();
                openMysteryBox();
            } else {
                System.out.println("Go ahead, open the box!");
            }
        } else {
            clear();
            System.out.println("Going right.");
            wait(1);
            clear();
            step();
            System.out.println("You have encountered an enemy! Fight or flight?");
            String engage = scanner.nextLine();
            if (engage.equalsIgnoreCase("fight")) {
                clear();
                fightOne();
            } else if (engage.equalsIgnoreCase("flight")) {
                clear();
                System.out.println("You have chosen to flee.");
                if (player.getExtraLives() == 0) {
                    System.out.println("However, that has a cost.");
                    System.out.println("As a violation of the fighter code, you are no longer allowed to fight.");
                    System.out.println("Game over.");
                }
            }
        }
    }

    private static void stageTwo() {
        playerStats();
        wait(3);
        clear();
        step();
        pickDirection();
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("left")) {
            System.out.println("You have encountered an enemy! Time to fight!");
            fightTwo(true);
        } else if (choice.equalsIgnoreCase("right")) {
            riddle();
        }
    }

    private static void openMysteryBox() {
        MysteryBox mysteryBox = new MysteryBox();
        mysteryBox.openBox(player); // Pass the player object
        System.out.println("Updated Stats:");
        System.out.println("XP: " + player.getXP());
        System.out.println("Level: " + player.getLevel());
        System.out.println("Extra Lives: " + player.getExtraLives());
        wait(3);
        clear();
        System.out.println("Onto the next level!");
        clear();
        wait(1);
        stageTwo();
    }
private static void riddle() {

System.out.println("Unauthorized individuals shall not pass.");

System.out.println("If you are authorized to pass, tell the magic number between 1-10"); //answer is 7

int guess;

do {

guess = scanner.nextInt();

if (guess < 7) {

System.out.println("Higher!");

}

else if (guess > 7) {

System.out.println("Lower!");

}

else if (guess == 7) {

System.out.println("Perfect!");

}

}

while (!(guess == 7));

System.out.println("Moving on!");

fightTwo(true);

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

wait(2);

clear();

stageTwo();

}

}

private static void fightTwo(boolean extraDamage) {
    System.out.println("Let's begin!");
    wait(1);
    clear();
    enemyTwoStats();
    wait(3);
    clear();
    
    while (enemyTwo.getHealth() > 0 && selectedFighter.getHealth() > 0) {
        //enemyTwo.setHealth(100.0);
        attackSelect();
        int attackChoice = scanner.nextInt();

        if (attackChoice == 1) {
            clear();
            if (extraDamage)
                selectedFighter.attack(enemyTwo, selectedFighter, "punch", 15.0 + selectedFighter.getDamageBonus());
            else
                selectedFighter.attack(enemyTwo, selectedFighter, "punch", 15.0);
            enemyTwo.attack(selectedFighter, enemyTwo, "uppercut", 50.0);
            wait(3);
            clear();
        } else if (attackChoice == 2) {
            clear();
            if (extraDamage)
                selectedFighter.attack(enemyTwo, selectedFighter, "kick", 20.0 + selectedFighter.getDamageBonus());
            else
                selectedFighter.attack(enemyTwo, selectedFighter, "kick", 20.0);
            enemyTwo.attack(selectedFighter, enemyTwo, "uppercut", 50.0);
            wait(3);
            clear();
        } else if (attackChoice == 3) {
            clear();
            if (extraDamage) {
                if (isDodgeSuccessful()) {
                    System.out.println("You dodged the attack!");
                } else {
                    System.out.println("You failed to dodge the attack!");
                    enemyTwo.attack(selectedFighter, enemyTwo, "uppercut", 50.0);
                }
            } else {
                System.out.println("You dodged the attack!");
            }
        }
        
        if (selectedFighter.getHealth() == 0) {
            checkPlayerChances();
        }
    }

    if (enemyTwo.getHealth() <= 0) {
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

wait(3);

selectedFighter.setHealth(100.0);

player.setExtraLives(player.getExtraLives() - 1);

}

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

for(int i = 0; i < 3; i ++ ) {

System.out.println("Step.");

wait(1);

}

}

private static void pickDirection() {

System.out.println("Left or right?");

}

}