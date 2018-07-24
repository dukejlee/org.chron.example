package org.chron.example.net;

import org.chron.example.net.IWebSocketMessageHandler;
import org.chron.example.net.WebSocketClientEndpoint;

/**
 * @Class Name : WebSocketClientTest.java
 * @Description: WebSocketClientTest Class
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
public class WebSocketClientTest implements IWebSocketMessageHandler {

	public void doing() throws Exception {
		String strUrl = "ws://192.168.0.39:8081/ws-msg";

		WebSocketClientEndpoint client = new WebSocketClientEndpoint();
		client.connect(strUrl);
		client.addMessageHandler(this);

		Thread.sleep(20000);
	}

	@Override
	public void handleMessage(String strMsg) {
		System.out.println(strMsg);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new WebSocketClientTest().doing();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
