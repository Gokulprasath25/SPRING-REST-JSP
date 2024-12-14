package spring.mvc.crud.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HelperUtils {
	
	public static String convertDtoToJsonString(Object dto) {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		try {
			String jsonStr = objectMapper.writeValueAsString(dto);
			return jsonStr;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
