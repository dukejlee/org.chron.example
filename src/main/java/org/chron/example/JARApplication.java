package org.chron.example;

import java.util.HashMap;
import java.util.Map;

import org.chron.example.util.JSONUtil;

/**
 * @Class Name : JARApplication.java
 * @Description: JARApplication Class
 * 
 * @author : DUKE
 * @since  : Jul 31, 2018
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
 * Jul 31, 2018       brique               Create
 *
 * </pre>  
 */
public class JARApplication {

	public void doing() throws Exception {
		double dA = Double.parseDouble(System.getProperty("INPUT_A", "0,0"));
		double dB = Double.parseDouble(System.getProperty("INPUT_B", "0,0"));
		double dResult = dA * dB;

		System.out.println(String.format("Result %s X %s = %s", dA, dB, dResult));

		Map<String, Object> map = new HashMap<>();
		map.put("RESULT_MSG"     , "OK");
		map.put("RESULT_MULTIPLY", dResult);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("OUTPUT", map);

		System.out.println(JSONUtil.toJSONString(resultMap));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new JARApplication().doing();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
