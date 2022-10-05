package org.nespresso.listProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

import static org.nespresso.home.HomePage.driver;

public class ListProductPage {
    @FindBy(xpath = "//div[starts-with(@class, 'ProductList__content')]//div[@class='ProductListGroup'][1]//div[@class='ProductListElementFilter']//child::div[@class='ProductListElement__content ProductListElement__content--capsule']//child::div[starts-with(@id, 'AddToBagButton')]")
    public WebElement addToCart;

    final List<String> quantitys = List.of("0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "150", "200", "250", "300");

    public ListProductPage(){
        //this.driver = driver;
        PageFactory.initElements(driver,this);
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
}
