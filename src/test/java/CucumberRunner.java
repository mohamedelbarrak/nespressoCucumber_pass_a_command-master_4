import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//mvn test  -Dtest=CucumberRunner "-Dcucumber.options=--tags @Ready"
@RunWith(Cucumber.class)
@CucumberOptions(features = "features/", glue = "home",tags = "@Ready",plugin = {"pretty", "html:target/htmlreports.html", "json:target/htmlreports.json"})
public class CucumberRunner {
}
