package com.app.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		plugin = {"pretty", 
				"html:target/cucumber-report",  //This line will give the the html report and it's location
				"json:target/cucumber.json"
		},
		tags = "@Api",
		features= {"src/test/resources/com/app/features/","src/test/resources/com/app/hrapp_features/"},
		glue="com/app/step_definitions/",
		dryRun=false 
)
public class CukesRunner extends AbstractTestNGCucumberTests {
	

}
