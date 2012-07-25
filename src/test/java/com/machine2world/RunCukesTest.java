package com.machine2world;

import cucumber.junit.Cucumber;

import org.apache.log4j.PropertyConfigurator;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(format = {"pretty", "html:target/cucumber-html-report", "json-pretty:target/cucumber-report.json"})
public class RunCukesTest {
	
}