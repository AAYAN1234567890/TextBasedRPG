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
        this.extraLives = newLives;
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
  
   
        public void addExtraLives(int extraLives) {
            this.extraLives += extraLives;
        }
        
  




    public void addLevel(int xpBonus) { 
        int levelUp_Requirement = 750;
        int requiredXP = levelUp_Requirement - (XP + xpBonus);
        if (XP + xpBonus >= levelUp_Requirement) {
            level++;
            System.out.println("Congratulations! You leveled up to level " + level + "!");
        } else {
            System.out.println("You have " + (XP + xpBonus) + " XP. You need " + requiredXP + " XP to level up.");
        }
    }
   
    public void addXP(int xpAmount) {
        XP += xpAmount;
        System.out.println("You gained " + xpAmount + " XP. You now have " + XP + " XP in total.");
       //ask aayan about paramater, remove if not needed
    }
    
   
   
   
    public Fighters pickFighter() {
    ArrayList<Fighters> fighterList = new ArrayList<>();
    fighterList.add(new Fighters("Sid" ,1, "lightweight" , 100.0 ));
    fighterList.add(new Fighters("Param" , 1, "heavyweight", 100.0));
    System.out.println("Name  Lvl   Type  Health");
    for (Fighters f: fighterList) {
    System.out.println(f.getName() + "   " +  f.getLevel() + "   " + f.getType() + "   " + f.getHealth());
    }
    System.out.println("Pick a character using the numeric value: Sid (1) or Param (2)");
    int choice = scanner.nextInt();
    Fighters selectedFighter = fighterList.get(choice - 1);
    System.out.println("You have chosen " + selectedFighter.getName());
    System.out.println("Level: " + selectedFighter.getLevel());
    System.out.println("Type: " + selectedFighter.getType());
    System.out.println("Health: " + selectedFighter.getHealth());
    return selectedFighter;
 
    }


    public static void main(String[] args) {    




    }




}
