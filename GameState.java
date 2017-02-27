/**
 * 
 */
package bork;

import java.io.*;
import java.util.Scanner;
/**
 * @author Bradley Dufour
 * @version 2.0
 */
class GameState {
	private static GameState theInstance;
	private Room adventurersCurrentRoom;
	private Dungeon dungeon;
	
	/** Initializes a GameState object
	 * 
	 */
	private GameState()
	{
		
	}
	
	/** Accessor method for the static GameState instance
	 * 
	 * @return The static GameState instance
	 */
	public static GameState instance()
	{
		if(theInstance == null)
		{
			theInstance = new GameState();
		}
		return theInstance;
	}
	
	/** Sets the dungeon to be the specified Dungeon object, and sets the player's current Room as the specified dungeon's entry Room
	 * 
	 * @param dungeon The Dungeon that will be played
	 */
	public void initialize(Dungeon dungeon)
	{
		this.dungeon = dungeon;
		adventurersCurrentRoom = dungeon.getEntry();
	}
	
	/** Accessor method for the Room the player is currently in
	 * 
	 * @return Room that the player is currently in
	 */
	public Room getAdventurersCurrentRoom()
	{
		return adventurersCurrentRoom;
	}
	
	/** Modifier method changing the Room the player is currently in
	 * 
	 * @param currRoom The new Room the Player is in
	 */
	public void setAdventurersCurrentRoom(Room currRoom)
	{
		adventurersCurrentRoom = currRoom;
	}
	
	/** Accessor method for the Dungeon that is currently being played
	 * 
	 * @return The Dungeon object that is currently being used
	 */
	public Dungeon getDungeon()
	{
		return dungeon;
	}
	
	/** Stores the current state of the current GameState to a .sav file
	 * 
	 * @param saveName Name of the file to save to
	 */
	void store(String saveName)
	{
		BufferedWriter write = null;
		try{
			write = new BufferedWriter(new FileWriter(new File(saveName)));
			write.write("Bork v2.0\nsave data\n");
			dungeon.storeState(write);
			write.write("Current Room: "+ adventurersCurrentRoom.getTitle());
		}catch(IOException e){
			System.err.println("Issue with Writing to save File");
		}finally{
			if(write != null)
			{
				try{
					write.flush();
					write.close();
				}catch(IOException e){
					System.err.println("Could not close Writer");
				}
			}
		}
	}
	
	/** Reads the state of a GameState from a .sav file
	 * 
	 * @param fileName Name of the file to read stored state from
	 */
	void restore(String fileName)
	{
		Scanner scan = null;
		try{
			scan = new Scanner(new File(fileName));
			String version = scan.nextLine();
			if(!version.equals("Bork v2.0"))
			{
				System.err.println("This File is not compatable with the current version of Bork");
			}
			
			//Removes "save data" line
			scan.nextLine();
			
			String filePath = scan.nextLine().split(" ")[2];
			this.initialize(new Dungeon(filePath));
			dungeon.restoreState(scan);
			String nextLine = scan.nextLine();
			String room = nextLine.split(":")[1].substring(1);
			adventurersCurrentRoom = dungeon.getRoom(room);
			
		}catch(FileNotFoundException e){
			System.err.println("Could not access .sav or .bork File");
			e.getStackTrace();
		}catch(NoRoomException e){
			System.err.println("Current Room non-existent");
		}finally{
			if(scan != null)
			{
				scan.close();
			}
		}
	}
	
}
