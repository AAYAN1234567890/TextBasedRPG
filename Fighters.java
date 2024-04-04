import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Fighters {
private String name;
private int level;
private String type;
private double health;
private boolean alive;


public Fighters(String name, int level, String type, double health) {
this.name = name;
this.level = level;
this.type = type;
this.health = health;
//this.setHealth(health);

}

public String getName() {
return this.name;
}

public int getLevel() {
return this.level;
}

public String getType() {
return this.type;
}

/*public void setHealth(double newHealth) { 
	this.health = newHealth;
	}
	*/

public void doDamage(double damageAmount) { 
enemy.health -= damageAmount; //Used ChatGPT to clarify how to subtract health (I thought we had to use setter, but ran into an error as object was inaccessible)
System.out.println("Opponent now has " + health);
}

public double getHealth () {
return this.health;
}


public void attack(String attackName, double damage) {
System.out.println("You did " + attackName + ". It did " + damage + ".");
doDamage(damage);
//enemy.setHealth(health - damage);
//implement means to actually deal damage to opponent - this will apply to all fighters
//once this is done, then the same principle can be carried to gaining xp and levels
}

public void dodge() {
	boolean dodge;
	Random random = new Random();
    int ifDodge = random.nextInt(5) + 1; // 1 to 3
    if(ifDodge < 3) {
    dodge = true;
    System.out.println("You have dodged the attack!");
    }
    else {
    dodge = false;
    System.out.println("The dodge failed - the enemy got their attack!");
    }
	}





	public static void main(String[] args) {
	/*Fighters aayan = new Fighters("name" ,1, "large" , 100.0 );
	Fighters kundan = new Fighters("name" , 1, "agile", 100.0);
	ArrayList<Fighters> fighterList = new ArrayList<>();
	Collections.addAll(fighterList, kundan, aayan);
	for(Fighters f: fighterList) {
		System.out.println(f.name);
	*/
		
	Fighters kundan = new Fighters("name" , 1 , "agile" , 100.0);
	kundan.attack("punch", 10);
	kundan.attack("punch", 10);
	
	
	
	Fighters enemy = new Fighters("enemy", 1, "agile" , 90.0);
	
	}

}

