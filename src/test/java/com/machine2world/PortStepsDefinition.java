package com.machine2world;

import static junit.framework.Assert.*;

import java.io.IOException;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PortStepsDefinition {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public PortStepsDefinition(){
		PropertyConfigurator.configure("log4j.properties");
	}
	
	@Override
	protected void finalize() throws Throwable {
		minaServer.stop();
		super.finalize();
	}

	private static HelloMina minaServer;
	private TelnetClient client;

	
	
	@Given("a HelloMina server")
	public void StartHelloMina() throws IOException{
		minaServer = new HelloMina(6666);
		minaServer.start();
	}
	
	@When("a telnet client connects")
	public void ConnectTelnetClient(){
		client = new TelnetClient();
		try{
			client.connect("localhost",6666);
		}catch(Exception e){			
			fail("unable to connect: "+e.getMessage());
		}
	}
	
	@Then("the connection should be accepted")
	public void TheConnectionShouldBeAccepted(){
		assertTrue(client.isConnected());
	}
}
