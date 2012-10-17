package ca.mytester.robbot.helper;

public enum WebDriverType {
	  CHROME("chrome"),
	  INTERNET_EXPLORER("ie"),
	  FIRE_FOX("firefox"),
	  HTML_UNIT("htmlunit"),
	  ANDROID("android"),
	  IPHONE("iphone");
	  
	  

	  private String browserType;
	  private WebDriverType(String browserType) {
	    this.browserType = browserType;
	  }
	  
	  public String getBrowserType() {
	    return browserType;
	  }
}
