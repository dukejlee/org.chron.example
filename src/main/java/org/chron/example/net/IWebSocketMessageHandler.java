package org.chron.example.net;

/**
 * @Class Name : IWebSocketMessageHandler.java
 * @Description: IWebSocketMessageHandler Class
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
public interface IWebSocketMessageHandler {

	/**
	 * Handle message
	 * 
	 * @param strMsg
	 */
	public void handleMessage(String strMsg);

}
