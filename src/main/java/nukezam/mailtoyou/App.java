package nukezam.mailtoyou;

import java.util.ArrayList;

import nukezam.mailtoyou.bean.People;
import nukezam.mailtoyou.email.EmailToYou;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ArrayList<People> ListPeople = new ArrayList<People>();
		People people = new People();
		people.setEmail("burmaing@gmail.com");
		ListPeople.add(people);
		EmailToYou emailToYou = new EmailToYou(ListPeople);
//		for (;;) {
//			try {
				emailToYou.run();
				//Thread.sleep(200);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}
}
