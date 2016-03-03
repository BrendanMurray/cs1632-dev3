import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import java.util.regex.Pattern;

public class HackerNewsTest {

	//static WebDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_38);
	static WebDriver driver = new FirefoxDriver();
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
			driver.findElement(By.partialLinkText(" comments")).click();	//find a '[0-9] comments' link 
			String URL = driver.getCurrentUrl();
			if (!URL.contains("item?id="))	//if the URL is missing the 'item?id=' string it is not a comments page 
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
			driver.findElement(By.partialLinkText(" comments")).click();	//find a '[0-9] comments' link 
			driver.findElement(By.partialLinkText("reply")).click();
			String URL = driver.getCurrentUrl();
			if (!URL.contains("reply"))	//if the URL is missing the 'reply' string it is not a reply page
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
			driver.findElement(By.partialLinkText(" comments")).click();	//find a comments link
			String firstURL = driver.getCurrentUrl();
			driver.findElement(By.partialLinkText("reply")).click();		//get to reply page
			driver.findElement(By.partialLinkText("parent")).click();		//find parent link to return to reply page
			String secondURL = driver.getCurrentUrl();
			assertTrue(firstURL.equals(secondURL));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}

	//Given that I am on a comments page
	//When I click on 'web'
	//I should be sent to a google search
	@Test
	public void testNNCommentsFindWeb(){
		try{
			driver.findElement(By.partialLinkText(" comments")).click();	//find a comments link
			driver.findElement(By.partialLinkText("web")).click();			//click the web link
			String URL = driver.getCurrentUrl();
			assertTrue(URL.contains("google"));								//check if the URL is now google
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
//Tests for search functionality
	
	//Given that I am on the main page
	//When I search for the first story on the page
	//I should not see no results
	@Test
	public void testSearchExistingStory()
	{
		WebElement element = driver.findElement(By.cssSelector("tr.athing:nth-child(1) > td:nth-child(3) > a:nth-child(2)"));
		String elementStr = element.getText();
		System.out.println(elementStr);
	    driver.findElement(By.name("q")).clear();
	    driver.findElement(By.name("q")).sendKeys(elementStr);
	    driver.findElement(By.name("q")).submit();
	   
	    assertFalse(elementStr.contains("No results"));
	}
	
	//Given that I I am on the main page
	//When I search for random nonsense
	//I should not see any results
	@Test
	public void testSearchNoResults()
	{
		//finds search text box
	    driver.findElement(By.name("q")).clear();
	    driver.findElement(By.name("q")).sendKeys(";lakjh;lksjd;jo");
	    driver.findElement(By.name("q")).submit();
	    WebElement element = driver.findElement(By.cssSelector("li.ng-binding"));
	    String elementStr = element.getText();
	    assertTrue(elementStr.contains("No results"));
	}
	
	//Given that I am on the main page
	//When I search for something
	//I should be redirected to algolia's site
	@Test
	public void testSearchRedirect()
	{
		//finds search text box
	    driver.findElement(By.name("q")).clear();
	    driver.findElement(By.name("q")).sendKeys("linux");
	    driver.findElement(By.name("q")).submit();
	    System.out.println(driver.getCurrentUrl());
	    assertTrue(driver.getCurrentUrl().contains("algolia"));
	}
	
	//Given that I am on the main page
	//When I make a search
	//I should see a time elapsed for the search
	@Test
	public void testSearchTime()
	{
		//finds search text box
	    driver.findElement(By.name("q")).clear();
	    driver.findElement(By.name("q")).sendKeys("linux");
	    driver.findElement(By.name("q")).submit();
	    WebElement element = driver.findElement(By.cssSelector("li.ng-binding"));
	    String elementStr = element.getText();
	    assertTrue(elementStr.contains("seconds"));
	}
	
	//Given that I am on the main page
	//When I make a search
	//I should see the number of results for that search
	@Test
	public void testSearchNumResults()
	{
	    driver.findElement(By.name("q")).clear();
	    WebElement search = driver.findElement(By.name("q"));
	    driver.findElement(By.name("q")).sendKeys("linux");
	    driver.findElement(By.name("q")).submit();;
	    assertTrue(driver.findElement(By.cssSelector("li.ng-binding > ng-pluralize:nth-child(2)")).getText().contains(" results"));
	}
	
	//Test Profile Page
	
	//Given that I am on the main page
	//When I click on my profile name
	//I should be redirected to my profile
	@Test
	public void testProfileRedirect()
	{
		//clicks on username
		driver.findElement(By.linkText("cs1632Test")).click();
		String newPageTitle = driver.getTitle();
		assertTrue(newPageTitle.contains("Profile"));
	}
	
	//Given that I am on my profile page
	//When I click comments
	//I should be redirected to a page with my comments
	@Test
	public void testProfileCommentsRedirect()
	{
		//Clicks on username
	    driver.findElement(By.cssSelector("#hnmain > tbody:nth-child(1) > tr:nth-child(1) "
	    								+ "> td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) "
	    								+ "> tr:nth-child(1) > td:nth-child(3) > span:nth-child(1) > "
	    								+ "a:nth-child(1)")
	    								).click();
	    //Clicks on profile comments link
		driver.findElement(By.cssSelector(".profileform > table:nth-child(3) > tbody:nth-child(1) "
										+ "> tr:nth-child(14) > td:nth-child(2) > a:nth-child(1) "
										+ "> u:nth-child(1)")
										).click();
		String newPageUrl= driver.getCurrentUrl();
		System.out.println(newPageUrl);
		assertTrue(newPageUrl.contains("threads?id=cs1632Test"));
	}
	
	//Given that I am on my profile page
	//When I click submissions
	//I should be redirected to a page with my submissions
	@Test
	public void testProfileSubmissionRedirect()
	{
		//clicks username
		driver.findElement(By.linkText("cs1632Test")).click();
		//clicks the submission link
		driver.findElement(By.partialLinkText("submission")).click();
		String newPageTitle = driver.getTitle();
		assertTrue(newPageTitle.contains("submission"));
	}
	
	//Given that I am on my profile page
	//I should see a line with my username next to it	
	@Test
	public void testProfileUserName()
	{
		//Clicks username
		driver.findElement(By.cssSelector("#hnmain > tbody:nth-child(1) > tr:nth-child(1) "
				+ "> td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) "
				+ "> tr:nth-child(1) > td:nth-child(3) > span:nth-child(1) > "
				+ "a:nth-child(1)")
				).click();
		//finds username section on page
		WebElement element = driver.findElement(By.cssSelector(".profileform > table:nth-child(3) > tbody:nth-child(1) "
										+ "> tr:nth-child(1) > td:nth-child(2) > a:nth-child(1) "
										+ "> font:nth-child(1)"));
		String elementStr = element.getText();
		assertTrue(elementStr.equals("cs1632Test"));
	}
	
}