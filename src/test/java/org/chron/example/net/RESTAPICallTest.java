package org.chron.example.net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : RESTAPICallTest.java
 * @Description: RESTAPICallTest Class
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
public class RESTAPICallTest {
	final static String HOST          = "http://192.168.0.39:8082";
	final static String CALL_WORKFLOW = "/composer/akka/request/%s/%s/0/0/0";

	/**
	 * 
	 * @throws Exception
	 */
	public void doing() throws Exception {
		List<Map<String, Object>> list = new ArrayList<>();

		Map<String, Object> map = new HashMap<>();
		map.put("param_name" , "FILE_URL");
		map.put("param_value", "/opt/chronotics/data/user_res/2018-07-05_08-37-12.4EAE0201.4EAE0201_A.CC_POM_R05_M10_B.csv");
		map.put("task_id"    , "2");

		list.add(map);

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("params", list);

		String strUrl = HOST + String.format(CALL_WORKFLOW, "1", "651");

		HTTPRequestHelper helper = new HTTPRequestHelper();
		String strResult = helper.request(strUrl, HTTPRequestHelper.POST, helper.mapToJsonStr(paramMap));

		System.out.println(strResult);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new RESTAPICallTest().doing();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
