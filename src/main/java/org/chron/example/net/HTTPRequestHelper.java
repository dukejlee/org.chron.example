package org.chron.example.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.chron.example.util.JSONUtil;

/**
 * @Class Name : HTTPRequestHelper.java
 * @Description: HTTPRequestHelper Class
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
public class HTTPRequestHelper {
	public static final String POST  = "POST";
	public static final String GET   = "GET";
	public static final String PUT   = "PUT";
	public static final String DELTE = "DELETE";

	/**
	 * Call REST API
	 * 
	 * @param strUrl
	 * @param strMethod
	 * @return
	 */
	public String request(String strUrl, String strMethod) {
		return request(strUrl, strMethod, null);
	}

	/**
	 * Call REST API
	 * 
	 * @param strUrl
	 * @param strMethod
	 * @param strParams
	 * @return
	 */
	public String request(String strUrl, String strMethod, String strParams) {
		StringBuffer strBuff = new StringBuffer();
		strMethod = strMethod == null? GET: strMethod;

		if(strParams != null && strParams.length() > 0 && strMethod.equals(GET)) {
			strUrl += (strUrl.charAt(strUrl.length() - 1) == '/')? strParams: "/" + strParams;
		}

		HttpURLConnection conn;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		InputStreamReader in = null;

		try {
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod(strMethod);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("charset", "utf-8");

			if(strParams != null && strParams.length() > 0 && strMethod.equals(POST)) {
				conn.setDoOutput(true);

				writer = new OutputStreamWriter(conn.getOutputStream());
				writer.write(strParams);
				writer.flush();
			}

			int iResponse = conn.getResponseCode();

			System.out.println(String.format("SEND: [%s] %s", strMethod, strUrl));
			System.out.println(String.format("RECV: %s"     , iResponse));

			in = new InputStreamReader(iResponse == 200? conn.getInputStream(): conn.getErrorStream());
			reader = new BufferedReader(in);

			String strLine = null;

			while((strLine = reader.readLine()) != null) {
				strBuff.append(strLine);
			}

			System.out.println(strBuff.toString());
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			try {writer.close();}catch(Exception e) {}
			try {reader.close();}catch(Exception e) {}
			try {in.close();}catch(Exception e) {}
		}

		return strBuff.toString();
	}

	/**
	 * Convert map to parameter string
	 * 
	 * @param map
	 * @return
	 */
	public String mapToParams(Map<String, String> map) {
		StringBuilder strBuff = new StringBuilder();

		for(String strKey: map.keySet()) {
			strBuff.append(strBuff.length() > 0? "&": "");
			strBuff.append(String.format("%s=%s", encodeUrl(strKey), encodeUrl(map.get(strKey).toString())));
		}

		return strBuff.toString();
	}

	/**
	 * Convert map to json string
	 * 
	 * @param map
	 * @return
	 */
	public String mapToJsonStr(Map<String, Object> map) {
		return JSONUtil.toJSONString(map);
	}

	/**
	 * Encode string
	 * 
	 * @param strValue
	 * @return
	 */
	public String encodeUrl(String strValue) {
		try {
			return URLEncoder.encode(strValue, "UTF-8");
		}
		catch(UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
