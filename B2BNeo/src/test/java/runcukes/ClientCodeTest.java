package runcukes;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "json:target/cucumber.json" ,"pretty", "html:target/cucumber"},
        features = "src/main/resources",
        glue = "com.lh.test",
        tags = {"@Name-Test01"},
        monochrome = true)
public class ClientCodeTest { 
}
	