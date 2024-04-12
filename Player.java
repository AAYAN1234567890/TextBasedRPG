/*

* Title: Player Class

* Author: Kundan Guntur

*/

import java.util.ArrayList;

import java.util.Scanner;

public class Player {

private static Scanner scanner = new Scanner(System.in);


private String name;

private int level;

private int XP;

private int lives;


// CONSTRUCTOR 


Player(String name, int level, int XP, int lives) {

this.name = name;

this.level = level;

this.XP = XP;

this.lives = lives;

}


//INPUTS 



/* method prompts user to select a fighter from the options, printed in an arrayList, chosen fighter

is stored in selectedFighter variable and is returned, for access and use in game class


@return selectedFighter: Fighters

*/


public Fighters pickFighter() {

ArrayList<Fighters> fighterList = new ArrayList<>();

fighterList.add(new Fighters("Sid" ,1, "lightweight" , 100.0 ));

fighterList.add(new Fighters("Param" , 1, "heavyweight", 100.0));

System.out.println("Name Lvl Type Health");

for (Fighters f: fighterList) {

System.out.println(f.getName() + " " +  f.getLevel() + " " + f.getType() + " " + f.getHealth());

}

System.out.println("Pick a fighter! Sid (1) or Param (2)");

int choice;

while (true) {

/* brought this over from our CS 20 Text-Based RPG, 

used ChatGPT to verify and modify syntax as the program continued to 

crash when user entered string instead of int - it added a parseInt to convert a string input to int

*/


String input = scanner.nextLine();

try {

choice = Integer.parseInt(input); //convert string input to int, assign to int choice variables

if (choice < 1 || choice > 2) {

System.out.println("Invalid choice. Please enter 1 or 2.");

continue;

}

break;

} catch (NumberFormatException e) { //look for strings, prompt user to try again if they do

System.out.println("Invalid input. Please enter a number.");

}

}

Fighters selectedFighter = fighterList.get(choice - 1);

System.out.println("You have chosen " + selectedFighter.getName());

System.out.println("Level: " + selectedFighter.getLevel());

System.out.println("Type: " + selectedFighter.getType());

System.out.println("Health: " + selectedFighter.getHealth());

return selectedFighter;

/* Debugging: initially, the selectedFighter variable was inaccessible beyond the class as nothing was being returned - 

once the return was added, the choice could be called upon in the game class, which is then used 

for the fights (implementation of fighter)

*/



}


// PROCESSING 


//Method add lives, which is called upon by mystery boxes (if they contain an "add lives" perk, method is called upon to add lives)

//  @param lives = int

public void addLives(int lives) {

this.lives += lives;

}


//method removes lives, which is called upon if player loses a fight

//  @param lives = int

public void removeLives(int lives) {

this.lives -= lives;

}



/*method adds XP to player, varied based on int entered in paramater when method is called upon in game - 

checkLevelUp() is called upon after to see if user qualifies for level up

@param xpAmount: int

*/

public void addXP(int xpAmount) {

XP += xpAmount;

System.out.println(name + " gained " + xpAmount + " XP. " + name + " now has " + XP + " XP in total.");

checkLevelUp();

}


/* based on calculated xp amount required to move up a level, method checks if user qualifies for a level up - 

* if yes, levelUp() is called upon

*/

private void checkLevelUp() {

int xpToNextLevel = calculateXPToNextLevel();

if (XP >= xpToNextLevel) {

levelUp();

}

}


/* method calculates required xp to level up, which increases as player levels up 

@return calculateXPToNextLevel: int

*/


private int calculateXPToNextLevel() {

// Calculate XP required to reach the next level based on formula

return (level * 100);

}


//adds level and conveys that to user, sets player XP to 0 once level up occurs

private void levelUp() {

level++;

System.out.println(name + " leveled up to level " + level + "!");

// Reset XP to 0 after leveling up

XP = 0;

}




//GETTERS 


//allows player name to be accessed and displayed as needed in game 

public String getName() {

return name;

}

//allows player level to be accessed and displayed as needed in game 

public int getLevel() {

return level;

}

//allows player XP to be accessed and displayed as needed in game 

public int getXP() {

return XP;

}

//allows player lives to be accessed and displayed as needed in game 

public int getLives() {

return lives;

}

public static void main(String[] args) {

}

}

/* What does this class do? It sets up the player, which represents the user interacting with the game.

It sets up the necessary attributes in the constructor (name, level, xp, lives (including extra lives)).

As the user does not need to manipulate attributes, setters are not necessary. However, the addLives() and removeLives()

act as setters in the sense that they can manipulate the "lives" attribute. Getters are used heavily in the class so that 

player attributes can be displayed in the game as needed (displaying stats, showing stats as they update).

The addLevel() and addXP() work together, as XP is added when user opens mystery boxes or wins fights. At the end of the

addXP() method, the addLevel() is called to check if user has met the XP requirement to level up (which increases with 

higher levels). The pickFighter() allows the user to pick a fighter from the available arrayList of fighters. Based on user

input, the fighter is assigned to selectedFighter, which is used in the game.

* Encapsulation: Hiding unneeded code (for user) in separate classes, in order to promote the overall integrity of program 

(user cannot directly access or add extra player attributes, unless it's through the intentionally included (setters) add/remove lives or addXP/level methods

- even then, the game does it, not the user)


* Advanced Algorithmic Structures: a try/catch block (accompanied by a while loop) is used while taking user input through a scanner, 

which runs until a user enters a valid input - this is used so that code doesn't break if they enter an invalid input (different data type than what 

scanner is set up for)

*/