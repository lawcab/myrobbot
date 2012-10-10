package ca.mytester.robbot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class myRobotTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WebDriver webDriver = new FirefoxDriver();
		webDriver.get("http://www.google.ca");

	}

}
