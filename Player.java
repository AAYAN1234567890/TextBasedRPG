import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Player {
	private static Scanner scanner = new Scanner(System.in);
	
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
	
	public void pickFighter() {
	ArrayList<Fighters> fighterList = new ArrayList<>();
	fighterList.add(new Fighters("MK" ,1, "large" , 100.0 ));
	fighterList.add(new Fighters("KG" , 1, "agile", 100.0));
	System.out.println("Name  Lvl   Type  Health");
	for (Fighters f: fighterList) {
	System.out.println(f.toString() + "   " +  f.getLevel() + "   " + f.getType() + "   " + f.getHealth());
	}
	System.out.println("Pick a character using the index");
	int choice = scanner.nextInt();
	Fighters selectedFighter = fighterList.get(choice);
	System.out.println("You have chosen " + selectedFighter); //print out everything related to fighters (level, type, etc)
	System.out.println("Level: " + selectedFighter.getLevel());
	System.out.println("Type: " + selectedFighter.getType());
	System.out.println("Health: " + selectedFighter.getHealth());
	System.out.println();
	System.out.println();
	/*fighterList.remove(choice);
	System.out.println("Remaining options:"); 
	for (Fighters f: fighterList) {
		System.out.println(f.toString());
		} */
	
	}
	
	
	

	public static void main(String[] args) {
		//System.out.println("Pick a character");
	//	sid.pickFighter();
		
		System.out.println("Welcome! What is your name?");
		String name = scanner.nextLine();
		Player player = new Player(name);
		System.out.println("Hello " + name + "!");
		player.setLevel(1);
		player.setXP(0);
		player.pickFighter();
		
		

	}

}
