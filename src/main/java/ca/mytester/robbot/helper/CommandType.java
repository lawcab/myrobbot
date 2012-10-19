package ca.mytester.robbot.helper;

public enum CommandType {
	OPEN_BROWSER("Open Browser"),
	CLOSE_BROWSER("Close Browser"),
	GO_TO("Go To"),
	TYPE("Type"),
	CLICK("Click");
	
	
	private final String commandLine;
	private CommandType(String commandLine) {
		this.commandLine = commandLine;
	}
	
	
    public String getCommandLine() {
        // TODO Auto-generated method stub
        return commandLine;
    }
	
}
