/**
 * 
 */
package bork;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Bradley Dufour
 * @version 2.0
 */
public class Dungeon {
	private String name;
	private Room entry;
	private ArrayList<Room> rooms;
	private String fileName;
	
	/** Initializes a new Dungeon so it has the specified entry Room, name, and an empty list of Rooms
	 * 
	 * @param entryRoom Room the player should start in
	 * @param dunName Name of the Dungeon
	 */
	public Dungeon(String borkFile)
	{
		fileName = borkFile;
		
		Scanner scan = null;
		try{
			scan = new Scanner(new java.io.File(borkFile));
		}catch(Exception e){
			System.err.println("Dungeon File not found");
			System.exit(0);
		}
		
		name = scan.nextLine();
		String info = scan.nextLine();
		if(!info.equals("Bork v2.0"))
		{
			System.err.println("The Dungeon file \""+ fileName +"\" is incompatible with the current Bork version.");
			System.exit(0);
		}
		
		//Removes the first "===" line
		scan.nextLine();
		
		//Gets and creates Rooms from .bork File
		try{
			entry = new Room(scan);
		}catch(NoRoomException e){
			System.err.println(".bork or .sav not formatted correctly");
		}
		rooms = new ArrayList<>();
		rooms.add(entry);
		boolean roomsLeft = true;
		while(roomsLeft)
		{
			try{
				rooms.add(new Room(scan));
			}catch(NoRoomException e){
				roomsLeft = false;
			}
		}
		
		//Gets and allows Rooms to create Exits from .bork File
		boolean exitsLeft = true;
		while(exitsLeft)
		{
			try{
				Exit nextExit = new Exit(scan, this);
				nextExit.getSrc().addExit(nextExit);
			}catch(NoExitException e){
				exitsLeft = false;
			}catch(NoRoomException e){
				System.err.println("File contains Exit starting/ending in a non-existent Room");
			}
		}
	}
	
	/** Accessor method for the entry Room to the Dungeon
	 * 
	 * @return Entry Room of the Dungeon
	 */
	public Room getEntry()
	{
		return entry;
	}
	
	/** Accessor method for the name of the Dungeon
	 * 
	 * @return Name of the Dungeon
	 */
	public String getName()
	{
		return name;
	}
	
	/** Adds a Room to the Dungeon's list of Rooms
	 * 
	 * @param room Room object to add to the Dungeon
	 */
	public void add(Room room)
	{
		rooms.add(room);
	}
	
	/** Finds a Room with a specific title within the Dungeon
	 * 
	 * @param roomTitle Title of the Room to search for
	 * @return Room object with the same title as roomTitle, null if no Room in the Dungeon has such a title
	 */
	public Room getRoom(String roomTitle) throws NoRoomException
	{
		for(Room room:rooms)
		{
			if(room.getTitle().equals(roomTitle))
			{
				return room;
			}
		}
		throw (new NoRoomException());
	}
	
	/** Saves Dungeon's current state to a .sav File
	 * 
	 * @param write BufferedWriter for writing to .sav File
	 */
	void storeState(BufferedWriter write) throws IOException
	{
		write.write("Dungeon file: "+ fileName +"\n");
		write.write("Room states:\n");
		for(Room room:rooms)
		{
			room.storeState(write);
		}
		write.write("===\n");
	}
	
	/** Reads a Dungeon's state from a .sav File
	 * 
	 * @param scan Scanner for reading from .sav File
	 */
	void restoreState(Scanner scan)
	{
		//Removes "Room states:" line
		scan.nextLine();
		boolean roomsLeft = true;
		while(roomsLeft)
		{
			String nextRoomName = scan.nextLine();
			nextRoomName = nextRoomName.substring(0, nextRoomName.length() - 1);
			try{
				getRoom(nextRoomName.substring(0, nextRoomName.length())).restoreState(scan);
			}catch(NoRoomException e){
				roomsLeft = false;
			}
		}
	}
}
