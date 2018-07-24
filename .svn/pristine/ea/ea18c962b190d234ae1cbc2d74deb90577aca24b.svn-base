package org.chron.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.chron.example.util.JSONUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Class Name : ExampleController.java
 * @Description: ExampleController Class
 * 
 * @author : DUKE
 * @since  : Jul 18, 2018
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
 * Jul 18, 2018       brique               Create
 *
 * </pre>  
 */

@RestController
@RequestMapping("/chron/exam")
public class ExampleController {

	@RequestMapping(path = "/data/{id}", method = RequestMethod.GET)
	public Map<String, Object> selectData(@PathVariable(name = "id") String strId) {
		Map<String, Object> resultMap = new HashMap<>();

		Map<String, String> map = new HashMap<>();
		map.put("id"  , strId);
		map.put("name", "aaa");

		resultMap.put("RESULT_CODE", HttpStatus.OK.value());
		resultMap.put("RESULT_MSG" , HttpStatus.OK.series());
		resultMap.put("RESULT_DATA", JSONUtil.toJSONString(map));

		return resultMap;
	}

}
