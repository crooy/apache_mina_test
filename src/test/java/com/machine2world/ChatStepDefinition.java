package com.machine2world;

import static junit.framework.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.SocketException;
import java.util.HashMap;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ChatStepDefinition {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ChatStepDefinition(){
		PropertyConfigurator.configure("log4j.properties");
	}
	
	@Override
	protected void finalize() throws Throwable {		
		super.finalize();
	}
	private static HelloMina minaServer;

	@Given("a chat HelloMina server")
	public void StartHelloMina() throws IOException{
		minaServer = new HelloMina(6666);
		minaServer.run();
	}
	
	private HashMap<Integer,AutomatedTelnetClient> clients = new HashMap<Integer,AutomatedTelnetClient>();
	
	
	@When("telnet client (\\d+) connects")
	public void ConnectedTelnet(int index) throws SocketException, IOException{
		clients.put(index, new AutomatedTelnetClient("localhost",6666));
	}	
	
	@Given("a telnet connection (\\d+)")
	public void ConnectTelnetClient(int index) throws SocketException, IOException{
		clients.put(index, new AutomatedTelnetClient("localhost",6666));
	}
	
	@When("the '(.+)' command is given by connection (\\d+)")
	public void GiveCommand(String command, int index) throws Exception, IOException{	
		assertTrue(clients.get(index).sendCommand(command));
	}
	
	@Then("the connection of client (\\d+) is open")
	public void TheConnectionShouldBeAccepted(int index){
		assertTrue(clients.get(index).isConnected());
	}
	
	@Then("the connection of client (\\d+) is closed")
	public void TheConnectionIsClosed(int index) throws InterruptedException{
		assertFalse(clients.get(index).sendCommand("test"));
	}
	@Then("^connection (\\d+) receives 'Hello'$")
	public void connection_receives_Hello(int arg1) throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}
}
