import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Player {
	private static Scanner scanner = new Scanner(System.in);
	
	private String name;
	private int level;
	private int XP;
	private int extraLives;	
	
	Player(String name, int level, int XP, int extraLives) {
	this.setName(name);
	this.setLevel(level);
	this.setXP(XP);
	this.setextraLives(extraLives);
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
	
	public void setextraLives(int newLives) { 
		this.extraLives= newLives;
		}
	public String getName() {
	return name;
	}
	
	public int getLevel() {
		return level;
		}
	
	public int getXP() {
		return XP;
		}
	public int getExtraLives() {
	return extraLives;
	}
	public void addLevel() {
	int levelUp_Requirement = 1000;
	int requiredXP = levelUp_Requirement - XP;
	if (XP == levelUp_Requirement) {
	level++;
	} else {
	System.out.println("You have " + XP + ". You need " +  requiredXP + "XP to level up." );
	}
	}
	
	public void addXP(int xpAmount) {
	XP+= xpAmount;
	System.out.println("You now have " + XP);
	}
	//for the two methods above, make sure that mystery box values work properly with them
	
	
	public void pickFighter() {
	ArrayList<Fighters> fighterList = new ArrayList<>();
	fighterList.add(new Fighters("MK" ,1, "large" , 100.0 ));
	fighterList.add(new Fighters("KG" , 1, "agile", 100.0));
	System.out.println("Name  Lvl   Type  Health");
	for (Fighters f: fighterList) {
	System.out.println(f.getName() + "   " +  f.getLevel() + "   " + f.getType() + "   " + f.getHealth());
	}
	System.out.println("Pick a character using the numeric value: MK(0) or KG (1)");
	int choice = scanner.nextInt();
	Fighters selectedFighter = fighterList.get(choice);
	System.out.println("You have chosen " + selectedFighter.getName()); //print out everything related to fighters (level, type, etc)
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
	
	public void playerStats() {
	
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
