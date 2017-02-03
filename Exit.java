/**
 * 
 */
package bork;

/**
 * @author Bradley Dufour
 * 
 */
public class Exit {
	String dir;
	Room src;
	Room dest;
	
	/**
	 * 
	 * @param dir Direction the Exit is accessed by
	 * @param src Room the Exit is in
	 * @param desc Room the Exit leads to
	 */
	public Exit(String dir, Room src, Room dest)
	{
		this.dir = dir;
		this.src = src;
		this.dest = dest;
	}
	
	/**
	 * 
	 * @return Description including the direction the Exit leads, and the Room the Exit leads to
	 */
	String describe()
	{
		return ("You can go "+ dir +" to "+ dest);
	}
	
	/**
	 * 
	 * @return Direction the Exit leads
	 */
	public String getDir()
	{
		return dir;
	}
	
	/**
	 * 
	 * @return Room the Exit is within
	 */
	public Room getSrc()
	{
		return src;
	}
	
	/**
	 * 
	 * @return Room the exit leads to
	 */
	public Room getDest()
	{
		return dest;
	}
}
