package nukezam.mailtoyou.email;

import java.util.ArrayList;
import java.util.List;

import nukezam.mailtoyou.bean.People;

/** 
* @Package :nukezam.mailtoyou.email
* @Title: Main.java 
* @Package nukezam.mailtoyou.email 
* @author zekun ma burmaing@gmail.com   
* @date 2018年1月8日 下午8:33:47 
* @version V1.0   
*/
public class Main {
public static void main(String[] args) {
	List<People> ListPeople = new ArrayList<People>();
	People people = new People();
	people.setEmail("burmaing@gmail.com");
	ListPeople.add(people);
	EmailToYou emailToYou= new EmailToYou(ListPeople);
	emailToYou.run();
//	try {
//		emailToYou.wait(1000);
//	} catch (InterruptedException e) {
//		e.printStackTrace();
//	}
}
}
