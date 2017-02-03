/**
 * 
 */
package bork;

import java.util.ArrayList;

/**
 * @author Bradley Dufour
 * 
 */
public class Room {
	private String title;
	private String desc;
	private ArrayList<Exit> exits;
	private boolean beenHere;
	
	/**
	 * 
	 * @param title Name of the Room
	 */
	public Room(String title)
	{
		this.title = title;
		exits = new ArrayList<Exit>(6);
		desc = "";
		beenHere = false;
	}
	
	/**
	 * 
	 * @return Room's name
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * 
	 * @param desc Description of the Room (not exits or title)
	 */
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	
	/**
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
			description += desc;
		}
		for(Exit exit:exits)
		{
			description += (" "+ exit.describe());
		}
		
		beenHere = true;
		return description;
	}
	
	/**
	 * 
	 * @param dir Direction to look for an Exit to leave by
	 * @return Room user ends in (current Room if dir has no corresponding Exit)
	 */
	Room leaveBy(String dir)
	{
		for(Exit exit:exits)
		{
			if(exit.getDir().equals(dir))
			{
				return exit.getDest();
			}
		}
		return this;
	}
	
	/**
	 * 
	 * @param exit Exit to add to Room's list of Exits
	 */
	public void addExit(Exit exit)
	{
		exits.add(exit);
	}
}
