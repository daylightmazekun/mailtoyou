package nukezam.mailtoyou.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import nukezam.mailtoyou.bean.WeatherEntity;

/**
 * @Package :nukezam.mailtoyou.utils
 * @Title: JsonToEntity.java
 * @Package nukezam.mailtoyou.utils
 * @author zekun ma burmaing@gmail.com
 * @date 2018年3月31日 下午9:15:02
 * @version V1.0
 */
public class JsonToEntity {
	// public static void main(String[] args) throws Exception {
	// JsonToEntity jsonToEntity = new JsonToEntity();
	// jsonToEntity.jsonToEntity();
	// }
	public WeatherEntity jsonToEntity() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		WeatherEntity weatherEntity = null;
		try {

			WeatherUtil weatherUtil = new WeatherUtil();
			// JSON to POJO
			weatherEntity = mapper.readValue(weatherUtil.getWeather(), WeatherEntity.class);

			System.out.println(weatherEntity.toString());

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return weatherEntity;
	}
}
