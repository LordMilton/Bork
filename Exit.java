/**
 * 
 */
package bork;

import java.util.Scanner;
/**
 * @author Bradley Dufour
 * @version 2.0
 */
public class Exit {
	String dir;
	Room src;
	Room dest;
	
	/** Initializes a new Exit with the direction associated with it, source Room, and destination Room specified by the .bork or .sav file
	 * 
	 * @param scan Scanner accessing .bork or .sav file to read Exit information from
	 * @param dun Dungeon containing the Room which will contain this Exit
	 */
	public Exit(Scanner scan, Dungeon dun) throws NoRoomException, NoExitException
	{
		//Throws out the "Exits:" line and any "---" lines
		scan.nextLine();
		String srcTitle = scan.nextLine();
		if(srcTitle.equals("==="))
		{
			throw new NoExitException();
		}
		src = dun.getRoom(srcTitle);
		dir = scan.nextLine();
		dest = dun.getRoom(scan.nextLine());
	}
	
	/** Provides a description of the Exit including the direction associated with it, and the destination Room
	 * 
	 * @return Description including the direction the Exit leads, and the Room the Exit leads to
	 */
	String describe()
	{
		return ("You can go "+ dir +" to "+ dest.getTitle() +".");
	}
	
	/** Accessor method for the Exit's associated direction
	 * 
	 * @return Direction the Exit leads
	 */
	public String getDir()
	{
		return dir;
	}
	
	/** Accessor method for the source Room of the Exit
	 * 
	 * @return Room the Exit is within
	 */
	public Room getSrc()
	{
		return src;
	}
	
	/** Accessor method for the destination Room of the Exit
	 * 
	 * @return Room the exit leads to
	 */
	public Room getDest()
	{
		return dest;
	}
}
