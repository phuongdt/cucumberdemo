package testRunning;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/",
		format={"pretty","junit:target/cucumber.xml"
				,"html:target"
				,"json:target/cucumber.json"},
		tags = {}
		)
public class TestRunner{

}