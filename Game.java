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


// INPUTS

/*Starting menu, prompts user to enter name, creating player class using name, rest of attributes are pre-determined (not by user)
 * pickFighter() is called upon, and selectedFighter variable is accessed (for use in fights and implementations of fighter) and then stageOne() to begin game
 */
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
selectedFighter = player.pickFighter(); //accesses returned selectFighter variable from player class
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

/* using random method, number between 1-20 determined, and user has to guess the number, and are prompted to do so until they answer correctly -
 * do while loop used to ask user repeatedly, and hints are given (lower or higher) to help user -
 * once they guess correctly, they can move on to the second fight
 */
private static void riddle() {
    System.out.println("I see that you'd like to fight.");
    System.out.println("However, unauthorized individuals shall not pass.");
    System.out.println("But, if you tell me the magic number between 1-20, I will let you pass.");
    System.out.println("Enter a number between 1 and 20:");

    Random random = new Random();
    int randomNumber = random.nextInt(20) + 1; // 1-20
    int guess;

    do {
        String input = scanner.nextLine().trim();

        try {
            guess = Integer.parseInt(input);

            if (guess < randomNumber) {
                System.out.println("Higher!");
            } else if (guess > randomNumber) {
                System.out.println("Lower!");
            } else {
                System.out.println("Perfect!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer between 1 and 20:");
            guess = -1; // Set guess to an invalid value to continue the loop
        }
    } while (guess != randomNumber);

    clear();
    System.out.println("Now you can proceed!");
}

// PROCESSING


//running level one, where they can go left (open mystery box and go straight to level 2) or right (fight a hard boss)

private static void stageOne() {
    step();
    clear();
    pickDirection();

    String choice = getUserInputUntilValid("left", "right"); // Wasn't familiar with how to handle exceptions with string scanners, so used ChatGPT

    if (choice.equalsIgnoreCase("left")) {
        goLeft();
        System.out.println("Woah! You see a mystery box!");
        System.out.println("Enter open.");

        String openBox = getUserInputUntilValid("open");

        if (openBox.equalsIgnoreCase("open")) {
            clear();
            openMysteryBox();
            System.out.println("You have made it to the next level!");
            wait(2);
            clear();
            stageTwo();
        }
    } else if (choice.equalsIgnoreCase("right")) {
        goRight();
        System.out.println("You have encountered an enemy! Fight or flight?");

        String engage = getUserInputUntilValid("fight", "flight");

        if (engage.equalsIgnoreCase("fight")) {
            clear();
            fightOne();
        } else if (engage.equalsIgnoreCase("flight")) {
            clear();
            System.out.println("You have chosen to flee.");
            player.removeLives(1);

            if (player.getLives() == 0) {
                System.out.println("However, that has a cost.");
                System.out.println("As a violation of the fighter code, you are no longer allowed to fight.");
                System.out.println("Game over.");
            }
        }
    }
}

// Level 2: player can go left (directly go to fight two) or right (answer riddle, then go to fight two)
private static void stageTwo() {
    playerStats();
    wait(3);
    clear();
    step();
    clear();
    pickDirection();
    String choice = getUserInputUntilValid("left", "right");

    if (choice.equalsIgnoreCase("left")) {
        goLeft();
        System.out.println("You have encountered an enemy!");
        fightTwo(true);
    } else if (choice.equalsIgnoreCase("right")) {
        goRight();
        riddle();
        fightTwo(true);
    }
}

//Level 3: player can go left (open mystery box and then last fight) or right (riddle and then last fight)

private static void stageThree() {
    playerStats();
    wait(3);
    clear();
    step();
    clear();
    pickDirection();

    String choice = getUserInputUntilValid("left", "right");

    if (choice.equalsIgnoreCase("left")) {
        goLeft();
        System.out.println("Woah! You see a mystery box!");
        System.out.println("Enter open.");

        String openBox = getUserInputUntilValid("open");

        if (openBox.equalsIgnoreCase("open")) {
            clear();
            openMysteryBox();
            System.out.println("You have made it to the last fight!");
            wait(2);
            clear();
            fightThree(true);
        }
    } else if (choice.equalsIgnoreCase("right")) {
        goRight();
        riddle();
        fightThree(true);
    }
}

//open mystery box (called upon) when found, adds perks from box and displays updated stats
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


/* Fight 1: hard boss, 3 attacks to choose from, if player chooses to dodge,
 * they can either be successful (defeat boss and move to stage 2) or fail (lose all health, and game is over)
 * two while loops used (one for running until player has chances remaining, and then one until one of the fighters' health
 * go to 0
 * if player wins, move to stage 2, if they lose and have no lives, game over
 */
private static void fightOne() {
    System.out.println("Let's begin the fight!");
    wait(2);
    clear();
    enemyOneStats();
    wait(3);
    clear();
    enemyAlive = true;

    while (player.getLives() > 0 && enemyAlive) { //runs until player has chances left
        enemyOne.setHealth(75.0);
        selectedFighter.setHealth(100.0);
        System.out.println("Lives: " + player.getLives());

        do {
        clear();
            attackSelect();

            int attackChoice;
            do {
                String input = scanner.nextLine().trim();

                try { //CS 20 implementation of int exception handling didn't work, so asked ChatGPT to alter code to have it
                    attackChoice = Integer.parseInt(input);
                    if (attackChoice < 1 || attackChoice > 3) {
                        System.out.println("Invalid input. Please enter a number between 1 and 3:");
                        attackChoice = -1; // Set attackChoice to an invalid value to continue the loop
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number between 1 and 3:");
                    attackChoice = -1; // Set attackChoice to an invalid value to continue the loop
                }
            } while (attackChoice < 1 || attackChoice > 3);

            switch (attackChoice) {
                case 1:
                    clear();
                    selectedFighter.attack(enemyOne, selectedFighter, "punch", 10.0);
                    enemyOne.attack(selectedFighter, enemyOne, "uppercut", 40.0);
                    wait(3);
                    break;
                case 2:
                    clear();
                    selectedFighter.attack(enemyOne, selectedFighter, "kick", 20.0);
                    enemyOne.attack(selectedFighter, enemyOne, "kick", 40.0);
                    wait(3);
                    break;
                case 3:
                    clear();
                    selectedFighter.dodge(selectedFighter, enemyOne, enemyOne.getHealth());
                    wait(3);
                    break;
            }

        } while (enemyOne.getHealth() > 0 && selectedFighter.getHealth() > 0); //while one of the fighters are alive

        if (selectedFighter.getHealth() <= 0) {
            enemyAlive = true;
            player.removeLives(1);
        } else if (enemyOne.getHealth() <= 0) {
            System.out.println("You won the fight!");
            wait(2);
            clear();
            player.addXP(150);
            selectedFighter.addXP(150);
            enemyAlive = false;
        }
    }

    if (enemyAlive) {
        clear();
        gameOver();
    } else {
        clear();
        System.out.println("Moving on to the next level!");
        wait(2);
        clear();
        stageTwo();
    }
}

/* Fight 2: easier boss, 3 attacks to choose from, if player chooses to dodge,
 * they can either be successful (simply dodge attack) or fail (take 25 damage)
 * two while loops used (one for running until player has chances remaining, and then one until one of the fighters' health
 * go to 0
 * if both fighters health go to 0, coinflip() called to determine winner
 * if player wins, move to stage 3, if they lose and have no lives, game over
 */
private static void fightTwo(boolean extraDamage) {
    System.out.println("Let's begin the fight!");
    wait(2);
    clear();
    enemyTwoStats();
    wait(3);
    clear();
    enemyAlive = true;

    while (player.getLives() > 0 && enemyAlive) { //ChatGPT: corrected my mistake (I put or instead of and)
        enemyTwo.setHealth(100.0);
        selectedFighter.setHealth(100.0);
        System.out.println("Lives: " + player.getLives());
       
        do {
        clear();
            attackSelect();

            int attackChoice;
            do {
                String input = scanner.nextLine().trim();

                try {
                    attackChoice = Integer.parseInt(input);
                    if (attackChoice < 1 || attackChoice > 3) {
                        System.out.println("Invalid input. Please enter a number between 1 and 3:");
                        attackChoice = -1; // Set attackChoice to an invalid value to continue the loop
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number between 1 and 3:");
                    attackChoice = -1; // Set attackChoice to an invalid value to continue the loop
                }
            } while (attackChoice < 1 || attackChoice > 3);

            switch (attackChoice) {
                case 1:
                    clear();
                    if (extraDamage)
                        selectedFighter.attack(enemyTwo, selectedFighter, "punch", 10.0 + selectedFighter.getDamageBonus());
                    else
                        selectedFighter.attack(enemyTwo, selectedFighter, "punch", 10.0);
                    enemyTwo.attack(selectedFighter, enemyTwo, "uppercut", 20.0);
                    wait(3);
                    break;
                case 2:
                    clear();
                    if (extraDamage)
                        selectedFighter.attack(enemyTwo, selectedFighter, "kick", 20.0 + selectedFighter.getDamageBonus());
                    else
                        selectedFighter.attack(enemyTwo, selectedFighter, "kick", 20.0);
                    enemyTwo.attack(selectedFighter, enemyTwo, "punch", 10.0);
                    wait(3);
                    break;
                case 3:
                    clear();
                    if (extraDamage) {
                        if (isDodgeSuccessful()) {
                            System.out.println("You dodged the attack!");
                            wait(3);
                        } else {
                            System.out.println("You failed to dodge the attack!");
                            enemyTwo.attack(selectedFighter, enemyTwo, "uppercut", 25.0);
                            wait(3);
                            clear();
                        }
                    } else {
                        System.out.println("You dodged the attack!");
                    }
                    break;
            }

        } while (enemyTwo.getHealth() > 0 && selectedFighter.getHealth() > 0);

        if (selectedFighter.getHealth() <= 0 && enemyTwo.getHealth() <= 0) {
            coinFlip();
        } else if (selectedFighter.getHealth() <= 0) {
            enemyAlive = true;
            player.removeLives(1);
        } else if (enemyTwo.getHealth() <= 0) {
            System.out.println("You won the fight!");
            wait(2);
            clear();
            player.addXP(200);
            selectedFighter.addXP(200);
            enemyAlive = false;
        }
    }

    if (enemyAlive) { //ChatGPT: when I asked chatgpt to check method for errors, it mentioned that I put = instead of == for boolean, and suggested this simplification
        gameOver();
    } else {
        System.out.println("Moving on to the next level!");
        wait(2);
        clear();
        stageThree();
    }
}

/* Fight 3: similar difficulty boss as fightTwo, 3 attacks to choose from, if player chooses to dodge,
 * they can either be successful (simply dodge attack) or fail (take 25 damage)
 * two while loops used (one for running until player has chances remaining, and then one until one of the fighters' health
 * go to 0
 * if both fighters health go to 0, coinflip() called to determine winner
 * if player wins, they win game and final stats are displayed, if they lose and have no lives, game over
 */
private static void fightThree(boolean extraDamage) {
    System.out.println("Let's begin!");
    wait(2);
    clear();
    enemyThreeStats();
    wait(3);
    clear();
    enemyAlive = true;

    while (player.getLives() > 0 && enemyAlive) {
        enemyThree.setHealth(100.0);
        selectedFighter.setHealth(100.0);
        System.out.println("Lives: " + player.getLives());

        do {
        clear();
            attackSelect();
            int attackChoice;
            do {
                String input = scanner.nextLine().trim(); //trim() removes extra spaces in string - w3schools

                try {
                    attackChoice = Integer.parseInt(input);
                    if (attackChoice < 1 || attackChoice > 3) {
                        System.out.println("Invalid input. Please enter a number between 1 and 3:");
                        attackChoice = -1; // Set attackChoice to an invalid value to continue the loop
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number between 1 and 3:");
                    attackChoice = -1; // Set attackChoice to an invalid value to continue the loop
                }
            } while (attackChoice < 1 || attackChoice > 3);

            switch (attackChoice) {
                case 1:
                    clear();
                    if (extraDamage)
                        selectedFighter.attack(enemyThree, selectedFighter, "punch", 10.0 + selectedFighter.getDamageBonus());
                    else
                        selectedFighter.attack(enemyThree, selectedFighter, "punch", 10.0);
                    enemyThree.attack(selectedFighter, enemyThree, "uppercut", 20.0);
                    wait(3);
                    break;
                case 2:
                    clear();
                    if (extraDamage)
                        selectedFighter.attack(enemyThree, selectedFighter, "kick", 20.0 + selectedFighter.getDamageBonus());
                    else
                        selectedFighter.attack(enemyThree, selectedFighter, "kick", 20.0);
                    enemyThree.attack(selectedFighter, enemyThree, "punch", 10.0);
                    wait(3);
                    break;
                case 3:
                    clear();
                    if (extraDamage) {
                        if (isDodgeSuccessful()) {
                            System.out.println("You dodged the attack!");
                            wait(3);
                        } else {
                            System.out.println("You failed to dodge the attack!");
                            enemyThree.attack(selectedFighter, enemyThree, "uppercut", 25.0);
                            wait(3);
                            clear();
                        }
                    } else {
                        System.out.println("You dodged the attack!");
                    }
                    break;
            }

        } while (enemyThree.getHealth() > 0 && selectedFighter.getHealth() > 0);

        if (selectedFighter.getHealth() <= 0 && enemyThree.getHealth() <= 0) {
            coinFlip();
        } else if (selectedFighter.getHealth() <= 0) {
            enemyAlive = true;
            player.removeLives(1);
        } else if (enemyThree.getHealth() <= 0) {
            System.out.println("You won the fight!");
            wait(2);
            clear();
            player.addXP(250);
            selectedFighter.addXP(250);
            enemyAlive = false;
        }
    }

    if (enemyAlive) {
        gameOver();
    } else {
        System.out.println("Thank you for playing our game! We hope you've enjoyed it.");
        wait(2);
        clear();
        System.out.println("Final Stats: ");
        playerStats();
        wait(4);
    }
}

/* Method determines if fighter dodge is successful or not
 * random number between 1-100 generated, if 70 or below, it is returned
 * which represents a 70% of dodge being successful, 30% unsuccessful
 * @return randomNumber: int
 */
private static boolean isDodgeSuccessful() {
    // Generate a random number between 1 and 100
    int randomNum = (int) (Math.random() * 100) + 1;
    // 70% chance of successful dodge
    return randomNum <= 70;
}

/* when both fighters' health goes to 0, method used to determine winner
 * random method generates number 1 or 2, if number 1 - enemy fighter wins
 * if number 2, player fighter wins
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

/*thread.sleep used to create a time gap between text (reader has a chance to read what's displayed before new text is printed)
 * based on how much text is printed, parameter of desired seconds used to have that second gap before new text printed
 * @param sec: int
 */
private static void wait(int sec) { //brought this over from our old CS Project (Text-Based RPG)
try {
Thread.sleep(sec * 1000);
} catch (InterruptedException e) {
e.printStackTrace();
}
}

 /* method takes user input and sees if it matches with desired input options -
 * if input matches, validation occurs, as it is returned for use in scanner
 * if input doesn't match, user is prompted to try again and correct options displayed
 * @return input: String
 * @param validInputs = String
 */
private static String getUserInputUntilValid(String... validInputs) {
    while (true) {
        String input = scanner.nextLine().trim().toLowerCase();
        for (String validInput : validInputs) { // searches if user input matches list of possible inputs
            if (input.equals(validInput)) {
                return input; //returns input for use in scanner
            }
        }
        System.out.println("Invalid input. Please enter one of the valid options: " + String.join(", ", validInputs));
    }
}

// OUTPUTS

//prints a list of attacks
private static void attackSelect() {
System.out.println("Pick an attack!");
System.out.println("Punch (1) - 10 damage");
System.out.println("Kick (2) - 20 damage");
System.out.println("Dodge (3) - Avoid enemy's next attack");
}

//prints enemy stats in fight 1
private static void enemyOneStats() {
System.out.println("Enemy name: " + enemyOne.getName());
System.out.println("Level: " + enemyOne.getLevel());
System.out.println("Type: " + enemyOne.getType());
System.out.println("Health: " + enemyOne.getHealth());
}
//prints enemy stats in fight 2
private static void enemyTwoStats() {
System.out.println("Enemy name: " + enemyTwo.getName());
System.out.println("Level: " + enemyTwo.getLevel());
System.out.println("Type: " + enemyTwo.getType());
System.out.println("Health: " + enemyTwo.getHealth());
}

//prints enemy stats in fight 3
private static void enemyThreeStats() {
System.out.println("Enemy name: " + enemyThree.getName());
System.out.println("Level: " + enemyThree.getLevel());
System.out.println("Type: " + enemyThree.getType());
System.out.println("Health: " + enemyThree.getHealth());
}
//prints player stats, updated throughout game
private static void playerStats() {
System.out.println("Player Name: " + player.getName());
System.out.println("Level: " + player.getLevel());
System.out.println("XP: " + player.getXP());
System.out.println("Fighter Name: " + selectedFighter.getName());
System.out.println("Fighter Level: " + selectedFighter.getLevel());
}

//prints "left or right," more concise than print statement every time
private static void pickDirection() {
System.out.println("Left or right?");
}

//text "going left", and step()
private static void goLeft() {
clear();
System.out.println("Going left.");
wait(1);
clear();
step();
clear();
}

//text "going right", and step()
private static void goRight() {
clear();
System.out.println("Going right.");
wait(1);
clear();
step();
clear();
}

//game over message
private static void gameOver() {
System.out.println("You put up a good fight. Game over.");
}


//clear console, makes program more user-friendly
private static void clear() {
for (int i = 0; i < 50; i++) System.out.println();
}

//conveys movement, after player chooses to go left/right
private static void step() {
for(int i = 0; i < 6; i ++ ) {
System.out.println("Step.");
wait(1);
}
}

// NO GETTERS OR SETTERS IN CLASS

public static void main(String[] args) {
Game game = new Game();
game.menu();
}



}