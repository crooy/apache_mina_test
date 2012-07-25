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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author ronald
 */
public class HelloMina {

    /**
     * @param args the command line arguments
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
    	 PropertyConfigurator.configure("log4j.properties");
    	
    	 IoAcceptor acceptor = new NioSocketAcceptor();
    	 acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
         acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" ))));
         
         acceptor.setHandler(new TelnetServerHandler());
         
         acceptor.bind( new InetSocketAddress(6666));
         
         
    }
}
