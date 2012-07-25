package com.machine2world;

import org.apache.commons.net.telnet.TelnetClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;

public class AutomatedTelnetClient {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	private TelnetClient telnet = new TelnetClient();
	private InputStream in;
	private PrintStream out;
	private String prompt = "#";

	public AutomatedTelnetClient(String server, int port) throws SocketException, IOException {
			// Connect to the specified server
			telnet.connect(server, port);

			// Get input and output stream references
			in = telnet.getInputStream();
			out = new PrintStream(telnet.getOutputStream());
			logger.info("connected telnet client to {}:{}",server,port);
	}

	
	
	public boolean isConnected(){
		return telnet.isConnected();
	}

	public boolean readUntil(String pattern) {
		try {
			char lastChar = pattern.charAt(pattern.length() - 1);
			StringBuffer sb = new StringBuffer();
			boolean found = false;
			int chi = in.read();
			char ch = (char) chi;
			while (true) {				
				sb.append(ch);
				logger.debug("read char {}",ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						return true;
					}
				}
				chi = in.read();
				if (chi == -1){
					return false;
				}
				ch = (char) chi;
			}
		}
		catch (Exception e) {
			logger.error("error while reading echoed output '{}' : {}",pattern,e.getMessage());
			return false;
		}
		
	}

	public void write(String value) {
		try {
			out.println(value);
			out.flush();
			System.out.println(value);
		}
		catch (Exception e) {
			logger.error("error while writing '{}' : {}",value,e.getMessage());
		}
	}

	public boolean sendCommand(String command){
		return sendCommand(command, command);
	}
	public boolean sendCommand(String command, String expectedReturn) {
		try {
			write(command);
			return readUntil(expectedReturn);
		}
		catch (Exception e) {
			logger.error("error while writing '{}' : {}",command,e.getMessage());
		}
		return false;
	}

	public void disconnect() {
		try {
			telnet.disconnect();
		}
		catch (Exception e) {
			logger.error("error while disconnecting telnet client");
		}
	}


}