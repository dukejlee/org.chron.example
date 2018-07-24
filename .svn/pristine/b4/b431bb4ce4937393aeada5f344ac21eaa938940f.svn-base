package org.chron.example.util;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @Class Name: JSONUtil.java
 * @Description: JSON 관련 유틸리티 클래스
 * 
 * @author DUKE
 * @since 2016. 1. 28.
 * @version 1.0
 * @see 
 * @Copyright ⓒ 2016 Brique Co.,Ltd, All Rights Reserved.
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일          수정자              수정내용
 * ------------------------------------------------------------------
 * 2016. 1. 28.       DUKE               최초생성
 *
 * </pre>  
 */

public class JSONUtil {
	/**
	 * JSON 형태의 데이터를 지정된 클래스 형태로 추출
	 * 
	 * @param strJSON
	 * @param clazz
	 * @return
	 */
	public static <T> T toObject(String strJSON, Class<T> clazz) {
		try {
			return new ObjectMapper().readValue(strJSON, clazz);
		}
		catch(JsonParseException e) {
			throw new RuntimeException(e);
		}
		catch(JsonMappingException e) {
			throw new RuntimeException(e);
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * JSON 형태의 데이터를 Map 객체로 추출
	 * 
	 * @param strJSON
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toObjectMap(String strJSON) {
		Map<String, Object> map = null;

		try {
			map = new ObjectMapper().readValue(strJSON, Map.class);
		}
		catch(JsonParseException e) {
			throw new RuntimeException(e);
		}
		catch(JsonMappingException e) {
			throw new RuntimeException(e);
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}

		return map;
	}

	/**
	 * 자바 Object로부터 JSON 형태의 문자열로 변환
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJSONString(Object obj) {
		String strJSON = null;

		try {
			strJSON = new ObjectMapper().writeValueAsString(obj);
		}
		catch(JsonParseException e) {
			throw new RuntimeException(e);
		}
		catch(JsonMappingException e) {
			throw new RuntimeException(e);
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}

		return strJSON;
	}

}
