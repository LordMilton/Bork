/**
 * 
 */
package bork;

import java.util.Scanner;
/**
 * @author Bradley Dufour
 * @version 2.0
 */
class Interpreter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//Dungeon newDungeon = buildSampleDungeon();
		System.out.println("What file is this Dungeon in? (Should be a .bork or .sav file)");
		String fileName = scan.next(); scan.nextLine();
		Dungeon newDungeon = new Dungeon(fileName);
		CommandFactory commander = CommandFactory.instance();
		GameState state = GameState.instance();
		state.initialize(newDungeon);
		
		System.out.println("Welcome to "+ newDungeon.getName() +".");
		System.out.println(state.getAdventurersCurrentRoom().describe());
		
		String input = promptUser(scan);
		while(!input.equals("q"))
		{
			Command command = commander.parse(input);
			System.out.println(command.execute());
			System.out.println(state.getAdventurersCurrentRoom().describe());
			input = promptUser(scan);
		}
	}
	
	private static String promptUser(Scanner commandLine)
	{
		System.out.print(">   ");
		String input = commandLine.nextLine();
		return input;
	}
	
/*	private static Dungeon buildSampleDungeon()
	{
		Room foyer = new Room("Foyer");
		foyer.setDesc("A rug lays on the floor and to the North is a locked front door.");
		Dungeon dungeon = new Dungeon(foyer, "A Suburban House");
		
		Room livingRoom = new Room("Living Room");
		livingRoom.setDesc("The floor is carpeted. Several comfortable looking chairs and couches surround you. There is a fish tank in the corner.");
		dungeon.add(livingRoom);
		
		Room familyRoom = new Room("Family Room");
		familyRoom.setDesc("The floor is carpeted. A couple of leather couches surround you. On one side is a computer on a desk.  On another is a large cabinet full of drawers.");
		dungeon.add(familyRoom);
		
		Room kitchen = new Room("Kitchen");
		kitchen.setDesc("The floor is vinyl. A large table stands in the center of the room. On the sides are counters with cabinets above them.");
		dungeon.add(kitchen);
		
		Room diningRoom = new Room("Dining Room");
		diningRoom.setDesc("The floor is hardwood. In the center of the room is a table with a beautiful centerpiece.");
		dungeon.add(diningRoom);
		
		Room laundryRoom = new Room("Laundry Room");
		laundryRoom.setDesc("The floor is linoleum. A washer and dryer take up one side with cabinets above them. On the other side is a wall covered in miscellaneous coats, hats, and bags.");
		dungeon.add(laundryRoom);
		
		foyer.addExit(new Exit("S", foyer, familyRoom));
		foyer.addExit(new Exit("E", foyer, livingRoom));
		foyer.addExit(new Exit("W", foyer, diningRoom));
		
		livingRoom.addExit(new Exit("W", livingRoom, foyer));
		
		familyRoom.addExit(new Exit("N", familyRoom, foyer));
		familyRoom.addExit(new Exit("W", familyRoom, kitchen));
		
		kitchen.addExit(new Exit("E", kitchen, familyRoom));
		kitchen.addExit(new Exit("W", kitchen, laundryRoom));
		kitchen.addExit(new Exit("N", kitchen, diningRoom));
		
		laundryRoom.addExit(new Exit("E", laundryRoom, kitchen));
		
		diningRoom.addExit(new Exit("E", diningRoom, foyer));
		diningRoom.addExit(new Exit("S", diningRoom, kitchen));
		
		return dungeon;
	}*/
}
