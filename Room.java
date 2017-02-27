/**
 * 
 */
package bork;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/**
 * @author Bradley Dufour
 * @version 2.0
 */
public class Room {
	private String title;
	private String desc;
	private ArrayList<Exit> exits;
	private boolean beenHere;
	
	/** Initializes a new Room with the specified title, an empty description, an empty list of Exits, and a false boolean as to whether or not the Player has entered before
	 * 
	 * @param title Name of the Room
	 */
	public Room(Scanner scan) throws NoRoomException
	{
		//Throws out the "Rooms:" line or the "---" line
		scan.nextLine();
		
		String roomName = scan.nextLine();
		if(roomName.equals("==="))
		{
			throw new NoRoomException();
		}
		title = roomName;
		exits = new ArrayList<Exit>(6);
		desc = scan.nextLine();
		beenHere = false;
	}
	
	/** Accessor method for the name of a Room
	 * 
	 * @return Room's name
	 */
	public String getTitle()
	{
		return title;
	}
	
	/** Modifier method for the description of a Room
	 * 
	 * @param desc Description of the Room (not exits or title)
	 */
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	
	/** Provides a description of the Room which is shortened if the Player has already been in the Room before
	 * 
	 * @return Entire description of the Room with exits, title, and 'desc' if the player has not been
	 * 		   in the Room before
	 */
	String describe()
	{
		String description = "";
		description += title +"\n";
		if(!beenHere)
		{
			description += desc +" ";
		}
		for(Exit exit:exits)
		{
			description += (exit.describe() +" ");
		}
		
		beenHere = true;
		return description;
	}
	
	/** Uses the Exit in a specific direction, moving a Player to the room at the end of that Exit
	 * 
	 * @param dir Direction to look for an Exit to leave by
	 * @return Room user ends in (current Room if dir has no corresponding Exit)
	 */
	Room leaveBy(String dir)
	{
		for(Exit exit:exits)
		{
			if(exit.getDir().equalsIgnoreCase(dir))
			{
				return exit.getDest();
			}
		}
		return this;
	}
	
	/** Adds an Exit to a Room's list of Exits
	 * 
	 * @param exit Exit to add to Room's list of Exits
	 */
	public void addExit(Exit exit)
	{
		exits.add(exit);
	}
	/** Writes the Room's current state to a .sav File
	 * 
	 * @param write BufferedWriter for writing to .sav File
	 */
	void storeState(BufferedWriter write) throws IOException
	{
		if(beenHere)
		{
			write.write(title +":\n");
			write.write("beenHere="+ beenHere +"\n");
			write.write("---\n");
		}
	}
	
	/** Reads a Room's state from a .sav File
	 * 
	 * @param scan Scanner for reading from .sav File
	 */
	void restoreState(Scanner scan)
	{
		scan.nextLine();
		beenHere = true;
		scan.nextLine();
	}
}
