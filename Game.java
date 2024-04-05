import java.util.Scanner;
import java.util.ArrayList;

public class Game {
private static Scanner scanner = new Scanner(System.in);
private static Player player;

	public static void main(String[] args) {
		Game game = new Game();
		game.menu();
		game.stageOne();

	}
	
	
private static void menu() {
System.out.println("Welcome to our game. Enter name");
String name = scanner.nextLine();
player = new Player(name);
System.out.println("Hello " + player.getName() + "!");
player.setLevel(1);
player.setXP(0);
player.setextraLives(0);
player.pickFighter();
wait(2);
clear();
System.out.println("Lets begin the game.");
System.out.println("Initial Stats:");
System.out.println("Player Name: " + player.getName());
System.out.println("Level: " + player.getLevel());
System.out.println("XP: " + player.getXP());
wait(4);
clear();
}

private static void stageOne() {
step();
pickDirection();
String choice = scanner.nextLine();
if (choice.equalsIgnoreCase("left")) {
System.out.println("Going left.");
step();
System.out.println("Woah! You see a mystery box!.");
System.out.println("Enter open.");
String openBox = scanner.nextLine();
if (openBox.equalsIgnoreCase("open")) {
System.out.println("You have opened box!");
MysteryBox.main(null);
System.out.println(player.getXP());
// make sure the xp and levelling (& extraLives or anything in the mystery box) system gets updated from the mysterbox into the user

}
else {
System.out.println("Go ahead, open the box!");
	
}

} else {
System.out.println("Going right.");
step();
}


}

private static void wait(int sec) { //brought this over from my old CS Project (Text-Based RPG)
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
