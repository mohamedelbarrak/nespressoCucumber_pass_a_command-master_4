package home;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.cucumber.java.After;
import org.nespresso.driver.Driver;
import org.nespresso.home.HomePage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.nespresso.listProduct.ListProductPage;
import org.openqa.selenium.WebDriver;

import static org.nespresso.driver.Driver.driver;

@RunWith(Cucumber.class)
public class HomeStepdefs {
    HomePage homePage;
    ListProductPage listProductPage;
    @Before
    public void prepateDriver() {
        new Driver();
        homePage = new HomePage();
        listProductPage = new ListProductPage();
    }

    @Given("^l'utilisateur est dans la page home$")
    public void lutilisateur_est_dans_la_page_home() throws Throwable {
        homePage.openPage();
    }

    @And("^accepter les cookies$")
    public void accepter_les_cookies() throws Throwable {
        homePage.acceptCoukies();
    }

    @And("^ouvrire la page de produit$")
    public void ouvrire_la_page_de_produit() throws Throwable {
        homePage.ouvrire_la_page_de_produit();
    }

    @When("^AJOUTER AU PANIER$")
    public void ajouter_au_panier() throws Throwable {
        listProductPage.ajouter_au_panier();
    }

    @And("^choisir la quantite (.+)$")
    public void choisir_la_quantite(String quantite) throws Throwable {
        listProductPage.choisir_la_quantite(quantite);
    }

    @Then("^votre panier change (.+)$")
    public void votre_panier_change(String quantite) throws Throwable {
        homePage.votre_panier_change(quantite);
    }

    @After
    public void terminateBrowser(){
        driver.quit();
    }

}