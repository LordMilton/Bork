/**
 * 
 */
package bork;

/**
 * @author Bradley Dufour
 * @version 1.0
 */
class CommandFactory {
	private static CommandFactory theInstance;
	
	private CommandFactory()
	{
		
	}
	
	public static CommandFactory instance()
	{
		if(theInstance == null)
		{
			theInstance = new CommandFactory();
		}
		return theInstance;
	}
	
	Command parse(String commandString)
	{
		return (new Command(commandString));
	}
}
