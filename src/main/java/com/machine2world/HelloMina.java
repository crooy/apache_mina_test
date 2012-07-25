package com.machine2world;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.PropertyConfigurator;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author ronald
 */
public class HelloMina implements Runnable {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    /**
     * @param args the command line arguments
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
    	 PropertyConfigurator.configure("log4j.properties");
    	
    	 HelloMina helloMinaServer  = new HelloMina(6666);
    	 helloMinaServer.start();
         
        
    }

    private int port;
	private IoAcceptor acceptor;
    public HelloMina(int port){
    	this.port = port;
    }
    
	private void start() throws IOException, InterruptedException {
		 acceptor = new NioSocketAcceptor();
    	 acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
         acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" ))));
         
         acceptor.setHandler(new TelnetServerHandler());
         
         acceptor.bind( new InetSocketAddress(port));
         
         Thread.sleep(100);
         
	}
	
	@Override
	protected void finalize() throws Throwable {
		acceptor.unbind();
		super.finalize();
	}

	@Override
	public void run() {		
		try {
			start();
			while (!acceptor.isActive()){
				Thread.sleep(10);
			}
		} catch (IOException e) {
			logger.error("IOException : ", e.getMessage());
		} catch (InterruptedException e) {
			logger.error("InterruptedException : ", e.getMessage());
		}
	}
	
}
