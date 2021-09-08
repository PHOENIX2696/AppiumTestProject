package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

/**
 * @author AH19309
 */
public class HomePage {

    @Given("^Launch the youtube application$")
    public void launchTheApplication(){
        System.out.println("Youtube launched");
    }


    @Then("^Youtube home page displayed$")
    public void youtubeHomePageDisplayed() throws InterruptedException {
        System.out.println("Home Page Displayed");
        Thread.sleep(5000);
    }
}
