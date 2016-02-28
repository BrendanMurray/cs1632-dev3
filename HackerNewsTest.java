import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.regex.Pattern;

public class HackerNewsTest {

	static WebDriver driver = new HtmlUnitDriver();
	static String baseUrl = "https://news.ycombinator.com";
	// Start at the home page for hackernews for each test
	@BeforeClass
	public static void beforeAllSetUp() throws Exception{
		driver.get(baseUrl);
		driver.findElement(By.linkText("login")).click();
	    driver.findElement(By.name("acct")).clear();
	    driver.findElement(By.name("acct")).sendKeys("cs1632Test");
	    driver.findElement(By.name("pw")).clear();
	    driver.findElement(By.name("pw")).sendKeys("pokemon123");
	    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	}
	@Before
	public void setUp() throws Exception {
		driver.get(baseUrl);
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
	// When I click on the "show" link
	// Then I should be redirected to the "Show" page
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
	// When I click on the "jobs" link
	// Then I should be redirected to the "jobs" page
	@Test
	public void testSeeJobs() {
		
		// find the "jobs" link and click on it
		// The page you go to should include "jobs" in the title
		
		driver.findElement(By.linkText("jobs")).click();
		String newPageTitle = driver.getTitle();
		assertTrue(newPageTitle.contains("jobs"));
	}

	// Given that I am on the main page
	// When I click on the "login" link
	// Then I should be redirected to the "login" page
	@Test
	public void testSeeLogin() {
		
		//Check that the page contains the "login" element
		
		try {
			driver.findElement(By.linkText("login"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Given that I am on the main page
	//When I click on the "discuss" link on a story
	//I should be sent to the comments page
	@Test
	public void testDiscussComments(){
		try{
			driver.findElement(By.partialLinkText("discuss")).click();
			String URL = driver.getCurrentUrl();
			if (!URL.contains("item?id="))
				fail();
		} catch (NoSuchElementException nseex) {
			fail();
		}
		
	}
	
	//Given that I am on the main page
	//When I click on the "?? comments" link on a story
	//I should be sent to the comments page
	@Test
	public void testNNComments(){
		try{
			driver.findElement(By.partialLinkText(" comments")).click();	//TODO this could be a regex
			String URL = driver.getCurrentUrl();
			if (!URL.contains("item?id="))
				fail();
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Given that I am on a comments page
	//When I click on a "reply" link on a comment
	//I should be sent to the reply page
	@Test
	public void testNNCommentsReply(){
		try{
			driver.findElement(By.partialLinkText(" comments")).click();
			driver.findElement(By.partialLinkText("reply")).click();
			String URL = driver.getCurrentUrl();
			if (!URL.contains("reply"))
				fail();
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}

	//Need a user logged in for this test.
	//Given that I am on a comments page
	//When I click on reply, and then click "parent" link to go back
	//I should be sent to the original comments page
	@Test
	public void testNNCommentsFindParent(){
		try{
			driver.findElement(By.partialLinkText(" comments")).click();
			String firstURL = driver.getCurrentUrl();
			driver.findElement(By.partialLinkText("reply")).click();
			driver.findElement(By.partialLinkText("parent")).click();
			String secondURL = driver.getCurrentUrl();
			assertTrue(firstURL.equals(secondURL));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Need a user logged in for this test.
	//Given that I am on a comments page
	//When I click on 'web'
	//I should be sent to a google search of the story
	@Test
	public void testNNCommentsFindWeb(){
		try{
			driver.findElement(By.partialLinkText(" comments")).click();
			driver.findElement(By.partialLinkText("web")).click();
			String URL = driver.getCurrentUrl();
			assertTrue(URL.contains("google"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}

	//Test searching functionality
	/*
	@Test
	public void testSearchExistingStory()
	{
		fail();
	}
	@Test
	public void testSearchNoResults()
	{
		fail();
	}
	public void testSearchRedirect()
	{
		fail();
	}
	public void testSearchTime()
	{
		fail();
	}
	public void testSearchNumResults()
	{
		fail();
	}
	
	//Test Profile Page
	public void testProfileRedirect()
	{
		fail();
	}
	public void testProfileCommentsRedirect()
	{
		fail();
	}
	public void testProfileSubmissionRedirect()
	{
		fail();
	}
	public void testProfileUserName()
	{
		fail();
	}
	*/
}