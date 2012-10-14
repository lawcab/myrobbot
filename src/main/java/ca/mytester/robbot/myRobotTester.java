package ca.mytester.robbot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ca.mytester.robbot.domain.Action;

public class myRobotTester {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		
		String inputFilename = args[0];
		
		BufferedReader myReader;
		try {
			myReader = new BufferedReader(new FileReader(inputFilename));
			String line;
			
			while ((line = myReader.readLine()) != null) {
				processOneLine(line);			
			}
			
			myReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File " + args[0] + " not found.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read the file "+ args[0]);
		}
		

	}

	private static void processOneLine(String line) {
		Action action = new Action(line);
		System.out.println(action.toString());
	}

}
