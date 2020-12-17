package zdtest.cucumbertests;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;


import static org.junit.Assert.assertTrue;
public class StepsDefinitions {
    WebDriver driver;
    WebDriverWait wait;
    String podcastTitleFromList;
    String firstPodcastFromListLink;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }
    @Given("DevTo main page is open")
    public void dev_to_main_page_is_open() {
        driver.get("https://dev.to");
    }
    @When("User click on podcasts")
    public void user_click_on_podcasts() {
        WebElement podcasts = driver.findElement(By.partialLinkText("Podcasts"));
        podcasts.click();
    }
    @When("User select first podcast")
    public void user_select_first_podcast() {
        wait.until(ExpectedConditions.urlToBe("https://dev.to/pod"));
        WebElement firstPodcast = driver.findElement(By.cssSelector(".content > h3:first-child"));
        podcastTitleFromList = firstPodcast.getText();
        firstPodcastFromListLink = driver.findElement(By.cssSelector("#substories > a:first-child")).getAttribute("href");
        firstPodcast.click();
    }
    @Then("User can see its title")
    public void user_can_see_its_title() {
        wait.until(ExpectedConditions.urlToBe(firstPodcastFromListLink));
        WebElement podcastTitle = driver.findElement(By.cssSelector(".title > h1:nth-child(2)"));
        String podcastTitleText = podcastTitle.getText();
        assertTrue(podcastTitleFromList.contains(podcastTitleText));
    }
    @Then("User can play it")
    public void user_can_play_it() {
        WebElement record = driver.findElement(By.className("record"));
        record.click();
        WebElement initializing = driver.findElement(By.className("status-message"));
        wait.until(ExpectedConditions.invisibilityOf(initializing));
        WebElement recordWrapper = driver.findElement(By.className("record-wrapper"));
        String classAttribute = recordWrapper.getAttribute("class");
        Boolean isPodcastPlayed = classAttribute.contains("playing");
        assertTrue(isPodcastPlayed);
    }
    @When("User click on Videos button")
    public void user_click_on_Video_button() {
        WebElement sideBarVideo = driver.findElement(By.partialLinkText("Videos"));
        sideBarVideo.click();
    }


    @Then("Video page is open")
    public void video_is_played() {

    }


    @And("User click on first Video")
    public void userClickOnFirstVideo() {
        wait.until(ExpectedConditions.urlToBe("https://dev.to/videos"));
        WebElement firstVideo = driver.findElement(By.className("video-image"));
        firstVideo.click();
    }


    @Given("DevTo page is open")
    public void devtoPageIsOpen() {
        driver.get("https://dev.to");
    }

    @When("User click on Tag button")
    public void userClickOnTagButton() {
        WebElement Tag = driver.findElement(By.partialLinkText("Tags"));
        Tag.click();
    }


    @Then("Page with Tags is open")
    public void firstTagIsOpen() {
    }



    @When("User search {string}")
    public void userSearch(String searchingPhrase) {
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.sendKeys("testing");
        searchBar.sendKeys(Keys.ENTER);

    }

    @Then("Top result should contain the word {string}")
    public void topResultShouldContainTheWord(String expectedPhrase) {
        wait.until(ExpectedConditions.urlContains("https://dev.to/search?q=" + expectedPhrase));
        WebElement topResult = driver.findElement(By.className("crayons-story_title"));
        String topResultTitle = topResult.getText();
        topResultTitle = topResultTitle.toLowerCase();
        assertTrue(topResultTitle.contains(expectedPhrase));
    }
}