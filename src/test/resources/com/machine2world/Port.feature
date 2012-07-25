Feature: a telnet port is open for connections

Scenario: A telnet connection is accepted

	Given a simple HelloMina server
	When a telnet client connects
	Then the connection should be accepted
	
Scenario: The connection is closeable

	Given a simple HelloMina server
	And a telnet connection
	When the 'quit' command is given
	Then the connection is closed

