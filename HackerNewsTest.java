import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HackerNewsTest {

  static WebDriver driver = new HtmlUnitDriver();
  
  // Start at the home page for hackernews for each test
  @Before
  public void setUp() throws Exception {
    driver.get("https://news.ycombinator.com");
  }

  // Given that I am on the main page
  // When I view the title
  // Then I see that it contains the words "Hacker News"
  @Test
  public void testShowsCorrectTitle() {
    
    // Simply check that the title contains the word "Hacker News"
    
    String title = driver.getTitle();
    assertTrue(title.contains("Hacker News"));
  }
  
  // Given that I am on the main page
  // When I view the header
  // Then I see that it contains "new", "comments", "show", "ask", "jobs", "submit" links
  @Test
  public void testHasCorrectHeaderLinks() {
    
    //Check that the nav bar contains the correct fields
    
    try {
      driver.findElement(By.linkText("Hacker News"));
      driver.findElement(By.linkText("new"));
      driver.findElement(By.linkText("comments"));
      driver.findElement(By.linkText("show"));
      driver.findElement(By.linkText("ask"));
      driver.findElement(By.linkText("jobs"));
      driver.findElement(By.linkText("submit"));
    } catch (NoSuchElementException nseex) {
      fail();
    }
  }
  
  
  // Given that I am on the main page
  // When I click on the "new" link
  // Then I should be redirected to the "newest" page
  @Test
  public void testSeeNewLinks() {
    
    // find the "new" link and click on it
    // The page you go to should include "New Links" in the title
    
    driver.findElement(By.linkText("new")).click();
    String newPageTitle = driver.getTitle();
    assertTrue(newPageTitle.contains("New Links"));
  }

}