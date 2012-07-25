package com.machine2world;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;

public class PortStepsDefinition {
	@Given("a HelloMina server")
	public void StartHelloMina(){
		throw new PendingException();
	}
	
	@When("a telnet client connects")
	public void ConnectTelnetClient(){
		throw new PendingException();
	}
	
	@Then("the connection should be accepted")
	public void TheConnectionShouldBeAccepted(){
		throw new PendingException();
	}
}
