import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.Highlighter;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FirstTests {
    WebDriver driver; //utworzenie pustego pola driver, aby było dostępne we wszystkich metodach
    WebDriverWait wait;

    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    @Before //warunki poczatkowe testow, wykona się przed każdą metodą test
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        //ustawiamy property ze wskazaniem na chromedriver, ktorego uzyjemy w testach
        driver = new ChromeDriver(); //otworzy nam przeglądarkę
        System.out.println(" wykonuję się tutaj! przed metodą testową");
        wait = new WebDriverWait(driver, 10);
    }

    @Test //kroki testowe - po prostu test do wykonania
    public void firstTest() {
        driver.get("https://dev.to"); //przejdź na stronę dev.to
        WebElement sideBarVideo = driver.findElement(By.xpath("/html/body/div[9]/div/div/div[1]/aside/nav[1]/div/a[3]")); //znajdz element week
        highlightElement(sideBarVideo);
        //sideBarVideo.click(); //klikamy w weekBtn
    }

    @Test
    public void openFirstVideoPage() {
        driver.get("https://dev.to");
        WebElement sideBarVideo = driver.findElement(By.partialLinkText("Videos"));
        highlightElement(sideBarVideo);
        sideBarVideo.click();
        //przechodzimy na strone z video
        //powinniśmy poczekać na załadowanie nowej strony

        wait.until(ExpectedConditions.urlToBe("https://dev.to/videos"));
        WebElement firstVideo = driver.findElement(By.className("video-image"));
        highlightElement(firstVideo);
        firstVideo.click();
    }

    @Test
    public void highlightFirstVideo() {
        driver.get("https://dev.to/videos");
        WebElement firstVideo = driver.findElement(By.className("video-image"));
        highlightElement(firstVideo);
        firstVideo.click();
    }

    @Test
    public void selectFirstPodcast() {
        driver.get("https://dev.to");
        WebElement podcasts = driver.findElement(By.partialLinkText("Podcasts"));
        podcasts.click();
        wait.until(ExpectedConditions.urlToBe("https://dev.to/pod"));
        WebElement firstPodcast = driver.findElement(By.cssSelector(".content > h3:first-child"));
        String podcastTitleFromList = firstPodcast.getText();
        String firstPodcastFromListLink = driver.findElement(By.cssSelector("#substories > a:first-child")).getAttribute("href");
        firstPodcast.click();
        wait.until(ExpectedConditions.urlToBe(firstPodcastFromListLink));
        WebElement podcastTitle = driver.findElement(By.cssSelector(".title > h1:nth-child(2)"));
        String podcastTittleText = podcastTitle.getText();
        assertTrue(podcastTitleFromList.contains(podcastTittleText));
        WebElement record = driver.findElement(By.cssSelector(".record"));
        record.click();
        WebElement initializing = driver.findElement(By.className("status-message"));
        wait.until(ExpectedConditions.invisibilityOf(initializing));

        WebElement recordyWrapper = driver.findElement(By.className("record-wrapper"));
        String classAtribute = recordyWrapper.getAttribute("class");
        Boolean isPodcastPlayed = classAtribute.contains("playing");


        assertTrue(isPodcastPlayed);



        }
    @Test
    public void newTest(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://dev.to");
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("testing");
        search.sendKeys(Keys.ENTER);
        WebElement Text = driver.findElement(By.tagName("mark"));
        String bodyText = Text.getText();
        int count = 0;
        while (bodyText.contains("testing")){
            count ++;
            bodyText = bodyText.substring(bodyText.indexOf("testing") + "testing".length());
        }
        System.out.println(count);}

        @Test
                public void NewOne(){
        driver.navigate().to("https://dev.to");
        WebElement sideBarElement = driver.findElement(By.xpath("//a[@id='article-link-540975']"));
        highlightElement(sideBarElement);
        sideBarElement.click();


        }
        @Test
    public void openFirstTagOnPage(){
        driver.get("https://dev.to");
        WebElement Tag = driver.findElement(By.partialLinkText("Tags"));
        highlightElement(Tag);
        Tag.click();


        }






       // int numberOfText = driver.getPageSource()


    }


//    @After //czynnosci zamykające testy
//    public void tearDown(){
//        driver.quit();
//        System.out.println("po każdej metodzie testowej");
//    }
