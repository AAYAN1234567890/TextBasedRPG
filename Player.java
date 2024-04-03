import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Player {
private static Scanner scanner = new Scanner(System.in);
private static ArrayList<Fighters> fighterList = new ArrayList<>();
//add them to arrayList, so that user can pick which one they want to use
private String name;
private int level;
private int XP;
Player(String name, int level, int XP) {
this.setName(name);
this.setLevel(level);
this.setXP(XP);
}
Player(String name) {
this.setName(name);
}
public void setName(String newName) {
this.name = newName;
}
public void setLevel(int newLevel) {
this.level = newLevel;
}
public void setXP(int newXP) {
this.XP= newXP;
}
public String toString() {
return name;
}
private void pickFighter() {
Fighters name1 = new Fighters("name" ,1, "large" , 100.0 );
Fighters name2 = new Fighters("name" , 1, "agile", 100.0);
Collections.addAll(fighterList, name1, name2);
for (Fighters f: fighterList) {
System.out.println(f.toString());
}
System.out.println("Pick a character using the index");
int choice = scanner.nextInt();
fighterList.remove(choice);
System.out.println("You have chosen " + choice); //change this to show the object removed from arrayList
}
public static void main(String[] args) {
//System.out.println("Pick a character");
// sid.pickFighter();
System.out.println("Welcome! What is your name?");
String name = scanner.nextLine();
Player player = new Player(name);
System.out.println("Hello " + name + "!");
player.setLevel(1);
player.setXP(0);
player.pickFighter();
}
}
