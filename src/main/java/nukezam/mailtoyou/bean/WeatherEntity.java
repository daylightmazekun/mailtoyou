package nukezam.mailtoyou.bean;

/** 
* @Package :nukezam.mailtoyou.bean
* @Title: WeatherEntity.java 
* @Package nukezam.mailtoyou.bean 
* @author zekun ma burmaing@gmail.com   
* @date 2018年3月31日 下午9:26:49 
* @version V1.0   
*/
/**
 * Copyright 2018 bejson.com 
 */
import java.util.List;


public class WeatherEntity {

   private String success;
   private List<Result> result;
   public void setSuccess(String success) {
        this.success = success;
    }
    public String getSuccess() {
        return success;
    }

   public void setResult(List<Result> result) {
        this.result = result;
    }
    public List<Result> getResult() {
        return result;
    }

}
