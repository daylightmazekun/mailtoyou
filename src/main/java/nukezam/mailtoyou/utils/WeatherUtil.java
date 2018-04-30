package nukezam.mailtoyou.utils;

import java.net.*;
import java.io.*;

/**
 * @Package :nukezam.mailtoyou.email
 * @Title: MainClass.java
 * @Package nukezam.mailtoyou.email
 * @author zekun ma burmaing@gmail.com
 * @date 2018年3月29日 下午8:44:01
 * @version V1.0
 */
public class WeatherUtil {

	public String getWeather() throws Exception {
		// 101190401
		URL u = new URL(
				"http://api.k780.com/?app=weather.future&weaid=101180101&appkey=32531&sign=68cf0209f9b1bb1961f689ac96b67c5a&format=json");
		InputStream in = u.openStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			byte buf[] = new byte[1024];
			int read = 0;
			while ((read = in.read(buf)) > 0) {
				out.write(buf, 0, read);
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
		byte b[] = out.toByteArray();
		return new String(b, "utf-8");
		// System.out.println(new String(b,"utf-8"));
	}
}
