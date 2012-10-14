package ca.mytester.robbot.domain;

public class Action {
	private String action;
	private String what;
	private String expression;
	
	public Action(String line) {
		super();
		String[] lineSplit = line.split("\t");
		
		this.action = lineSplit[0];
		this.what = lineSplit[1];
		if (lineSplit.length==3) {
			this.expression = lineSplit[2];
		}
		
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "Action [action=" + action + ", what=" + what + ", expression="
				+ expression + "]";
	}
	
	
}
