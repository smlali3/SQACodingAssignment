
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebAutomation {

	WebDriver driver;
	JavascriptExecutor js;

	@Test(priority = 1)
	public void validateThatTwoItemsAreAddedToTheCart() throws InterruptedException {
		startDriver();

		WebElement cokies = driver.findElement(By.xpath("(//*[@id='onetrust-accept-btn-handler'])"));
		cokies.click();

		search("sofa");
		pickTheItem(1);
		addToBag();
		closeTheDialog();

		search("table");
		pickTheItem(3);
		addToBag();
		closeTheDialog();

		WebElement shopingCart = driver.findElement(By.xpath("//*[@data-tracking-label='shopping-bag']"));
		shopingCart.click();

		Thread.sleep(1000);
		List<WebElement> items = driver.findElements(By.xpath("//div[@class='product_product__pvcUf']"));
		Assert.assertEquals(items.size(), 2);
	}

	@Test(priority = 2)
	public void validateErrorMessage() {

		WebElement discountLink = driver.findElement(By.xpath("//*[text() = 'Have a discount code?']"));
		js.executeScript("arguments[0].scrollIntoView();", discountLink);
		discountLink.click();

		WebElement discountCode = driver.findElement(By.xpath("//*[@id='discountCode']"));
		discountCode.sendKeys("123456789ABCDEF");

		WebElement applyButton = driver.findElement(By.xpath("//*[text() = 'Apply']"));
		applyButton.click();

		WebElement errorMessage = driver.findElement(By.xpath("//span[@class='cart-ingka-form-field__message']"));
		Assert.assertTrue(errorMessage.getText().contains("invalid coupon code"));
		Assert.assertTrue(errorMessage.isDisplayed());
		
		driver.close();
	}

	public void startDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.ikea.com/us/en/");
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void search(String item) {
		WebElement searchBar = driver.findElement(By.xpath("//*[@placeholder='What are you looking for?']"));
		searchBar.sendKeys(item);

		WebElement searchButton = driver.findElement(By.xpath("//*[@id='search-box__searchbutton']"));
		searchButton.click();
	}

	public void pickTheItem(int index) {
		WebElement item = driver.findElement(By.xpath("(//*[@id=\"search-results\"]/div)[" + index + "]"));
		item.click();
	}

	public void addToBag() {
		WebElement addToBagButton = driver.findElement(By.xpath("//*[@class='pip-buy-module__buttons--left']"));
		js.executeScript("arguments[0].scrollIntoView();", addToBagButton);
		addToBagButton.click();
	}

	public void closeTheDialog() throws InterruptedException {
		WebElement closeIcon = driver.findElement(By.xpath("//button[@aria-label='Close']"));
		closeIcon.click();
		Thread.sleep(1000);
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

}

