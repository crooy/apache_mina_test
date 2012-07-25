Feature: the telnet server enables chatting

Scenario: At least two telnet connections are possible

	Given a chat HelloMina server
	When telnet client 1 connects
	And telnet client 2 connects
	Then the connection of client 1 is open
	And the connection of client 2 is open
	
Scenario: only one connection is closed when quit is given

	Given a chat HelloMina server
	And a telnet connection 1
	And a telnet connection 2
	When the 'quit' command is given by connection 1
	Then the connection of client 1 is closed
	And the connection of client 2 is open
	
Scenario: The text from client 1 is sent to all clients

	Given a chat HelloMina server
	And a telnet connection 1
	And a telnet connection 2
	When the 'Hello' command is given by connection 1
	Then connection 2 receives 'Hello'

