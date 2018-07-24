package org.chron.example.net;

/**
 * @Class Name : WebSocketClientEndpoint.java
 * @Description: WebSocketClientEndpoint Class
 * 
 * @author : DUKE
 * @since  : Jul 9, 2018
 * @version: 1.0
 * @see
 * @Copyright ⓒ 2011 Brique Co.,Ltd, All Rights Reserved.
 * 
 * <pre>
 * —----------------------------------------------------------------
 * Modification Information 
 * —----------------------------------------------------------------
 *   Date        Modifier              Contents
 * —----------------------------------------------------------------
 * Jul 9, 2018       brique               Create
 *
 * </pre>  
 */

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class WebSocketClientEndpoint {
	Session session = null;
	IWebSocketMessageHandler handler = null;

	/**
	 * Connect to the url
	 * 
	 * @param strUrl
	 */
	public void connect(String strUrl) {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();

		try {
			container.connectToServer(this, URI.create(strUrl));
		}
		catch(DeploymentException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Register message handler
	 * 
	 * @param handler
	 */
	public void addMessageHandler(IWebSocketMessageHandler handler) {
		this.handler = handler;
	}

	/**
	 * When message received
	 * 
	 * @param strMsg
	 */
	@OnMessage
	public void onMessage(String strMsg) {
		if(handler == null) {
			return;
		}

		handler.handleMessage(strMsg);
	}

	/**
	 * When connection opened
	 * 
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		System.out.println(String.format("Open: %s", session.toString()));
		this.session = session;
	}

	/**
	 * When connection closed
	 * 
	 * @param session
	 * @param reason
	 */
	@OnClose
	public void onClise(Session session, CloseReason reason) {
		System.out.println(String.format("Close: %s", session.toString()));
		this.session = null;
	}

	/**
	 * When error occurred
	 * 
	 * @param e
	 */
	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}

}
