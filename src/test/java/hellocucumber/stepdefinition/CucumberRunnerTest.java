package hellocucumber.stepdefinition;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"pretty","json:target/cucumber.json"},
        features="classpath:featurefile"
)
public class CucumberRunnerTest {
}
