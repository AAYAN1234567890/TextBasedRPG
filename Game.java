import java.util.Scanner;
import java.util.ArrayList;
public class Game {
private static Scanner scanner = new Scanner(System.in);
public static void main(String[] args) {
Game game = new Game();
game.menu();
game.stageOne();
}
private static void menu() {
System.out.println("Welcome to our game. Enter name");
String name = scanner.nextLine();
Player player = new Player(name);
System.out.println("Hello " + name + "!");
player.pickFighter();
wait(2);
System.out.println("Lets begin the game."); //print out player stats before the game starts
}
private static void stageOne() {
step();
}
private static void wait(int x) { //brought this over from my old CS Project (Text-Based RPG)
try {
Thread.sleep(x * 1000);
} catch (InterruptedException e) {
e.printStackTrace();
}
}
private static void step() {
for(int i = 0; i < 5; i ++ ) {
System.out.println("Step.");
wait(1);
}
}
}
