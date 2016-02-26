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
	
	// Given that I am on the main page
	// When I click on the "comments" link
	// Then I should be redirected to the "new comments" page
	@Test
	public void testSeeComments() {
		
		// find the "comments" link and click on it
		// The page you go to should include "New Comments" in the title
		
		driver.findElement(By.linkText("comments")).click();
		String newPageTitle = driver.getTitle();
		assertTrue(newPageTitle.contains("New Comments"));
	}
	
	// Given that I am on the main page
	// When I click on the "new" link
	// Then I should be redirected to the "newest" page
	@Test
	public void testSeeShow() {
		
		// find the "show" link and click on it
		// The page you go to should include "Show" in the title
		
		driver.findElement(By.linkText("show")).click();
		String newPageTitle = driver.getTitle();
		assertTrue(newPageTitle.contains("Show"));
	}

	// Given that I am on the main page
	// When I click on the "ask" link
	// Then I should be redirected to the "Ask" page
	@Test
	public void testSeeAsk() {
		
		// find the "ask" link and click on it
		// The page you go to should include "Ask" in the title
		
		driver.findElement(By.linkText("ask")).click();
		String newPageTitle = driver.getTitle();
		assertTrue(newPageTitle.contains("Ask"));
	}

	// Given that I am on the main page
	// When I click on the "ask" link
	// Then I should be redirected to the "Ask" page
	@Test
	public void testSeeJobs() {
		
		// find the "jobs" link and click on it
		// The page you go to should include "jobs" in the title
		
		driver.findElement(By.linkText("jobs")).click();
		String newPageTitle = driver.getTitle();
		assertTrue(newPageTitle.contains("jobs"));
	}
}