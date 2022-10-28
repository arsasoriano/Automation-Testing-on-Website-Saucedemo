package step_definitions;

import com.example.pageObject.InventoryPage;
import com.example.pageObject.LoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;

public class loginSteps {

    private WebDriver webDriver;

    public loginSteps(){
        super();
        this.webDriver = Hooks.webDriver;
    }

    @Given("user open the web page")
    public void verifyLoginPage (){
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.isDisplayed();
    }

    @When("user input \"(.*)\" as userName and \"(.*)\" as password")
    public void inputCredential(String userName,String password){
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.setUsername(userName);
        loginPage.setPassword(password);
        loginPage.clickLogin();
    }

    @Then("user already go to inventory page")
    public void verifyInventoryPage() throws InterruptedException {
        InventoryPage inventoryPage = new InventoryPage(webDriver);
        Assert.assertTrue(inventoryPage.displayed());
        Thread.sleep(2000);
    }

    @Then("user will get \"(.*)\" on login page")
    public void verifyErrorMessage(String errorText){
        LoginPage loginPage = new LoginPage(webDriver);
        assertEquals(errorText, loginPage.getErrorMessage());
    }
}
