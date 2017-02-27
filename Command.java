/**
 * 
 */
package bork;

/**
 * @author Bradley Dufour
 * @version 1.0
 */
class Command {
	private String command;
	
	/** Initializes a Command object with the specified direction
	 * 
	 * @param command Direction the command says to move the player in (Viable command list will be lengthened in future)
	 */
	Command(String command)
	{
		this.command = command;
	}
	
	/**
	 * 
	 * @return Information that should be printed after command executes ("You _pick up_ _item_")
	 */
	String execute()
	{
		GameState state = GameState.instance();
		
		//If the command was not valid
		if(!(command.equalsIgnoreCase("U") || command.equalsIgnoreCase("D") || command.equalsIgnoreCase("N") || command.equalsIgnoreCase("E") || command.equalsIgnoreCase("W") || command.equalsIgnoreCase("S") || command.equalsIgnoreCase("save")))
		{
			return ("I don't know what you mean by \""+ command +"\".");
		}
		
		//If the command was to "save"
		if(command.equalsIgnoreCase("save"))
		{
			state.store("ASuburbanHouse.sav");
			return ("The game has been saved.");
		}
		
		//If the command was a direction
		Room startRoom = state.getAdventurersCurrentRoom();
		state.setAdventurersCurrentRoom(state.getAdventurersCurrentRoom().leaveBy(command));
		
		//If the command was a direction in which there was no exit
		if(startRoom == state.getAdventurersCurrentRoom())
		{
			return ("You cannot move "+ command +".");
		}
		
		//If the command was a perfectly valid direction
		return "You move "+ command +".";
	}
}
