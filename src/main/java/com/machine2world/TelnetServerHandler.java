package com.machine2world;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TelnetServerHandler extends IoHandlerAdapter {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void exceptionCaught(IoSession session, Throwable exc)
			throws Exception {
		logger.error("Session {} threw exception {}", session.getId(), exc.toString());
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String messageText = message.toString();
		session.write(messageText);
		logger.debug("received message '{}'",messageText);		
		
		if (messageText.equals("quit")){
			session.close(false);
			
			return;
		}
			 	
		
	}

	

}
