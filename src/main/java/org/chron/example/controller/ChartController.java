package org.chron.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Class Name : ChartController.java
 * @Description: ChartController Class
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/chart")
public class ChartController {
	final static String TITLE = "Auto Settling";

	final static int LCL = 2;
	final static int UCL = 10;

	final static String[] ARR_LABEL = {"X", "LCL", "Y", "UCL"};
	final static String[] ARR_COLOR = {"red", "gray", "blue"};

	/**
	 * Get chart data
	 * 
	 * @param strId
	 * @param iMax
	 * @param iCount
	 * @return
	 */
	@RequestMapping(path = "/data/{max}/{count}", method = RequestMethod.GET)
	public List<Map<String, Object>> selectData(
			@PathVariable(name = "max") int iMax,
			@PathVariable(name = "count") int iCount) {

		List<Map<String, Object>> list = new ArrayList<>(iCount);

		for(int i=0; i < iCount; i++) {
			list.add(getChartData(iMax, i+1));
		}

		return list;
	}

	/**
	 * Get chart data with lcl and ucl
	 * 
	 * @param iMax
	 * @param iCount
	 * @param iLcl
	 * @param iUcl
	 * @return
	 */
	@RequestMapping(path = "/data/cl/{max}/{count}/{lcl}/{ucl}", method = RequestMethod.GET)
	public List<Map<String, Object>> selectDataWithUCL(
			@PathVariable(name = "max") int iMax,
			@PathVariable(name = "count") int iCount,
			@PathVariable(name = "lcl") int iLcl,
			@PathVariable(name = "ucl") int iUcl) {

		List<Map<String, Object>> list = new ArrayList<>(iCount);

		for(int i=0; i < iCount; i++) {
			list.add(getChartData(iMax, i+1, iLcl, iUcl));
		}

		return list;
	}

	/**
	 * Get chart data
	 * 
	 * @param strId
	 * @param iMax
	 * @param iCount
	 * @return
	 */
	@RequestMapping(path = "/data_all/{id}/{max}/{count}", method = RequestMethod.GET)
	public List<Map<String, Object>> selectDataAll(
			@PathVariable(name = "id") String strId,
			@PathVariable(name = "max") int iMax,
			@PathVariable(name = "count") int iCount) {

		List<Map<String, Object>> list = new ArrayList<>(iCount);

		for(int i=0; i < iCount; i++) {
			list.add(getChartDataAll(strId, iMax, i+1));
		}

		return list;
	}

	//{
	//  "data": [
	//    [1.0, 0.0, 1.51711106300354, 10.0]
	//  ],
	//  "title": "Auto Settling-1",
	//}
	/**
	 * Make Json string for charting
	 * 
	 * @param iMax
	 * @param iIndex
	 * @return
	 */
	Map<String, Object> getChartData(int iMax, int iIndex) {
		return getChartData(iMax, iIndex, LCL, UCL);
	}

	/**
	 * Make Json string for charting with lcl and ucl
	 * 
	 * @param iMax
	 * @param iIndex
	 * @param iLcl
	 * @param iUcl
	 * @return
	 */
	Map<String, Object> getChartData(int iMax, int iIndex, int iLcl, int iUcl) {
		Map<String, Object> resultMap = new HashMap<>();

		// Make chart data
		List<double[]> list = new ArrayList<>(iMax);
		double[] dData = null;

		Random random = new Random();

		for(int i=0; i < iMax; i++) {
			dData = new double[4];
			dData[0] = i+1;
			dData[1] = iLcl;
			dData[2] = random.nextFloat() * 15;
			dData[3] = iUcl;

			list.add(dData);
		}

		resultMap.put("title", TITLE + "-" + iIndex);
		resultMap.put("data" , list);

		return resultMap;
	}

	//{
	//  "data": [
	//    [1.0, 0.0, 1.51711106300354, 10.0]
	//  ],
	//  "options": {
	//    "underlayCallback": {
	//      "color": "yellow",
	//      "areas": [
	//        [1, 2], [6, 8], [14, 19]
	//      ]
	//    },
	//    "title": "Auto Settling-1",
	//    "colors": ["red", "gray", "blue"],
	//    "labels": ["X", "LCL", "Y", "UCL"]
	//  },
	//  "id": "id"
	//}

	/**
	 * Make Json string for charting
	 * 
	 * @param strId
	 * @param iMax
	 * @param iIndex
	 * @return
	 */
	Map<String, Object> getChartDataAll(String strId, int iMax, int iIndex) {
		Map<String, Object> resultMap = new HashMap<>();

		// Make chart data
		List<double[]> list = new ArrayList<>(iMax);
		double[] dData = null;

		Random random = new Random();

		for(int i=0; i < iMax; i++) {
			dData = new double[4];
			dData[0] = i+1;
			dData[1] = LCL;
			dData[2] = random.nextFloat() * 15;
			dData[3] = UCL;

			list.add(dData);
		}

		// Make area
		List<int[]> listArea = new ArrayList<>();
		listArea.add(new int[]{1, 2});
		listArea.add(new int[]{6, 8});
		listArea.add(new int[]{14, 19});

		Map<String, Object> callbackMap = new HashMap<>();
		callbackMap.put("areas", listArea);
		callbackMap.put("color", "yellow");

		// Make options
		Map<String, Object> optionMap = new HashMap<>();
		optionMap.put("title"           , TITLE + "-" + iIndex);
		optionMap.put("labels"          , ARR_LABEL);
		optionMap.put("colors"          , ARR_COLOR);
		optionMap.put("underlayCallback", callbackMap);

		resultMap.put("id"     , strId);
		resultMap.put("data"   , list);
		resultMap.put("options", optionMap);

		return resultMap;
	}

}
