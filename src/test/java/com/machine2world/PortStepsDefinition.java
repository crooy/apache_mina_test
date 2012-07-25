package com.machine2world;

import static junit.framework.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

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
		super.finalize();
	}

	private static HelloMina minaServer;
	private AutomatedTelnetClient client;

	
	
	@Given("a simple HelloMina server")
	public void StartHelloMina() throws IOException{
		minaServer = new HelloMina(6666);
		minaServer.run();
	}
	
	@Given("a telnet connection")
	public void ConnectedTelnet(){
		ConnectTelnetClient();
	}	
	
	@When("a telnet client connects")
	public void ConnectTelnetClient(){
		
		try{
			client = new AutomatedTelnetClient("localhost",6666);
		}catch(Exception e){			
			fail("unable to connect: "+e.getMessage());
		}
	}
	
	@When("the '(.+)' command is given")
	public void GiveCommand(String command) throws Exception, IOException{	
		assertTrue(client.sendCommand(command));
	}
	
	@Then("the connection should be accepted")
	public void TheConnectionShouldBeAccepted(){
		assertTrue(client.isConnected());
	}
	
	@Then("the connection is closed")
	public void TheConnectionIsClosed() throws InterruptedException{
		assertFalse(client.sendCommand("test"));
	}

}
