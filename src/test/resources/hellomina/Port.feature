Feature: open port to connect to with telnet

Scenario: A telnet connection is accepted

	Given a HelloMina server
	When a telnet client connects
	Then the connection should be accepted
	