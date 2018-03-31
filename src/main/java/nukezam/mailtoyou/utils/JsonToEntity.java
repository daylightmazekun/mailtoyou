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
	public Object jsonToEntity(){

    	ObjectMapper mapper = new ObjectMapper(); 

    	try {
    			
    		//POJO to JSON
//			mapper.writeValue(new File("article.json"), createArticle());
//			System.out.println("json created!");

			//JSON to POJO
    		WeatherEntity article = mapper.readValue(new File("article.json"), WeatherEntity.class);

			//"Raw" Data Binding Example
			Map<String,Object> articleMap = mapper.readValue(new File("article.json"), Map.class);

			System.out.println(article);

			System.out.println(articleMap);

			//Data binding Collection<E>
			List<WeatherEntity> articles = new LinkedList<WeatherEntity>();

//			articles.add(createArticle());
//			articles.add(createArticle());

			mapper.writeValue(new File("articles.json"), articles);
			
			//( 1 ) Collection<Map>
			List result = mapper.readValue(new File("articles.json"), List.class);
			System.out.println(result.get(0).getClass());
			System.out.println(result);

			//( 2 ) Collection<Artilce>
			result = mapper.readValue(new File("articles.json"), new TypeReference<List<WeatherEntity>>() { });
			System.out.println(result.get(0).getClass());
			System.out.println(result);
			
			System.out.println("---------------------------------------------------------");
			//Tree
			ObjectNode objectRoot = (ObjectNode) mapper.readTree(new File("article.json"));
			Iterator<String> fields = objectRoot.fieldNames();
			String field = "";
			while(fields.hasNext()){
				field = fields.next();
				System.out.println("field: "+field);
			}
			System.out.println("---------------------------------------------------------");

			ArrayNode arrayRoot = (ArrayNode) mapper.readTree(new File("articles.json"));

			Iterator<JsonNode> elements = arrayRoot.elements();
			JsonNode element;

			while(elements.hasNext()){
				element = elements.next();				
				fields = element.fieldNames();
				field = "";
				while(fields.hasNext()){
					field = fields.next();
					System.out.println("field: "+field);
				}

			}

    	} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapper;
    }

//    private static Article createArticle(){
//
//    	Article article = new Article();
//
//    	article.setTitle("Jackson - Java to JSON & JSON to Java");
//    	article.setUrl("http://hmkcode.com/jackson-java-json");
//    	article.addCategory("Java");
//    	article.addTag("Java");
//    	article.addTag("Jackson");
//    	article.addTag("JSON");
//
//    	return article;
//    }
}