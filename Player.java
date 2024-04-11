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

 
   
    Player(String name, int level, int XP, int lives) {
  /*  this.setName(name);
    this.setLevel(level);
    this.setXP(XP);
    this.setLives(lives);
    */
    this.name = name;
    this.level = level;
    this.XP = XP;
    this.lives = lives;
    }
   
  /*  public void setName(String newName) {
    this.name = newName;
    }
   
    public void setLevel(int newLevel) {
        this.level = newLevel;
        }
    public void setXP(int newXP) {
        this.XP= newXP;
        }
   
    public void setLives(int lives) {
        this.lives = lives;
        }
       
       */
   
    public String getName() {
    return name;
    }
   
    public int getLevel() {
        return level;
        }
   
    public int getXP() {
        return XP;
        }


        public int getLives() {
            return lives;
        }
 
   
        public void addLives(int lives) {
            this.lives += lives;
        }
       
        public void removeLives(int lives) {
            this.lives -= lives;
        }
       
 
/*
        public void gainXP(int xp) {
            System.out.println(name + " gained " + xp + " XP.");
             int XP = xp;
            // Check if level up
            checkLevelUp();
        }
       
        private void checkLevelUp() {
            int xpToNextLevel = calculateXPToNextLevel();
            if (XP >= xpToNextLevel) {
                levelUp();
            }
        }
       
        private void levelUp() {
            level++;
            System.out.println(name + " leveled up to level " + level + "!");
        }
       
        private int calculateXPToNextLevel() { //Ran into issues setting xp required to level up for each level, so used ChatGPT for syntax
            // Calculate XP required to reach the next level based on     formula
            return (level * 50) + 50; //the player needs 100 XP to reach level 2.
        }
    */
   
        public void addLevel(int xpBonus) {
            int levelUp_Requirement = 100;
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
           checkLevelUp();
        }

        private void checkLevelUp() {
            int xpToNextLevel = calculateXPToNextLevel();
            if (XP >= xpToNextLevel) {
                levelUp();
            }
        }

        private void levelUp() {
            level++;
            System.out.println(name + " leveled up to level " + level + "!");
            // Reset XP to 0 after leveling up
            XP = 0;
        }
    
        private int calculateXPToNextLevel() {
            // Calculate XP required to reach the next level based on formula
            return (level * 100); // Assuming 100 XP required for each level
        }
   
   
        public Fighters pickFighter() {
            ArrayList<Fighters> fighterList = new ArrayList<>();
            fighterList.add(new Fighters("Sid" ,1, "lightweight" , 100.0 ));
            fighterList.add(new Fighters("Param" , 1, "heavyweight", 100.0));
            System.out.println("Name  Lvl   Type  Health");
            for (Fighters f: fighterList) {
            System.out.println(f.getName() + "   " +  f.getLevel() + "   " + f.getType() + "   " + f.getHealth());
            }
            System.out.println("Pick a fighter! Sid (1) or Param (2)");
            int choice;
        while (true) { // referred to my CS 20 Text-Based RPG for syntax
           try {
               choice = scanner.nextInt();
               if (choice != 1 && choice != 2) {
                System.out.println("That wasn't an option. Enter 1 or 2.");
                   continue;
               }
               break;
           } catch (NumberFormatException e) {
            System.out.println("That wasn't an option. Enter 1 or 2.");
           }
           //^ if I type a letter it breaks?
        }
            Fighters selectedFighter = fighterList.get(choice - 1);
            System.out.println("You have chosen " + selectedFighter.getName());
            System.out.println("Level: " + selectedFighter.getLevel());
            System.out.println("Type: " + selectedFighter.getType());
            System.out.println("Health: " + selectedFighter.getHealth());
            return selectedFighter;
            /*Debugging: initially, the selectedFighter variable was inaccessible beyond the class as nothing returned -
             once the return was added, the choice could be called upon in the game class, which is then used
             for the fights
             */
           
         
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
 (user cannot directly access or add extra player attributes, unless it's through the intentionally included add/remove lives or addXP/level methods
 - even then, the game does it, not the user)
 
 * Advanced Algorithmic Structure; a try/catch block (accompanied by a while loop) is used while taking user input through a scanner,
 which runs until a user enters a valid input - this is used so that code doesn't break if they enter an invalid input (different data type than what
 scanner is set up for)
 */
