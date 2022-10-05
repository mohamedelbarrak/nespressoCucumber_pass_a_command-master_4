package org.nespresso.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage {
    WebDriver driver;
    String webDriverType = "webdriver.chrome.driver";
    String webDriverPath = "C:/melbarrak/Applications/intellijSeleniumJarAndDrivers/chromedriver_win32/chromedriver.exe";
    String nespressoUrl = "https://www.nespresso.com/fr/fr/";
    final List<String> quantitys = List.of("0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "150", "200", "250", "300");

    @FindBy(xpath = "//button[@id='_evidon-banner-acceptbutton']")//org.openqa.selenium.support
    public WebElement acceptCoukies;

    @FindBy(xpath = "(//span[contains(@class,'HeaderNavigationBarItem')])[1]")
    public WebElement coffeeMenu;

    @FindBy(xpath = "(//span[contains(@class,'HeaderNavigationBarItem')])[1]")
    public WebElement coffeeMenuChoice;

    @FindBy(xpath = "//div[starts-with(@class, 'ProductList__content')]//div[@class='ProductListGroup'][1]//div[@class='ProductListElementFilter']//child::div[@class='ProductListElement__content ProductListElement__content--capsule']//child::div[starts-with(@id, 'AddToBagButton')]")
    public WebElement addToCart;

    final String QUANTITY = "//div[starts-with(@class, 'ProductList__content')]//div[@class='ProductListGroup'][1]//div[@class='ProductListElementFilter']//child::div[@class='ProductListElement__content ProductListElement__content--capsule']//child::div[starts-with(@id, 'AddToBagButton')]//descendant::li[@class='PredefinedQuantityList__quantity']["+"2"+"]";
    @FindBy(xpath = QUANTITY)
    public WebElement choiceQuantity;

    @FindBy(xpath = "//button[@id='ta-mini-basket__open']//child::span")
    public WebElement pannierQuantityNumber;

    @FindBy(xpath = "//button[@id='ta-mini-basket__open']")
    public WebElement pannierColor;

    public HomePage() {
        System.setProperty(webDriverType, webDriverPath);
        this.driver = new ChromeDriver();
        PageFactory.initElements(driver,this);
    }

    public void openPage(){
        driver.get(nespressoUrl);
    }

    public void acceptCoukies(){
        //driver.findElement(By.id("_evidon-banner-acceptbutton")).click();
        //driver.findElement(By.xpath("//button[@id='_evidon-banner-acceptbutton']")).click();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        acceptCoukies.click();
    }

    public void ouvrire_la_page_de_produit(){
        new Actions(driver).clickAndHold(coffeeMenu).perform();
        //implicite Thread.sleep(5000);//TimeUnit.SECONDS.sleep(5);//Thread.sleep(5000);
        //vs
        //explicite driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        coffeeMenuChoice.click();
    }

    public void ajouter_au_panier(){
        addToCart.click();
    }

    public void choisir_la_quantite(String quantite){
        String quantitySelectedIndexForqQuantitysList = String.valueOf(quantitys.indexOf(quantite)+1);
        String QUANTITY = "//div[starts-with(@class, 'ProductList__content')]//div[@class='ProductListGroup'][1]//div[@class='ProductListElementFilter']//child::div[@class='ProductListElement__content ProductListElement__content--capsule']//child::div[starts-with(@id, 'AddToBagButton')]//descendant::li[@class='PredefinedQuantityList__quantity']["+quantitySelectedIndexForqQuantitysList+"]";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath(QUANTITY)).click();
        System.out.println("quantitySelected: " + quantitySelectedIndexForqQuantitysList);
    }

    public void votre_panier_change(String quantite) throws InterruptedException {
        //TimeUnit.SECONDS.sleep(3);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        TimeUnit.SECONDS.sleep(5);
        String pannierQuantity = pannierQuantityNumber.getText();
        //

        //getColor
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String pannierColor_background_color = pannierColor.getCssValue("background-color");
        String pannierColorAsHex = Color.fromString(pannierColor_background_color).asHex();
        System.out.println("pannierQuantity:"+pannierQuantity);
        System.out.println("pannierColor_background_color_2:"+Color.fromString(pannierColor_background_color).asHex());//#3d8705
        //System.out.println("pannierColorAsHex: "+pannierColorAsHex+" pannierColorAsHex "+pannierColorAsHex);
        //3 #4c9018 //4 #3d8705
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        switch (quantite){
            case "0":
                if (pannierQuantity.equalsIgnoreCase(quantite)){
                    System.out.println("same quantity 1");
                }else {
                    System.out.println("erreur 1");
                    driver.findElement(By.xpath("//erreur"));
                }
                break;
            default:
                if (pannierQuantity.equalsIgnoreCase(quantite) && pannierColorAsHex.equalsIgnoreCase("#3d8705")){
                    System.out.println("same quantity 2");
                }else {
                    System.out.println("erreur 2");
                    driver.findElement(By.xpath("//erreur"));
                }
                break;
        }
    }

    public void terminateBrowser() {
        this.driver.quit();
    }
}
