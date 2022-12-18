
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTestCase {
    @Test
    public void buyProductWithoutLogin() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://askomdch.com/");

        driver.findElement(By.cssSelector("li[id='menu-item-1227'] a[class='menu-link']")).click();
        driver.findElement(By.cssSelector("#woocommerce-product-search-field-0")).sendKeys("Blue");
        driver.findElement(By.cssSelector("button[value='Search']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector(".woocommerce-products-header__title.page-title")).getText(),"Search results: “Blue”");

        driver.findElement(By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("a[title='View cart']")).click();
        Thread.sleep(3000);

        String productName = driver.findElement(By.cssSelector("td[class='product-name'] a")).getText();
        Assert.assertEquals(productName,"Blue Shoes");

        driver.findElement(By.cssSelector(".checkout-button.button.alt.wc-forward")).click();

        driver.findElement(By.cssSelector("#billing_first_name")).sendKeys("user");
        driver.findElement(By.cssSelector("#billing_last_name")).sendKeys("ami");
        driver.findElement(By.cssSelector("#billing_address_1")).sendKeys("10050");
        driver.findElement(By.cssSelector("#billing_city")).sendKeys("ban");
        driver.findElement(By.cssSelector("#billing_postcode")).sendKeys("94188");
        driver.findElement(By.cssSelector("#billing_email")).sendKeys("user@gmail.com");
        driver.findElement(By.cssSelector("#place_order")).click();

        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("[class*='woocommerce-notice']")).getText(),"Thank you. Your order has been received.");

    }
}
