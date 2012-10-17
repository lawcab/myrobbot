package ca.mytester.robbot.helper;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PageHelper {
	private static final String EMPTY_FIELD_MARKER = "none";
	private static final long DEFAULT_WAIT_PERIOD = 10;

	private final WebDriver webDriver;

	/**
	 * Construct the Page object by passing in the Settings
	 * 
	 * @param settings
	 *            get the WebDriver from the Settings object.
	 */
	public PageHelper(final WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	/**
	 * This will make the WebDriver go to the specified url
	 * 
	 * @param url
	 *            The address you want to go to
	 */

	public void goToUrl(String url) {
		this.webDriver.get(url);
	}

	/**
	 * Returns you the WebDriver this page is using
	 * 
	 * @return WebDriver
	 */

	public WebDriver getWebDriver() {
		return webDriver;
	}

	/**
	 * Types the value of keys to the field specified by the By selector
	 * parameter.
	 * 
	 * @param keys
	 *            The String that you want to enter to the field
	 * 
	 * @param selector
	 *            How you will identify the field to enter the value.
	 */
	protected void typeField(String keys, By selector) {

		final WebElement inputField = getElementAndClear(selector);

		if (!keys.equals(EMPTY_FIELD_MARKER)) {
			inputField.sendKeys(keys);
			sleepSafely(250);
		}
	}
	
	/**
	 * Types the value of keys to the field specified by the By selector
	 * parameter. Use this if your field auto-refreshes the page onchange.
	 * 
	 * @param keys
	 *            The String that you want to enter to the field
	 * 
	 * @param selector
	 *            How you will identify the field to enter the value.
	 */
	protected void typeFieldWithRefresh(String keys, By selector) {

		WebElement inputField = getElementAndClear(selector);
		waitForElement(selector);
		inputField = getWebDriver().findElement(selector);

		if (!keys.equals(EMPTY_FIELD_MARKER)) {
			inputField.sendKeys(keys);
			sleepSafely(250);
		}
	}


	/**
	 * Gets the WebElement. Clears that element and return in.
	 * 
	 * @param selector How the field is identified
	 * @return The WebElement
	 */

	protected WebElement getElementAndClear(By selector) {
		WebElement element = null;

		try {
			element = getWebDriver().findElement(selector);
			element.clear();

		} catch (NoSuchElementException e) {
			throw new RuntimeException(e);
		}

		return element;
	}

	/**
	 * Gets the WebElement and return in.
	 * @param selector selector How the field is identified
	 * @return The WebElement
	 */
	protected WebElement getElement(By selector) {
		WebElement element = null;

		try {
			element = getWebDriver().findElement(selector);
		} catch (NoSuchElementException e) {
			throw new RuntimeException(e);
		}

		return element;
	}
	
	
	/**
	 * Gets the WebElement list and return .
	 * @param selector selector How the field is identified
	 * @return The list of WebElement
	 */
	protected List<WebElement> getElements(By selector) {
		//WebElement element = null;
		 List<WebElement> elementsFound;

		try {
			elementsFound = getWebDriver().findElements(selector);
		} catch (NoSuchElementException e) {
			throw new RuntimeException(e);
		}

		return elementsFound;
	}
	
	/**
	 * Waits for a certain time 
	 * @param timeToSleep the time to wait in milliseconds
	 */
	public void sleepSafely(final long timeToSleep) {
		try {
			Thread.sleep(timeToSleep); 
										
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clicks on a certain field
	 * @param selector The identification of the field you want to click
	 */
	protected void clickField(By selector) {
		final WebElement element = getElement(selector);
		element.click();
		sleepSafely(1000);
	}
	
	/**
	 * Use this method if you are expecting a popup browser open. 
	 * It will return to you the handle of the new popup browser.Clicks on a certain field
	 * @param selector The identification of the field you want to click
	 */
	protected String clickItemWithPopUp(By selector) {
		final WebElement element = getElement(selector);
		String newPopUpHandle = "";
		Set<String> openedWindows = webDriver.getWindowHandles();
		webDriver.findElement(selector).click();
		
		Set<String> newOpenedWindows = webDriver.getWindowHandles();
		//get the one difference
		newOpenedWindows.removeAll(openedWindows);
		
		int j = newOpenedWindows.size();
		System.out.println("size is :" + j);
		Iterator<String> openedWindowIterator = newOpenedWindows.iterator();
		while (openedWindowIterator.hasNext()) {
			newPopUpHandle = openedWindowIterator.next();
		}
		
		System.out.println(newPopUpHandle);
		
		return newPopUpHandle;
	}
	
	
	protected void switchToPopUpWindow() {
		Set<String> openWindows = getWebDriver().getWindowHandles();
		
		Iterator<String> openWindowsIterator = openWindows.iterator();
		String popUpWindow="";
		while (openWindowsIterator.hasNext()) {
			popUpWindow = openWindowsIterator.next();
		}
		
		getWebDriver().switchTo().window(popUpWindow);
		
	}
	
	/**
	 * Checks if WebElement exists of not
	 * @param selector The identification of the field you are checking.
	 * @return 
	 * true if the field exists. <br> 
	 * false if the field does not exists.
	 */
	protected boolean doesElementExists(By selector) {
		try {
			WebElement webElement = getWebDriver().findElement(selector);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	protected boolean isElementEnabled(By selector) {
		try {
			WebElement webElement = getWebDriver().findElement(selector);
			return webElement.isEnabled();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	
	/**
	 * Waits for a WebElement by the default wait period
	 * @param selector  The identification of the field you are checking. 
	 */
	public void waitForElement(final By selector) {
		waitForElement(selector, DEFAULT_WAIT_PERIOD);
	}

	/**
	 * Waits for a WebElement by the specified wait period in milliseconds
	 * @param selector The identification of the field you are checking.
	 * @param timeOutInSeconds The the time to wait 
	 */
	public void waitForElement(final By selector, long timeOutInSeconds) {
		WebDriver webDriver = getWebDriver();
		ExpectedCondition ec = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				webDriver.switchTo().defaultContent();
				webDriver.findElement(selector);
				return true;
			}
		};

		WebDriverWait w = new WebDriverWait(webDriver, timeOutInSeconds, 2000);
		webDriver.switchTo().defaultContent();
		w.until(ec);
	}
	
	/**
	 * Select a dropdown option using value.
	 * @param value The option value you want to select
	 * @param selector The identification of the field you are checking.
	 */

	public void selectUsingValue(String value, By selector) {
		WebDriver webDriver = getWebDriver();
		if (!value.equals(EMPTY_FIELD_MARKER)) {
			try {
				Select selectBox = new Select(webDriver.findElement(selector));
				selectBox.selectByValue(value);
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *  Select a dropdown option using text.
	 * @param value The option text you want to select.
	 * @param selector The identification of the field you are checking.
	 */
	public void selectUsingText(String value, By selector) {
		WebDriver webDriver = getWebDriver();
		if (!value.equals(EMPTY_FIELD_MARKER)) {
			try {
				Select selectBox = new Select(webDriver.findElement(selector));
				selectBox.selectByVisibleText(value);
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * check or uncheck a checkbox
	 * @param check true if you want the checkbox checked. false if you want the checkbox unchecked.
	 * @param selector The identification of the field you are checking.
	 */
	public void setCheckBox(boolean check, By selector) {
		if (check) {
			if (getElement(selector).getAttribute("checked") == null) {
				clickField(selector); 
			}
		}
		if (!check) {
			if (getElement(selector).getAttribute("checked") != null) {
				clickField(selector); 
			}
		}
	}
}
